package file;

/*
 * DbName: Inventory
 * TableName: Item
 * username:root
 * password:ayushbh123
 */
import java.util.ArrayList;
import java.sql.*;

public class DbCon
{
	ArrayList<Item> retrievedItems;
	ArrayList<Item> taxedItems;	
	DbCon()
    {
		retrievedItems=new ArrayList<>();
		taxedItems=new ArrayList<>();
	}	
	public ArrayList<Item> getTaxedItems() 
    {
		return taxedItems;
	}
	public Connection connect()
    {  
		Connection conn=null;
		try 
        {
			Class.forName("com.mysql.jdbc.Driver");
		} 
        catch (ClassNotFoundException e) 
        {
			System.out.println("JDBC Driver loading error");
		}		
		try 
        {
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Inventory","root", "ayushbh123");
			System.out.println("Db connected");
		} 
        catch (SQLException e) 
        {
			System.out.println("Error in getting connection");
		}
		return conn;
	}	
	public void retriveItems(Connection conn) throws InterruptedException 
    {  
		Statement statement=null;
		ResultSet result=null;
		try 
        {
			statement=conn.createStatement();
			result=statement.executeQuery("Select * from Item");	
			while(result.next()) 
            {
				synchronized(this) 
                {					
					Item item=new Item();
					item.setName(result.getString("name"));
					System.out.println("Thread1: Retrieving item "+item.getName()+" data from the db");
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
		} 
        catch (SQLException e) 
        {
        	System.out.println("Error in Executing sql Query");
		}
	}
	public void taxItems() throws InterruptedException 
    {  
		int i=0;
		while(true) 
        {
            synchronized (this) 
            {
				wait(2000);
				Item item=new Item();
				try 
                {
    				item=retrievedItems.get(i++);
				}   
                catch(IndexOutOfBoundsException e) 
                {
					System.out.println("All items saved to other collection!");
					break;
				}
				System.out.println("Thread2: Item "+item.getName()+" retrieved,taxed and saved");
				item.setTax();
				taxedItems.add(item);
				notify();
				Thread.sleep(500);
			}
		}
	}
}
