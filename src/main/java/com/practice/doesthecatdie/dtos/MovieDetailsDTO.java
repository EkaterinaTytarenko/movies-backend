package com.practice.doesthecatdie.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.doesthecatdie.models.Movie;

import java.util.Set;
import java.util.stream.Collectors;

// dto that will be used for displaying the full info about the chosen movie
@JsonSerialize
public class MovieDetailsDTO {
    @JsonSerialize
    private Long id;

    @JsonSerialize
    private String title;

    @JsonSerialize
    private int year;

    @JsonSerialize
    private String genres;

    @JsonSerialize
    private String picture_url;

    @JsonSerialize
    private Set<CategoryDTO> common_ratings;

    public MovieDetailsDTO(Movie movie) {
        this.id= movie.getId();
        this.title= movie.getTitle();
        this.year=movie.getYear();
        this.genres=movie.getGenres();
        this.picture_url= movie.getPicture_url();
        this.common_ratings=movie
                .getTotal_ratings()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toSet());
    }
}
