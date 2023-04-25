package DatabaseOperations;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultsWriter {

    /**Writes the result json into the .json file*/
    public static void writeResultsToJson(String fileName, JsonObject resultJson){
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(new Gson().toJson(resultJson));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Writes results for Task I V.0 */
    private static void writeMostProductsHolder(Connection connection) throws SQLException {
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.goodsAmountBySellersQuery(connection);
        while (rs.next()){
            int productID = rs.getInt("ProductID");
            int sellerId = rs.getInt("SellerID");
            int quantity = rs.getInt("MaxQuantity");
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("product_id", productID);
            currentJson.addProperty("seller_id", sellerId);
            currentJson.addProperty("quantity", quantity);
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oMostProductsHolder, outputData);
    }

    /**Writes results for Task I V.1*/
    private static void writeMinPriceTask(Connection connection) throws SQLException{
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.minPriceQuery(connection);
        while (rs.next()){
            int productID = rs.getInt("ProductID");
            int sellerId = rs.getInt("SellerID");
            int minPrice = rs.getInt("MinPrice");
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("product_id", productID);
            currentJson.addProperty("seller_id", sellerId);
            currentJson.addProperty("min_price", minPrice);
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oLeastPrice, outputData);
    }

    /**Writes results for Task I V.2*/
    private static void writeProductsAmountTask(Connection connection) throws SQLException{
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.goodsAmountsQuery(connection);
        while(rs.next()){
            int productId = rs.getInt("ProductID");
            String productName = rs.getString("ProductName");
            int quantity = rs.getInt("TotalQuantity");
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("product_id", productId);
            currentJson.addProperty("product_name", productName);
            currentJson.addProperty("quantity", quantity);
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oGeneralStock, outputData);
    }

    /**Writes results for Task I V.3 */
    private static void writeGoodsSoldGeneral(Connection connection) throws SQLException {
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.goodsSoldGeneral(connection);
        while(rs.next()){
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("product_id", rs.getInt("ProductID"));
            currentJson.addProperty("total_sold", rs.getInt("TotalSold"));
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oGeneralSold, outputData);
    }

    /**Writes results for Task I V. 4*/
    private static void writeTopFiveSellers(Connection connection) throws SQLException{
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.topFiveSellersQuery(connection);
        while(rs.next()){
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("seller_id", rs.getInt("SellerID"));
            currentJson.addProperty("products_sold", rs.getInt("SoldGoods"));
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oTopFiveSellers, outputData);
    }


    /**Writes results for Task I V.5*/
    private static void writeFivePopularProducts(Connection connection) throws SQLException{
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.topFivePopularProductsQuery(connection);
        while(rs.next()){
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("product_id", rs.getInt("ProductID"));
            currentJson.addProperty("quantity_sold", rs.getInt("TotalSold"));
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oMostPopularGoods, outputData);
    }

    /**Writes results for Task II V.0*/
    private static void writeSalesDistribution(Connection connection) throws  SQLException{
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.salesDateDistributionQuery(connection);
        while(rs.next()){
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("sale_date", rs.getDate("SaleDate").toString());
            currentJson.addProperty("quantity_sold", rs.getInt("TotalSales"));
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oSellsDistribution, outputData);
    }

    /**Writes results for Task II V.1*/
    private static void writeTopSalesDays(Connection connection) throws SQLException{
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.topSalesDaysQuery(connection);
        while(rs.next()){
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("sale_date", rs.getDate("SaleDate").toString());
            currentJson.addProperty("quantity_sold", rs.getInt("TotalSales"));
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oFiveBestDates, outputData);
    }

    private static void writeAverageSells(Connection connection) throws  SQLException{
        JsonArray outputJsonArray = new JsonArray();
        JsonObject outputData = new JsonObject();
        ResultSet rs = ShopQueries.getAverageSalesQuery(connection);
        while(rs.next()){
            JsonObject currentJson = new JsonObject();
            currentJson.addProperty("avg_sales", rs.getInt("AvgSalesPerDay"));
            outputJsonArray.add(currentJson);
        }
        outputData.add("data", outputJsonArray);
        writeResultsToJson(ConstantsContainer.oAvgSells, outputData);
    }

    /**Writes all the tasks results into the json files*/
    public static void writeAllSolutions(Connection connection) throws SQLException, IOException {
        writeMostProductsHolder(connection);
        writeMinPriceTask(connection);
        writeProductsAmountTask(connection);
        writeGoodsSoldGeneral(connection);
        writeTopFiveSellers(connection);
        writeFivePopularProducts(connection);
        writeSalesDistribution(connection);
        writeTopSalesDays(connection);
        writeAverageSells(connection);
        Desktop.getDesktop().open(new File("output"));

    }
}


