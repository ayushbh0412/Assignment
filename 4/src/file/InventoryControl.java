package file;

import java.sql.Connection;
public class InventoryControl 
{	
	public static void main(String[] args) throws InterruptedException 
    {
		DbCon dbTask=new DbCon();
		Connection conn=dbTask.connect();
		if(conn==null) 
        {
			System.out.println("Exiting...");
			return;
		}		
		Thread t1=new Thread(new Runnable() 
        {			
			@Override
			public void run()   
            {
				try 
                {
					dbTask.retriveItems(conn);
				} 
                catch (InterruptedException e) 
                {
					System.out.println("Retrieving thread interuppted");
				}
				
			}
		});
		Thread t2=new Thread(new Runnable() 
        {			
			@Override
			public void run()
            {
				try 
                {
					dbTask.taxItems();
				} 
                catch (InterruptedException e) 
                {
					System.out.println("Taxing items thread interuppted");
				}
			}
		});
		 t1.start(); 
	     t2.start(); 
	     t1.join(); 
	     t2.join(); 
	     printitem(dbTask);  
    }
	
	private static void printitem(DbCon dbTask) 
    {
		System.out.println("\nAll the items are listed below");
		for(Item i:dbTask.getTaxedItems())
			System.out.println(i.toString()+"\n\n");
	}
}
