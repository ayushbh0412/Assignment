package model;

import db.DbConnection;
import db.DbOperation;

import java.sql.Connection;

public class InventoryControl {
    public static void main(String[] args) throws InterruptedException {
        DbConnection dbConn = new DbConnection();
        DbOperation dbTask = new DbOperation();
        Connection conn = dbConn.connect();
        if (conn == null) {
            System.out.println("Exiting...");
            return;
        }
        Thread t1 = new Thread(() -> {
            try {
                dbTask.retrieveItems(conn);
            } catch (InterruptedException e) {
                System.out.println("Retrieving thread interrupted");
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                dbTask.taxItems();
            } catch (InterruptedException e) {
                System.out.println("Taxing items thread interrupted");
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        printItem(dbTask);
    }

    private static void printItem(DbOperation dbTask) {
        System.out.println("\nAll the items are listed below");
        for (Item i : dbTask.getTaxedItems())
            System.out.println(i.toString() + "\n\n");
    }
}
