package io.egen.rentalflix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Service implementing IFlix interface
 * You can use any Java collection type to store movies
 */
public class  MovieService implements IFlix {
	private ArrayList<Movie> movieList;
	private HashMap<Integer, String> rentals;
	private HashSet<Integer> MovieId;
	public MovieService() 
	{
		this.rentals=new HashMap<>();
		this.movieList=new ArrayList<Movie>();
		this.MovieId=new HashSet<>();
		movieList.add(new Movie(1, "Titanic", 1993, "English"));
		movieList.add(new Movie(2, "Life of Pie",2014, "English"));
		movieList.add(new Movie(3, "Kung Fu Panda", 2015, "English"));
		MovieId.add(1);
		MovieId.add(2);
		MovieId.add(3);
		rentals.put(2, "vipin");
	}
	@Override
	public synchronized List<Movie> findAll() {
		return movieList;
	}

	@Override
	public synchronized List<Movie> findByName(String name) {
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
	public synchronized Movie create(Movie movie) {
		movieList.add(movie);
		MovieId.add(movie.getId());
		return movie;
	}

	@Override
	public synchronized Movie update(Movie movie) {
		// TODO Auto-generated method stub
		Iterator<Movie> it=movieList.iterator();
		int i=0;
		while(it.hasNext())
		{
			Movie m=it.next();
			if(m.getId()==movie.getId())
			{
				movieList.set(i, movie);
				return movie;
			}
			i++;
		}
		throw new IllegalArgumentException("Invalid movie provided for update");
	}

	@Override
	public synchronized Movie delete(int id) {
		Iterator<Movie> it=movieList.iterator();
		
		while(it.hasNext())
		{
			Movie m=it.next();
			if(m.getId()==id)
			{
				it.remove();
				MovieId.remove(m.getId());
				return m;
			}
		}
		throw new IllegalArgumentException("Invalid id provided for delete"); 
	}

	@Override
	public synchronized boolean rentMovie(int movieId, String user) {
		if(rentals.containsKey(new Integer(movieId)))
		{
			throw new IllegalArgumentException("Movie already rented");
		}
		if(MovieId.contains(movieId))
		{	
			rentals.put(movieId, user);
			return true;
		}
		return false;
	}

}
