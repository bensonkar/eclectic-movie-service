package com.example.movieservice.repositories;

import com.example.movieservice.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bkariuki
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
