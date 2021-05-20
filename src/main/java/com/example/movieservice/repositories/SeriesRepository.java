package com.example.movieservice.repositories;

import com.example.movieservice.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bkariuki
 */
@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Series findByTitle(String title);

    List<Series> findAllByStatus(Boolean status);

    List<Series> findAllByRating(Integer rating);

    List<Series> findAllByComment(String comment);
}
