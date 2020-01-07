package file;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test
{
	Item itemTest;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		itemTest=new Item();
	}

	@Test
	void testGetName() 
	{
		itemTest.setName("pen");
		assertEquals("pen", itemTest.getName());
	}
	
	@Test
	void testGetType() 
	{
		itemTest.setType(1);
		assertEquals("raw", itemTest.getType());
		
		itemTest.setType(2);
		assertEquals("manufactured", itemTest.getType());
		
		itemTest.setType(3);
		assertEquals("imported", itemTest.getType());
	}

	@Test
	void testGetPrice() 
	{
		itemTest.setPrice(100);
		assertEquals(100, itemTest.getPrice());
	}

	@Test
	void testGetQuantity() 
	{
		itemTest.setQuantity(10);
		assertEquals(10, itemTest.getQuantity());
	}
	@Test
	void testGetTax() 
	{
		itemTest.setPrice(100);
		itemTest.setType(1);  
		assertEquals(12.5, itemTest.getTax());
		
		itemTest.setPrice(100);
		itemTest.setType(2); 
		assertEquals(14.75, itemTest.getTax());
		
		itemTest.setPrice(100);
		itemTest.setType(3); 
		assertEquals(20.0, itemTest.getTax());
	}
}
