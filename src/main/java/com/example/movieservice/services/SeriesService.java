package com.example.movieservice.services;

import com.example.movieservice.entities.Series;
import com.example.movieservice.repositories.RatingRepository;
import com.example.movieservice.repositories.SeriesRepository;
import com.example.movieservice.utils.SeriesImp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bkariuki
 */
@Service
public class SeriesService implements SeriesImp {
    private final SeriesRepository seriesRepository;
    private final RatingRepository RatingRepository;

    public SeriesService(SeriesRepository seriesRepository, RatingRepository ratingRepository) {
        this.seriesRepository = seriesRepository;
        RatingRepository = ratingRepository;
    }

    @Override
    public Series createSeries(Series series) {
        try {
            seriesRepository.save(series);
            return series;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteSeries(Long id) {
        try {
            seriesRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Series> allSeries() {
        try {
            List series = seriesRepository.findAll();
            return series;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Series> findSeriesByStatus(Boolean status) {
        try {
            List series = seriesRepository.findAllByStatus(status);
            return series;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Series> findSeriesByRating(Integer rating) {
        try {
            List series = seriesRepository.findAllByRating(rating);
            return series;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Series> findSeriesByComment(String comment) {
        try {
            List series = seriesRepository.findAllByComment(comment);
            return series;
        } catch (Exception e) {
            throw e;
        }
    }
}
