package com.example.movieservice.utils;

import com.example.movieservice.entities.Movie;
import com.example.movieservice.wrappers.MovieWrapper;

import java.util.List;

/**
 * @author bkariuki
 */
public interface MovieImpl {
    Movie createMovie(Movie movie);

    void deleteMovie(Long id);

    List<Movie> allMovies();

    List<Movie> findMoviesByStatus(Boolean status);

    List<Movie> findMoviesByRating(Integer rating);

    List<Movie> findMoviesByComment(String comment);
}
