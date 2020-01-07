package file;

import java.io.*;
import java.util.*;

public class Menu
{	
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	ArrayList<Student> students;  
    Menu()
    {
		 students=new SerializeTask().read();
		 if(students==null)
			students=new ArrayList<>(); 
	} 
	public void displayMenu() 
    {
		System.out.println("\n\nENTER THE CHOICE \n1.ADD DETAILS\n"+
				"2.DISPLAY DETAILS\n"+
				"3.DELETE DETAILS\n"+
				"4.SAVE DETAILS\n"+
				"5.EXIT");
		try 
        {
			SubMenu(Integer.parseInt(br.readLine()));
		} 
         catch (NumberFormatException e) 
        {
			System.out.println("Not a number");
		}
        catch (IOException e) 
        {
			System.out.println("Choice Input Error");
		}
		displayMenu();
	}
	
	private void SubMenu(int choice) 
    {
		switch(choice) 
        {
			case 1: addUser();
				    break;
			case 2: displayUser();
				    break;
			case 3: deleteUser();
				    break;
			case 4: saveUser();
				    break;
			case 5: System.exit(1);
                    break;
			default: System.out.println("Invalid Choice");
		}
	}

	private void saveUser() 
    {
		SerializeTask task=new SerializeTask();
		task.write(students);
	}

	private void deleteUser() 
    {
		int roll=0;
		System.out.println("ENTER THE ROLL NUMBER TO BE DELETED");
		try 
        {
			roll=Integer.parseInt(br.readLine());
			if(deleteStudent(roll)==true)
				System.out.println("Student with roll no="+ roll +"successfully deleted\n");
			else
				System.out.println("Student with roll no="+ roll+"does not exist");
		} 
        catch (NumberFormatException e) 
        {
			System.out.println("Choice not a number");
			deleteUser();
		} 
        catch (IOException e) 
        {
			System.out.println("Choice input error");
			deleteUser();
		}	
	}
	

	private void displayUser() 
    {		
		int choice=0;
		System.out.println("CHOOSE THE SORTING CRITERIA"
				+ "\n1.DEFAULT"
				+ "\n2.BY NAME"
				+ "\n3.BY AGE"
				+ "\n4.BY ROLL NO."
				+ "\n5.BY ADDRESS");
		try 
        {
			choice=Integer.parseInt(br.readLine());
			switch(choice) 
            {
    			case 1: displayStudentsInfo();
    				    break;
    			case 2: Collections.sort(students,Comparator.comparing(Student::getName).thenComparingInt(Student::getRoll));
    					displayStudentsInfo();
    				    break;
    			case 3: Collections.sort(students,Comparator.comparingInt(Student::getAge).thenComparing(Student::getName));
    					displayStudentsInfo();
    				    break;
    			case 4: Collections.sort(students,Comparator.comparingInt(Student::getRoll).thenComparing(Student::getName));
    					displayStudentsInfo();
    				    break;
    			case 5: Collections.sort(students,Comparator.comparing(Student::getAddress).thenComparing(Student::getName));
    					displayStudentsInfo();
    				    break;
			    default: System.out.println("Invalid Choice");
						displayUser();
			}
		} 
        catch (NumberFormatException e) 
        {
			System.out.println("Choice not a number");
			displayUser();
		} 
        catch (IOException e) 
        {
			System.out.println("Choice input error");
			displayUser();
		}		
	}
	
	private void addUser() 
    {
		Student student =new Student();
		String name,addr;
		int age,roll;
		ArrayList<Course>courses;
		while((name=getName())==null);
		    student.setName(name);
		while((age=getAge())==-1);
		    student.setAge(age);
		while((roll=getRoll())==-1);
		    student.setRoll(roll);
		while((addr=getAddress())==null);
		    student.setAddress(addr);
		while((courses=getCourses())==null);
		    student.setEnrolledCourses(courses);		
		students.add(student);
		Collections.sort(students,Comparator.comparing(Student::getName).thenComparingInt(Student::getRoll));
	}
	
