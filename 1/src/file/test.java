package file;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test
{
	Item testins;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		testins=new Item();
	}

	@Test
	void testGetName() 
	{
		testins.setName("pen");
		assertEquals("pen", testins.getName());
	}
	
	@Test
	void testGetType() 
	{
		testins.setType(1);
		assertEquals("raw", testins.getType());
		
		testins.setType(2);
		assertEquals("manufactured", testins.getType());
		
		testins.setType(3);
		assertEquals("imported", testins.getType());
	}

	@Test
	void testGetPrice() 
	{
		testins.setPrice(100);
		assertEquals(100, testins.getPrice());
	}

	@Test
	void testGetQuantity() 
	{
		testins.setQuantity(10);
		assertEquals(10, testins.getQuantity());
	}
	@Test
	void testGetTax() 
	{
		testins.setPrice(100);
		testins.setType(1);  
		assertEquals(12.5, testins.getTax());
		
		testins.setPrice(100);
		testins.setType(2); 
		assertEquals(14.75, testins.getTax());
		
		testins.setPrice(100);
		testins.setType(3); ss
		assertEquals(20.0, testins.getTax());
	}
}
