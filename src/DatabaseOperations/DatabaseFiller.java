package DatabaseOperations;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DatabaseFiller {

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
    private static void readDataFromJson(@NotNull  Connection conn, String filePath, String insertionInitQuery, String[] fields) throws IOException, SQLException {
        JsonArray jsonData = getJsonData(filePath);
        final StringBuilder addingQuery = new StringBuilder(insertionInitQuery);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (var i : jsonData) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentSubquery = new StringBuilder("(");
                    JsonObject currentJson = i.getAsJsonObject();
                    for (String field : fields) {
                        String fieldValue = currentJson.get(field).getAsString();
                        if (Objects.equals(field, "date")){
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date date = dateFormat.parse(fieldValue);
                                currentSubquery.append(date.getTime()).append(", ");
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            currentSubquery.append(generateQuotedString(fieldValue)).append(", ");
                        }
                    }
                    currentSubquery = new StringBuilder(currentSubquery.substring(0, currentSubquery.length() - 2) + "), ");
                    synchronized (addingQuery) {
                        addingQuery.append(currentSubquery);
                    }
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Statement stmt = conn.createStatement();
        int rowsAffected = stmt.executeUpdate(addingQuery.substring(0, addingQuery.length() - 2) + ';');
        if (ConstantsContainer.PROCESSING_LOGGER){
            System.out.println("Objects loaded: " + rowsAffected);
        }
        stmt.close();
    }


    /**Loads the  data from the Products table into the database*/
    private static void readProductsFile(Connection conn) throws SQLException, IOException {
        readDataFromJson(conn, ConstantsContainer.productsFilePath, ConstantsContainer.productsInsertionInit, ConstantsContainer.productsJsonFields);;
    }

    /**Loads the  data from the Sales json into the database*/
    private static void readSalesFile(Connection conn) throws SQLException, IOException{
        readDataFromJson(conn, ConstantsContainer.salesFilePath, ConstantsContainer.salesInsertInit, ConstantsContainer.salesJsonFields);
    }

    /**Loads the data from the Salesmen table into the database*/
    private static void readSalesmenFile(Connection conn) throws SQLException, IOException{
        readDataFromJson(conn, ConstantsContainer.salesmenFilePath, ConstantsContainer.salesmenInsertInit, ConstantsContainer.salesmenJsonFields);
    }

    /**Loads the data from the Stock table inti the database*/
    private static void readStockFile(Connection conn) throws SQLException, IOException{
        readDataFromJson(conn, ConstantsContainer.stockFilePath, ConstantsContainer.stockInsertInit, ConstantsContainer.stockJsonFields);
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
