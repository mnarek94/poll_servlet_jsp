package dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private static DBConnectionProvider dbConnectionProvider;
    private final String DB_URL = "jdbc:mysql://localhost:3306/poll";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "root";

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static  DBConnectionProvider getDbConnectionProvider() {
        if (dbConnectionProvider == null) {
            dbConnectionProvider = new DBConnectionProvider();
        }
        return dbConnectionProvider;
    }
    public Connection getConnection(){
        try {

            return DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
