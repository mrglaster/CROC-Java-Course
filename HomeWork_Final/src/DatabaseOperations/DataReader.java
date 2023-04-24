package DatabaseOperations;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataReader {

    /**Reads array of JSON from the input file*/
    public static JsonArray getJsonData(String fileName) throws IOException {
        Gson gson = new Gson();
        String json = Files.readString(Paths.get(fileName));
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        return jsonObject.getAsJsonArray("data");
    }

    /**Encloses the input data in quotation marks*/
    private static String generateQuotedString(Object inputData){
        return '"' + inputData.toString() + '"';
    }


    /**Uploads data from the JSON file into the database*/
    private static void readDataFromJson(Connection conn, String filePath, String insertionInitQuery, String[] fields) throws IOException, SQLException {
        JsonArray jsonData = getJsonData(filePath);
        StringBuilder addingQuery = new StringBuilder(insertionInitQuery);
        for (var i : jsonData){
            StringBuilder currentSubquery = new StringBuilder("(");
            JsonObject currentJson = i.getAsJsonObject();
            for (String field : fields) {
                String fieldValue = currentJson.get(field).getAsString();
                currentSubquery.append(generateQuotedString(fieldValue)).append(", ");
            }
            currentSubquery = new StringBuilder(currentSubquery.substring(0, currentSubquery.length() - 2) + "), ");
            addingQuery.append(currentSubquery);
        }
        addingQuery = new StringBuilder(addingQuery.substring(0, addingQuery.length() - 2) + ';');
        Statement stmt = conn.createStatement();
        int rowsAffected = stmt.executeUpdate(addingQuery.toString());
        if (ConstantsContainer.PROCESSING_LOGGER){
            System.out.println("Objects loaded: " + rowsAffected);
        }
        stmt.close();
    }


    /**Loads the  data from the Products table into the database*/
    private static void readProductsFile(Connection conn) throws SQLException, IOException {
        readDataFromJson(conn, ConstantsContainer.goodsFilePath, ConstantsContainer.productsInsertionInit, new String[]{"id", "name"});;
    }

    /**Loads the  data from the Sales json into the database*/
    private static void readSalesFile(Connection conn) throws SQLException, IOException{
        readDataFromJson(conn, ConstantsContainer.salesFilePath, ConstantsContainer.salesInsertInit, new String[]{"sale_id", "seller_id", "product_id", "quantity", "date"});
    }

    /**Loads the data from the Salesmen table into the database*/
    private static void readSalesmenFile(Connection conn) throws SQLException, IOException{
        readDataFromJson(conn, ConstantsContainer.salesmenFilePath, ConstantsContainer.salesmenInsertInit, new String[]{"id", "last_name", "first_name"});
    }

    /**Loads the data from the Stock table inti the database*/
    private static void readStockFile(Connection conn) throws SQLException, IOException{
        readDataFromJson(conn, ConstantsContainer.stockFilePath, ConstantsContainer.stockInsertInit, new String[]{"seller_id", "product_id", "price", "quantity"});
    }

    /**Fills the database with the data from the files*/
    public static void fillDatabase(Connection conn) throws SQLException, IOException {
        DatabaseFunctions.clearTables(conn);
        System.out.println("\nLoading table:  Salesmen ");
        readSalesmenFile(conn);
        System.out.println("\nLoading table: Stock ");
        readStockFile(conn);
        System.out.println("\nReading table: Products ");
        readProductsFile(conn);
        System.out.println("\nReading table:  Sales ");
        readSalesFile(conn);
    }

}
