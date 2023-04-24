import DatabaseOperations.ConstantsContainer;
import DatabaseOperations.DataReader;
import DatabaseOperations.DatabaseFunctions;

import java.io.IOException;
import java.sql.*;

public class Main {


    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = DatabaseFunctions.getProjectDatabaseConnection();
        DataReader.fillDatabase(conn);


    }
}