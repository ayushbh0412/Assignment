package file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Node 
{
	private int id;
	private String name;
	private HashMap<String,String> info;
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public HashMap<String, String> getInfo() 
	{
		return info;
	}
	public void setInfo(HashMap<String, String> info) 
	{
		this.info = info;
	}
	@Override
	public int hashCode() 
	{
		return id;
	}
}

public class Main 
{	
	private static int getIntegerInput(BufferedReader br) 
	{
		int num=0;
		try 
		{
			num=Integer.parseInt(br.readLine());
		} 
		catch (NumberFormatException e) 
		{
			System.out.println("Not a number!...Exiting..! Run again!");
			System.exit(1);
			
		}
		catch (IOException e) 
		{
			System.out.println("Number input error!...Exiting..! Run again!");
			System.exit(2);	
		}
		return num;
	}
	
	private static String getStringInput(BufferedReader br) 
	{
		String data=null;
		try 
		{
			data=(br.readLine());
		} 
		catch ( IOException e) 
		{
			System.out.println("String input error!...Exiting!.. Run again!");
			System.exit(2);	
		}
		return data;
	}

	public static void main(String[] args) 
	{
			Graph graph=new Graph();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while(true) 
		{
			System.out.println("\nENTER CHOICE\n"
					+ "1.ADD A NODE\n"
					+ "2.ADD A DEPENDENCY\n"
					+ "3.DELETE A NODE\n"
					+ "4.DELETE A DEPENDENCY\n"
					+ "5.GET IMMEDIATE NODE\n"
					+ "6.GET IMMEDIATE PARENT\n"
					+ "7.GET ANCESTORS\n"
					+ "8.GET DESENDENTs\n"
					+ "9.EXIT");
			int choice=getIntegerInput(br);
			switch(choice) 
			{
				case 1: System.out.println("ENTER NODE ID");
						int id=getIntegerInput(br);
						System.out.println("ENTER NODE NAME");
						String name=getStringInput(br);
						System.out.println("ENTER KEY");
						String key=getStringInput(br);
						System.out.println("ENTER INFO");
						String value=getStringInput(br);	
						graph.addNode(id, name, key, value);		
						break;
				case 2:	System.out.println("ENTER PARENT NODE ID");
					   	int pid=getIntegerInput(br);
				   		System.out.println("ENTER CHILD NODE ID");
				   		int cid=getIntegerInput(br);
				   		graph.addDependency(pid, cid);
						break;
				case 3: System.out.println("ENTER NODE ID");
						id=getIntegerInput(br);
						graph.deleteNode(id);
						break;
				case 4: System.out.println("ENTER PARENT NODE ID");
		   				pid=getIntegerInput(br);
		   				System.out.println("ENTER CHILD NODE ID");
		   				cid=getIntegerInput(br);
		   				graph.deleteDependency(pid, cid);
						break;
				case 5: System.out.println("ENTER NODE ID");
						id=getIntegerInput(br);
						graph.getImmidiateParents(id);
						break;
				case 6: System.out.println("ENTER NODE ID");
						id=getIntegerInput(br);
						graph.getImmidiateChildren(id);
						break;
				case 7:	System.out.println("ENTER NODE ID");
						id=getIntegerInput(br);
						graph.getAncestors(id);
						break;
				case 8:	System.out.println("ENTER NODE ID");
						id=getIntegerInput(br);
						graph.getDescendents(id);
						break;
				case 9: System.exit(0);
						break;
				default: System.out.println("INVALID CHOICE\n");
			}
		}
	}
}