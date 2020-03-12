package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DbTest {
    DbConnection dbTask;

    @BeforeEach
    void setUp() {
        dbTask = new DbConnection();
    }

    @Test
    void testConnect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR");
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Inventory", "root", "ayushbh123");
            System.out.println("CONNECTED");
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
        assertEquals(conn, dbTask.connect());
    }
}

