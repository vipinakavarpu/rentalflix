package io.egen.rentalflix;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit test cases for MovieService
 */
public class MovieServiceTest {
	MovieService ms;
	@BeforeClass
	public void init()
	{
		ms= new MovieService();
		System.out.println("ms intialized");
	}
	@Test
	public void notNullTestforFindAll()
	{
		
		assertNotEquals(null, ms.findAll());
	}
	@Test
	public void positiveTestForFindByName()
	{
		
		assertEquals("Titanic", ms.findByName("Titanic").get(0).getTitle());
	}
	@Test
	public void notNullTestForFindByName()
	{
		assertNotEquals(null, ms.findByName("test"));
	}
	@Test
	public void notEmptyList_For_FindByName()
	{
		
	}
	
}
