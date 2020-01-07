package file;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test 
{
	Student s;
	@BeforeEach
	void setUp() throws Exception 
	{
		s=new Student();
	}

	@Test
	void testGetName() 
	{
		s.setName("ayush");
		assertEquals("ayush", s.getName());
	}

	@Test
	void testGetAge() 
	{
		s.setAge(20);
		assertEquals(20, s.getAge());
	}

	@Test
	void testGetAddress() 
	{	
		s.setAddress("10/33 VDN");
		assertEquals("10/33 VDN", s.getAddress());
	}

	@Test
	void testGetRoll() 
	{
		s.setRoll(131);
		assertEquals(131, s.getRoll());
	}

	@Test
	void testGetEnrolledCourses() 
	{
		ArrayList<Course> courses=new ArrayList<>();
		courses.add(new Course("A"));
		courses.add(new Course("C"));
		courses.add(new Course("B"));
		courses.add(new Course("D"));
		s.setEnrolledCourses(courses);
		assertEquals(courses, s.getEnrolledCourses());
	}

}
