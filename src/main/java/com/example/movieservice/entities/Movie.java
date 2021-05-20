package com.example.movieservice.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bkariuki
 */
@Entity
@Table(name = "MOVIES")
public class Movie implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    private String title;
    private String description;
    private Boolean status;
    private Integer rating;
    private String comment;

    public Movie() {
        this.status = false;
        this.rating = 0;
        this.comment = "no comment";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
