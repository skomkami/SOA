package app.dao;

import app.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesDAO{

    public ArrayList<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {return movies;}
    public void addMovie(Movie m) {movies.add(m);}
    public Movie find(int id) {
        return movies
                .stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void modifyMovie(Movie m) {

    }

}
