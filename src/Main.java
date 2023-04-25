import DatabaseOperations.DatabaseFiller;
import DatabaseOperations.DatabaseFunctions;
import DatabaseOperations.ShopQueries;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = DatabaseFunctions.getProjectDatabaseConnection();
        DatabaseFiller.fillDatabase(conn);
        ResultSet rs = ShopQueries.salesDateDistribution(conn);
    }
}