	private String getName() 
    {
		String name=null;
		System.out.print("FULL NAME:");
		try 
        {
			name=br.readLine();
			if(validateName(name)==false) 
				return null;			
		} 
        catch (IOException e) 
        {
			System.out.println("Name input error");
			return null;
		}		
		return name;
	}
	private String getAddress() 
    {    
		String addr=null;
		System.out.print("ADDRESS:");
		try 
        {
			addr=br.readLine();
			if(validateAddress(addr)==false) 
				return null;			
		} 
        catch (IOException e) 
        {
			System.out.println("Address input error");
			return null;
		}
		return addr;
	}	
	private int getAge() 
    {
		int age=0;
		System.out.print("AGE:");
		try 
        {
			age=Integer.parseInt(br.readLine());
			if(validateAge(age)==false) 
				return -1;
			
		} 
        catch (IOException e) 
        {
			System.out.println("age input error");
			return -1;
		}
        catch (NumberFormatException e) 
        {
			System.out.println("Not a number");
			return -1;
		}		
		return age;
	}
	private int getRoll() 
    {
		int roll=0;
		System.out.print("ROLL NUMBER:");
		try 
        {
			roll=Integer.parseInt(br.readLine());
			if(validateRoll(roll)==false) 
				return -1;			
		} 
        catch (IOException e) 
        {
			System.out.println("Roll number input error");
			return -1;
		}
        catch (NumberFormatException e) 
        {
			System.out.println("Not a number");
			return -1;
		}		
		return roll;
	}
	
	private ArrayList<Course> getCourses()
    {
		System.out.println("SELECT THE COURSES(>=4)"
				+ "\n1. A\t2. B\t3. C\t4. D\t5. E\t6. F");
		ArrayList<Course> courses=new ArrayList<Course>();
		int type;
		try 
        {
			for(int i=0;i<4;i++) 
            {
				System.out.print("COURSE"+(i+1)+":");
				type=Integer.parseInt(br.readLine());
				if(type<1||type>6) 
                {
					System.out.println("Invalid Course");
					i--;
					continue;
				}
				if(validateCourse(courses,Course.available.get(type-1))==false)
					i--;
				else
					courses.add(new Course(Course.available.get(type-1)));
			}
			String choice=null;
			System.out.print("WANT TO ENROLL MORE?(y/n):");
			choice=br.readLine();
			if(choice.toLowerCase().equals("n"))
				return courses;
			else 
            {				
				int limit=0;
				while(limit!=1&&limit!=2) 
                {
					System.out.print("ENTER THE NUMBER OF COURSES:");
					limit=Integer.parseInt(br.readLine());
				}
				for(int i=1;i<=limit;i++) 
                {
					System.out.print("COURSE"+(4+i)+":");
					type=Integer.parseInt(br.readLine());
					if(type<1||type>6) 
                    {
						System.out.println("Invalid Course");
						i--;
						continue;
					}
					if(validateCourse(courses,Course.available.get(type-1))==false)
						i--;
					else
						courses.add(new Course(Course.available.get(type-1)));
				}
			}
		}
        catch(IOException e) 
        {
			System.out.println("Course Input Error");
			return null;
		}
        catch(NumberFormatException e) 
        {
			System.out.println("Input is not a number..Enter all the courses again!\n");
			courses.clear();
			return null;
		}		
		return courses;
	}
	private boolean validateName(String name) 
    {
		if(name.equals("")) 
        {
			System.out.println("Name cannot be blank");
			return false;
		}
		return true;
	}
	private boolean validateAddress(String addr) 
    {
		if(addr.equals("")) 
        {
			System.out.println("Address cannot be blank");
			return false;
		}
		return true;
	}	
	private boolean validateAge(int age) 
    {
		if(age==0) 
        {
			System.out.println("Age cannot be 0");
			return false;
		}
		return true;
	}
	
	private boolean validateRoll(int roll) 
    {
		for(Student s:students) 
			if(s.getRoll()==roll) 
            {
				System.out.println("Student with rollno= "+roll+" already exist\n");
				return false;
			}
		return true;
	}
	
	private boolean validateCourse(ArrayList<Course>courses,String type) 
    {
		for(Course c:courses)
			if(c.toString().equals(type)) 
            {
				System.out.println("Course Aready entered! Please enter another course");
				return false;
			}
		return true;
	}
	
	private boolean deleteStudent(int roll) 
    {
		for(Student s:students)
			if(s.getRoll()==roll) 
            {
				students.remove(s);
				return true;
			}
		return false;
	}
	private void displayStudentsInfo() 
    {
		if(students.isEmpty()) 
        {
			System.out.println("Nothing to display");
			return;
		}
        System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.print("NAME \t\t ROLL NO. \t AGE \t ADDRESS \t\t COURSES ENROLLED\n");
        System.out.println("------------------------------------------------------------------------------------------------------");
		for(Student s:students) 
        {
			System.out.print(s.getName() + "\t\t " + s.getRoll() + "\t\t " + s.getAge() +"\t " + s.getAddress() +"\t\t\t ");
			for(Course c:s.getEnrolledCourses()) 
            {
				System.out.print(c+",");
			}
			System.out.print("\t\n");
		}
	}
}