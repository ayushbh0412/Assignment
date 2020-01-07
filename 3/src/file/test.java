package file;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test
{
	Graph graph;
	@BeforeEach
	void setUp() throws Exception 
	{
		graph=new Graph();		
	}
	@Test
	void testAddNode() 
	{
		graph.addNode(1, "a", "key", "value");
		graph.addNode(2, "b", "key", "value");
		assertEquals(2, graph.getNodes().size());
	}
	@Test
	void testAddDependency() 
	{
		graph.addNode(1, "a", "key", "value");
		graph.addNode(2, "b", "key", "value");
		graph.addDependency(1, 2);
		assertEquals(true,graph.dependencyExists(1, 2));
	}
	@Test
	void testIsDescendent() 
	{
		graph.addNode(1, "a", "key", "value");
		graph.addNode(2, "b", "key", "value");
		graph.addNode(3, "c", "key", "value");
		graph.addDependency(1, 2);
		graph.addDependency(2, 3);
		assertEquals(true, graph.isDescendent(1, 3) );
	}
}
