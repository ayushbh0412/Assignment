package file;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable 
{
	private static final long serialVersionUID = -3509975826119083103L;
	private String name;
	private int age;
	private String address;
	private int roll;
	private ArrayList<Course> enrolledCourses;
	public String getName() 
    {
		return name;
	}
	public void setName(String name1) 
    {
        name = name1;
	}
	public int getAge() 
    {
		return age;
	}
	public void setAge(int age1) 
    {
        age = age1;
	}
	public String getAddress() 
    {
		return address;
	}
	public void setAddress(String address1) 
    {
		address = address1;
	}
	public int getRoll() 
    {
		return roll;
	}
	public void setRoll(int roll1) 
    {
		roll = roll1;
	}
	public ArrayList<Course> getEnrolledCourses() 
    {
		return enrolledCourses;
	}
	public void setEnrolledCourses(ArrayList<Course> enrolledCourses1) 
    {
		enrolledCourses = enrolledCourses1;
	}
}
