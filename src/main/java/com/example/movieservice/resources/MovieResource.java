package com.example.movieservice.resources;

import com.example.movieservice.entities.Movie;
import com.example.movieservice.repositories.MovieRepository;
import com.example.movieservice.services.MovieService;
import com.example.movieservice.utils.MovieImpl;
import com.example.movieservice.wrappers.MovieWrapper;
import com.example.movieservice.wrappers.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author bkariuki
 */
@RestController
public class MovieResource {
    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final MovieImpl movieImpl;

    public MovieResource(MovieRepository movieRepository, MovieService movieService, MovieImpl movieImpl) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.movieImpl = movieImpl;
    }

    @GetMapping("all-movies")
    public ResponseEntity getMovies() {
        ResponseWrapper response = new ResponseWrapper();
        List data = movieImpl.allMovies();
        response.setData(data);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/create-movie")
    public ResponseEntity createMovie(@RequestBody Movie movie) {
        ResponseWrapper response = new ResponseWrapper();
        Movie exists = movieRepository.findByTitle(movie.getTitle());
        if (Objects.nonNull(exists)) {
            response.setCode(409);
            response.setMessage("movie already exists");
            return ResponseEntity.status(409).body(response);
        }
        Movie data = movieImpl.createMovie(movie);
        response.setCode(201);
        response.setData(data);
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/update-movie/{id}")
    public ResponseEntity updateMovie(@RequestBody MovieWrapper wrapper, @PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        Movie movie = movieRepository.findById(id).get();
        if (Objects.isNull(movie)) {
            response.setCode(409);
            response.setMessage("movie id does nt exist");
            return ResponseEntity.status(409).body(response);
        }
        if (wrapper.getTitle() != null) {
            movie.setTitle(wrapper.getTitle());
        }
        if (wrapper.getDescription() != null) {
            movie.setDescription(wrapper.getDescription());
        }
        movie = movieRepository.save(movie);
        response.setData(movie);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        Movie exists = movieRepository.getOne(id);
        if (Objects.isNull(exists)) {
            response.setCode(409);
            response.setMessage("movie id does nt exist");
            return ResponseEntity.status(409).body(response);
        }
        movieImpl.deleteMovie(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-movies-by-status/{status}")
    public ResponseEntity getMoviesByStatus(@PathVariable Boolean status) {
        ResponseWrapper response = new ResponseWrapper();
        List data = movieImpl.findMoviesByStatus(status);
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-movies-by-rating/{rating}")
    public ResponseEntity getMoviesByRating(@PathVariable Integer rating) {
        ResponseWrapper response = new ResponseWrapper();
        List data = movieImpl.findMoviesByRating(rating);
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-movies-by-comment/{comment}")
    public ResponseEntity getMoviesByComment(@PathVariable String comment) {
        ResponseWrapper response = new ResponseWrapper();
        List data = movieImpl.findMoviesByComment(comment);
        response.setData(data);
        return ResponseEntity.ok(response);
    }

}
