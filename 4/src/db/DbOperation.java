package db;

import model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbOperation {
    ArrayList<Item> retrievedItems;
    ArrayList<Item> taxedItems;

    public DbOperation() {
        retrievedItems = new ArrayList<>();
        taxedItems = new ArrayList<>();
    }

    public ArrayList<Item> getTaxedItems() {
        return taxedItems;
    }

    public void retrieveItems(Connection conn) throws InterruptedException {
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("Select * from Item");
            while (result.next()) {
                synchronized (this) {
                    Item item = new Item();
                    item.setName(result.getString("name"));
                    System.out.println("Thread1: Retrieving item " + item.getName() + " data from the db");
                    item.setType(result.getInt("type"));
                    item.setPrice(result.getDouble("price"));
                    item.setQuantity(result.getInt("quantity"));
                    retrievedItems.add(item);
                    notify();
                    Thread.sleep(500);
                    wait();
                }
            }
            conn.close();
            statement.close();
            result.close();
        } catch (SQLException e) {
            System.out.println("Error in Executing sql Query");
        }
    }

    public void taxItems() throws InterruptedException {
        int i = 0;
        while (true) {
            synchronized (this) {
                wait(2000);
                new Item();
                Item item;
                try {
                    item = retrievedItems.get(i++);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("All items saved to other collection!");
                    break;
                }
                item.setTax();
                taxedItems.add(item);
                notify();
                Thread.sleep(500);
            }
        }
    }
}
