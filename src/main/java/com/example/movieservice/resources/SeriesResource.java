package com.example.movieservice.resources;

import com.example.movieservice.entities.Movie;
import com.example.movieservice.entities.Series;
import com.example.movieservice.repositories.SeriesRepository;
import com.example.movieservice.services.SeriesService;
import com.example.movieservice.utils.SeriesImp;
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
public class SeriesResource {
    private final SeriesRepository seriesRepository;
    private final SeriesImp seriesImp;

    public SeriesResource(SeriesRepository seriesRepository, SeriesImp seriesImp) {
        this.seriesRepository = seriesRepository;
        this.seriesImp = seriesImp;
    }


    @GetMapping("all-series")
    public ResponseEntity getMovies() {
        ResponseWrapper response = new ResponseWrapper();
        List data = seriesImp.allSeries();
        response.setData(data);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/create-series")
    public ResponseEntity createMovie(@RequestBody Series series) {
        ResponseWrapper response = new ResponseWrapper();
        Series exists = seriesRepository.findByTitle(series.getTitle());
        if (Objects.nonNull(exists)) {
            response.setCode(409);
            response.setMessage("movie already exists");
            return ResponseEntity.status(409).body(response);
        }
        Series data = seriesImp.createSeries(series);
        response.setCode(201);
        response.setData(data);
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/update-series/{id}")
    public ResponseEntity updateMovie(@RequestBody MovieWrapper wrapper, @PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        Series series = seriesRepository.findById(id).get();
        if (Objects.isNull(series)) {
            response.setCode(409);
            response.setMessage("movie id does nt exist");
            return ResponseEntity.status(409).body(response);
        }
        if (wrapper.getTitle() != null) {
            series.setTitle(wrapper.getTitle());
        }
        if (wrapper.getDescription() != null) {
            series.setDescription(wrapper.getDescription());
        }
        series = seriesRepository.save(series);
        response.setData(series);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-series{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id) {
        ResponseWrapper response = new ResponseWrapper();
        Series exists = seriesRepository.getOne(id);
        if (Objects.isNull(exists)) {
            response.setCode(409);
            response.setMessage("movie id does nt exist");
            return ResponseEntity.status(409).body(response);
        }
        seriesImp.deleteSeries(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-series-by-status/{status}")
    public ResponseEntity getMoviesByStatus(@PathVariable Boolean status) {
        ResponseWrapper response = new ResponseWrapper();
        List data = seriesImp.findSeriesByStatus(status);
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-series-by-rating/{rating}")
    public ResponseEntity getMoviesByRating(@PathVariable Integer rating) {
        ResponseWrapper response = new ResponseWrapper();
        List data = seriesImp.findSeriesByRating(rating);
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-series-by-comment/{comment}")
    public ResponseEntity getMoviesByComment(@PathVariable String comment) {
        ResponseWrapper response = new ResponseWrapper();
        List data = seriesImp.findSeriesByComment(comment);
        response.setData(data);
        return ResponseEntity.ok(response);
    }
}
