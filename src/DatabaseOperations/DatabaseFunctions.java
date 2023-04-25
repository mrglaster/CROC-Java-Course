package DatabaseOperations;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DatabaseFunctions {

    /**Makes a connection to the project database*/
    public static  Connection getProjectDatabaseConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(ConstantsContainer.databaseFilePath);
            return conn;

        } catch (SQLException e) {
            throw new SQLException("Something went wrong during the database connection!");
        }
    }


    /**Returns list of tables in the database*/
    public static ArrayList<String> getTablesList(Connection connection) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master  WHERE type='table'");
        while (rs.next()) {
            result.add(rs.getString("name"));
        }
        stmt.close();
        return result;
    }


    /**Checks if some table exists in the project database*/
    public static boolean tableExists(Connection conn, String tableName) throws SQLException {
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+tableName+"'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return  rs.getString(1) != null;

    }

    /**Clears all the data from the selected table if it exists*/
    public static void clearTable(Connection conn, String tableName) throws SQLException {
        if (tableExists(conn, tableName)){
            String query = "DELETE FROM " + tableName;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } else {
            throw new SQLException("Table " + tableName + "doesn't exist!");
        }

    }

    /**Clears all the tables in the project database*/
    public static void clearTables(Connection conn) throws SQLException {
        ArrayList<String> tables = getTablesList(conn);
        for (String i:tables){
            if (!Objects.equals(i, ConstantsContainer.tableToIgnore)){
                clearTable(conn, i);
            }
        }

    }


}
