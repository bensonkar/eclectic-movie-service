package com.example.movieservice.utils;

import com.example.movieservice.entities.Movie;
import com.example.movieservice.entities.Series;

import java.util.List;

/**
 * @author bkariuki
 */
public interface SeriesImp {
    Series createSeries(Series series);

    void deleteSeries(Long id);

    List<Series> allSeries();

    List<Series> findSeriesByStatus(Boolean status);

    List<Series> findSeriesByRating(Integer rating);

    List<Series> findSeriesByComment(String comment);
}
