package file;

import java.io.*;
import java.util.ArrayList;

public class SerializeTask 
{
	final String file="students.txt";
	public void write(ArrayList<Student> students) 
    {
		try 
        {
			FileOutputStream fo=new FileOutputStream(file);
			ObjectOutputStream out=new ObjectOutputStream(fo);
			out.writeObject(students);
			System.out.println("CHANGES SAVED SUCCESSFULLY");
			out.close();
			fo.close();
		}
        catch (IOException e) 
        {
			System.out.println("File I/O error");
		}	
	}

    @SuppressWarnings("unchecked")
	public ArrayList<Student> read() 
    {
		ArrayList<Student> students=null;
		try 
        {
			FileInputStream fi=new FileInputStream(file);
			ObjectInputStream in=new ObjectInputStream(fi);
			students=(ArrayList<Student>)in.readObject(); 
			in.close();
			fi.close();
		} 
        catch (FileNotFoundException e) 
        {
			System.out.println("File"+file+" cannot be located");
		}
        catch (IOException e) 
        {
			System.out.println("File I/O error");
		} 
        catch (ClassNotFoundException e) 
        {
			e.printStackTrace();
		}
		return students;
	}
}
