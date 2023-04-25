import DatabaseOperations.DatabaseFiller;
import DatabaseOperations.DatabaseFunctions;
import DatabaseOperations.ResultsWriter;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = DatabaseFunctions.getProjectDatabaseConnection();
        DatabaseFiller.fillDatabase(conn);
        ResultsWriter.writeAllSolutions(conn);
        conn.close();
    }
}