package com.example.movieservice.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "RATINGS")
public class Rating implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Integer rating;
    private Boolean status;
    private String comments;
    @Column(name = "MOVIE_ID")
    private Long movieId;
    @ManyToOne()
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private Movie movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
