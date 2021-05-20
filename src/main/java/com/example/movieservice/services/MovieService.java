package com.example.movieservice.services;

import com.example.movieservice.entities.Movie;
import com.example.movieservice.repositories.MovieRepository;
import com.example.movieservice.repositories.RatingRepository;
import com.example.movieservice.utils.MovieImpl;
import com.example.movieservice.wrappers.MovieWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bkariuki
 */
@Service
@Transactional
public class MovieService implements MovieImpl {

    private final MovieRepository movieRepository;
    private final RatingRepository RatingRepository;

    public MovieService(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        RatingRepository = ratingRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        try {
            movieRepository.save(movie);
            return movie;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteMovie(Long id) {
        try {
            movieRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> allMovies() {
        try {
            List movies = movieRepository.findAll();
            return movies;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Movie> findMoviesByStatus(Boolean status) {
        try {
            List movies = movieRepository.findAllByStatus(status);
            return movies;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Movie> findMoviesByRating(Integer rating) {
        try {
            List movies = movieRepository.findAllByRating(rating);
            return movies;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Movie> findMoviesByComment(String comment) {
        try {
            List movies = movieRepository.findAllByComment(comment);
            return movies;
        } catch (Exception e) {
            throw e;
        }
    }
}
