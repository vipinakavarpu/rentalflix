package io.egen.rentalflix;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit test cases for MovieService
 */
public class MovieServiceTest {
	private static MovieService ms;
	@BeforeClass
	public static void init()
	{
		ms= new MovieService();
	}
	@Test
	public void notNullTest_For_FindAll()
	{
		
		assertNotEquals(null, ms.findAll());
	}
	@Test
	public void positiveTest_For_FindByName()
	{
		
		assertEquals("Titanic", ms.findByName("Titanic").get(0).getTitle());
	}
	@Test
	public void notNullTest_For_FindByName()
	{
		assertNotEquals(null, ms.findByName("test"));
	}
	@Test
	public void notEmptyListTest_For_FindByName()
	{
		assertNotEquals(0, ms.findByName(""));
	}
	@Test
	public void createMovieTest()
	{
		int sizeBeforeInsertion=ms.findAll().size();
		ms.create(new Movie(4, "Lion King", 1998, "English"));
		int sizeAfterInsertion=ms.findAll().size();
		assertEquals(new Integer(sizeBeforeInsertion+1), new Integer(sizeAfterInsertion));
	}
	@Test
	public void updateMovie_Should_Return_Movie_Test()
	{
		Movie m=new Movie(3, "The Lion King", 1999, "English");
		assertEquals(m, ms.update(m));
	}
	@Test(expected=IllegalArgumentException.class)
	public void updateMovie_Should_Throw_IllegalArgument_Test()
	{
		Movie m=new Movie(5, "The Lion King", 1999, "English");
		ms.update(m);
	}
	@Test
	public void deleteMovie_Should_Reduce_SizeofList_ByOneTest()
	{
		int sizeBeforeDeletion=ms.findAll().size();
		ms.delete(1);
		assertEquals(new Integer(sizeBeforeDeletion-1), new Integer(ms.findAll().size()));
	}
	@Test(expected=IllegalArgumentException.class)
	public void deleteMovie_Should_Throw_IllegalArgumentException_Test()
	{
		ms.delete(10);
	}
	@Test
	public void rentMovie_Should_Return_True()
	{
		ms.rentMovie(3, "Babu");
	}
	@Test
	public void rentMovie_Should_Return_False()
	{
		ms.rentMovie(5, "Steve");
	}
	@Test(expected=IllegalArgumentException.class)
	public void rentMovie_Should_Throw_IllegalArgumentException_Test()
	{
		ms.rentMovie(2, "Vipin");
	}
}
