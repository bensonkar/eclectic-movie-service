package com.example.movieservice.repositories;

import com.example.movieservice.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bkariuki
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByTitle(String title);

    List<Movie> findAllByStatus(Boolean status);

    List<Movie> findAllByRating(Integer rating);

    List<Movie> findAllByComment(String comment);
}
