package file;

import java.text.DecimalFormat;

public class Item 
{		
		private String name;
		private String type;
		private double price;
		private int quantity;
		private double tax;
		static String types[]= {"raw","manufactured","imported"};
		DecimalFormat df=new DecimalFormat("#,###.##");		
		public String getName() 
        {
			return name;
		}
		public void setName(String name) 
        {
			this.name = name;
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
			}
            catch(Exception e) 
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
		public void setTax() 
        {
			if(type.equals("raw"))
				tax=price*0.125;
			else if(type.equals("manufactured"))
				tax=price*0.125+0.02*(price+price*0.125);
			else if(type.equals("imported")) 
            {
				double surcharge;
				double importDuty=0.10*price;
				double finalCost=importDuty+price; 
				if(finalCost<=100)
					surcharge=5;        
				else if(finalCost>100 && finalCost<=200)
					surcharge=10;
				else
					surcharge=0.05*finalCost;
				tax=surcharge+importDuty;
			}
		}
		public double getTax() 
        {
			return tax;
		}
		private double getFinalPricePerItem() 
        {
			return price+tax;
		}
		private double getTotalPrice() 
        {
			return getFinalPricePerItem()*quantity;
		}
		public String toString() 
        {
			return ("\t*************************************************\n\t\tNAME:             " + name +
			   			 "\n\t\tTYPE:             " + type + 
			     		 "\n\t\tPRICE:            " + price + 
			   			 "\n\t\tQUANTITY:         " + quantity+
			   			 "\n\t\tSALES TAX:        "+ tax+
			   			 "\n\t\tFINAL PRICE/ITEM: "+ getFinalPricePerItem()+   
			   			 "\n\t\tTOTAL PRICE :      "+ getTotalPrice() + "\n\t*************************************************");     
		}
}

