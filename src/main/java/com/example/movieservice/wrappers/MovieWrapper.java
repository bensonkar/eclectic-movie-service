package com.example.movieservice.wrappers;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bkariuki
 */
@Data
public class MovieWrapper implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Integer rating;
    private String comment;
}
