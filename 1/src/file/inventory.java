package file;

import java.io.*;
class Item{
	
	private String name;
	private String type;
	private double price;
	private int quantity;
	private double tax;
	static String types[]= {"raw","manufactured","imported"};
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String nm) 
	{
		this.name = nm;
	}
	
	public double getTax() 
	{
		return tax;
	}

	public String getType() 
	{
		return type;
	}
	
	public void setType(int i) 
	{
		try 
		{
			this.type = types[i-1];
			setTax(i);   
		}catch(Exception e) 
		{
			System.out.println("Invalid Type");
		}
	}

	public double getPrice() 
	{
		return price;
	}
	public void setPrice(double price) 
	{
		this.price = price;
	}
	public int getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	private void setTax(int type) 
	{
		if(type==1) 
			tax=price*0.125; 
		else if(type==2) 
			tax=price*0.125+0.02*(price+price*0.125);
		else if(type==3) 
		{
			double surcharge;
			double impDuty=0.10*price;
			double finalCost=impDuty+price;
			if(finalCost<=100)
				surcharge=5;        
			else if(finalCost>100 && finalCost<=200)
				surcharge=10;
			else
				surcharge=0.05*finalCost;
			tax=surcharge+impDuty;  
		}
	}
	private double getFinalPricePerItem() 
	{
		return price+tax;
	}
	private double getTotalPrice() 
	{
		return getFinalPricePerItem()*quantity;
	}
	public void printData() 
	{
		System.out.println("\t*************************************************");
		System.out.println("\t\tName:             " + name +
			   			 "\n\t\tType:             " + type + 
			     		 "\n\t\tPrice:            " + price + 
			   			 "\n\t\tQuantity:         " + quantity+
			   			 "\n\t\tSales Tax:        "+ tax+
			   			 "\n\t\tFinal Price/item: "+ getFinalPricePerItem()+   
			   			 "\n\t\tTotal Price:      "+ getTotalPrice());
		System.out.println("\t*************************************************");               
	}
}

public class inventory
{
	public static void main(String args[]) 
	{		
		String ch="y";
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int i=0;
		do 
		{
			i++;
			Item item=new Item();
			System.out.println("ENTER THE ITEM "+ i + " DETAILS");
			System.out.print("NAME \t\t");
			try 
			{
				item.setName(br.readLine());
			}catch(IOException e) {
				System.out.println("Name Input Error");
			}
			System.out.print("PRICE \t\t");
			try 
			{
				item.setPrice(Double.parseDouble(br.readLine()));
			}catch(IOException ie) {
				System.out.println("Price Input Error");
			}catch(NumberFormatException e) {
				System.out.println("Not a number");
			}
			System.out.print("QUANTITY \t");
			try 
			{
				item.setQuantity(Integer.parseInt(br.readLine()));
			}catch(IOException ie) {
				System.out.println("Quantity Input Error");
			}catch(NumberFormatException e) {
				System.out.println("Not a number");
			}
			System.out.print("CHOOSE THE TYPE:\n1. RAW\n2. MANUFACTUTED\n3. IMPORTED\n");
			try 
			{
				item.setType(Integer.parseInt(br.readLine()));
			}catch(IOException ie) {
				System.out.println("Type Input Error");
			}catch(NumberFormatException e) {
				System.out.println("Not a number");
			}
			System.out.println("\n\n\t\tSHOWING ITEM "+i+" DETAILS:");
			item.printData();
			
			System.out.print("\nDO YOU WANT TO ENTER MORE ITEM?(y/n):\t\t");
			try 
			{
				ch=br.readLine();
				if(ch.toLowerCase().equals("n")) {
					System.exit(0);
				}
			} catch (IOException e) {
				System.out.println("Invalid choice");
			}
		}while(ch.toLowerCase().equals("y"));
		
	}
}