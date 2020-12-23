package com.practice.doesthecatdie.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.doesthecatdie.models.Movie;

//basic dto that will be used for displaying all movies in the db
@JsonSerialize
public class MoviesSearchDTO {

    @JsonSerialize
    private Long id;

    @JsonSerialize
    private String title;

    @JsonSerialize
    private int year;

    public MoviesSearchDTO(Movie movie) {
        this.id=movie.getId();
        this.title=movie.getTitle();
        this.year=movie.getYear();
    }
}
