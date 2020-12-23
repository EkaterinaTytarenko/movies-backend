package com.practice.doesthecatdie.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.doesthecatdie.models.TotalRating;

// dto that will be represent ratings by each category in MoviesDetailsDTO
@JsonSerialize
public class CategoryDTO {

    @JsonSerialize
    private String id;

    @JsonSerialize
    private String rating;

    @JsonSerialize
    private String category_name;

    public CategoryDTO(TotalRating rating) {
        this.id=rating.getCategory().getId()+"";
        this.rating=rating.getRating()+"";
        this.category_name=rating.getCategory().getName();
    }

}
