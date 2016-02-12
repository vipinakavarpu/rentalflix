package io.egen.rentalflix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Service implementing IFlix interface
 * You can use any Java collection type to store movies
 */
public class MovieService implements IFlix {
	private ArrayList<Movie> movieList;
	private HashMap<Integer, String> rentals;
	public MovieService() 
	{
		this.rentals=new HashMap<>();
		this.movieList=new ArrayList<Movie>();
		movieList.add(new Movie(1, "Titanic", 1993, "English"));
		movieList.add(new Movie(2, "Life of Pie",2014, "English"));
		movieList.add(new Movie(3, "Kung Fu Panda", 2015, "English"));
	}
	@Override
	public List<Movie> findAll() {
		return movieList;
	}

	@Override
	public List<Movie> findByName(String name) {
		ArrayList<Movie> result=new ArrayList<Movie>();
		for(Movie m:movieList)
		{
			if(m.getTitle().equals(name))
			{
				result.add(m);
			}
		}
		return result;
	}
	
	@Override
	public Movie create(Movie movie) {
		movieList.add(movie);
		return movie;
	}

	@Override
	public Movie update(Movie movie) {
		// TODO Auto-generated method stub
		for(Movie m:movieList)
		{
			if(m.getId()==movie.getId())
			{
				movieList.set(m.getId(), movie);
				return movie;
			}
		}
		throw new IllegalArgumentException("Invalid movie provided for update");
	}

	@Override
	public Movie delete(int id) {
		Iterator<Movie> it=movieList.iterator();
		
		while(it.hasNext())
		{
			Movie m=it.next();
			if(m.getId()==id)
			{
				it.remove();
				return m;
			}
		}
		throw new IllegalArgumentException("Invalid id provided for delete"); 
	}

	@Override
	public boolean rentMovie(int movieId, String user) {
		if(rentals.containsKey(new Integer(movieId)))
		{
			throw new IllegalArgumentException("Movie already rented");
		}
		rentals.put(movieId, user);
		return true;
	}

}
