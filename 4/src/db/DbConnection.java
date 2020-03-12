package db;
/*
 * DbName: Inventory
 * TableName: Item
 * username:ayush
 * password:password
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver loading error");
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inventory", "ayush", "password");
            System.out.println("Db connected");
        } catch (SQLException e) {
            System.out.println("Error in getting connection");
        }
        return conn;
    }
}