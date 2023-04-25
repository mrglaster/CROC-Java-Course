package DatabaseOperations;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopQueries {

    /**Executes query for data getting and returns ResultSet as the result*/
    private static ResultSet processGettingQuery(@NotNull Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    /**For each product, display the seller who has the largest quantity of this product in stock, and the amount of this product he has in stock (Task I V.0*/
    public static ResultSet goodsAmountBySellersQuery(Connection connection) throws SQLException{
        String query = """
                SELECT p.ProductID, s.SellerID, MAX(st.Quantity) AS MaxQuantity
                FROM Products p
                JOIN Stock st ON p.ProductID = st.ProductID
                JOIN Sellers s ON s.SellerID = st.SellerID
                GROUP BY p.ProductID
                """;
        return processGettingQuery(connection, query);
    }

    /**For each product display the seller who has the lowest price for this product, and the price for this product from this seller (Task I V. 1)*/
    public static ResultSet minPriceQuery(Connection connection) throws SQLException{
        String query =  """
                SELECT p.ProductID, s.SellerID, MIN(st.Price) AS MinPrice
                FROM Products p
                JOIN Stock st ON p.ProductID = st.ProductID
                JOIN Sellers s ON s.SellerID = st.SellerID
                GROUP BY p.ProductID
                """;
        return processGettingQuery(connection, query);
    }


    /**Processes query getting total amount of products by type (Task I V.2)*/
    public static ResultSet goodsAmountsQuery(Connection connection) throws SQLException {
        String query = "SELECT ProductID, ProductName, SUM(Quantity) AS TotalQuantity FROM Products JOIN Stock USING (ProductID) GROUP BY ProductID;";
        return processGettingQuery(connection, query);
    }


    /**For each product display the total number of products of this type sold (Task I. V.3)*/
    public  static ResultSet goodsSoldGeneral(Connection connection) throws SQLException{
        String query = """
                SELECT p.ProductID, SUM(s.QuantitySold) AS TotalSold
                FROM Products p
                JOIN Sales s ON p.ProductID = s.ProductID
                GROUP BY p.ProductID ORDER BY p.ProductID""";
        return processGettingQuery(connection, query);
    }

    /**Output the top 5 sellers who sold the largest number of goods (Task I, V. 4)*/
    public static ResultSet topFiveSellersQuery(Connection connection) throws  SQLException{
        String query = """
                SELECT s.SellerID, SUM(sa.QuantitySold) AS TotalSold
                FROM Sellers s
                JOIN Sales sa ON s.SellerID = sa.SellerID
                GROUP BY s.SellerID
                ORDER BY TotalSold DESC
                LIMIT 5""";
        return processGettingQuery(connection, query);
    }

    /**Output top 5 products with fast sales growth (Task I */
    public static ResultSet topFivePopularProducts(Connection connection) throws SQLException{
        String query = """
                SELECT p.ProductID, SUM(s.QuantitySold) AS TotalSold
                FROM Products p
                JOIN Sales s ON p.ProductID = s.ProductID
                GROUP BY p.ProductID
                ORDER BY TotalSold DESC
                LIMIT 5""";
        return processGettingQuery(connection, query);
    }


    /**Display the distribution of the total number of sales by dates (Task II, V.0)*/
    public static ResultSet salesDateDistribution(Connection connection) throws SQLException {
        String query = """
                SELECT SaleDate, SUM(QuantitySold) as TotalSales
                FROM Sales
                GROUP BY SaleDate;
                """;
        return processGettingQuery(connection, query);
    }

    /**Display the top 5 dates on which the largest number of products were sold (Task II V.1)*/
    public static ResultSet topSalesDays(Connection connection) throws SQLException{
        String query = """
                SELECT SaleDate, SUM(QuantitySold) as TotalSales
                FROM Sales
                GROUP BY SaleDate
                ORDER BY TotalSales DESC
                LIMIT 5;
                """;
        return processGettingQuery(connection, query);
    }

    /**Display the average number of items sold per day (Task II V. 2) */
    public static ResultSet getAverageSales(Connection connection) throws SQLException{
        String query = "SELECT SUM(QuantitySold) / COUNT(DISTINCT SaleDate) as AvgSalesPerDay FROM Sales";
        return processGettingQuery(connection, query);

    }
}
