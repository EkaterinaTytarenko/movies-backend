package com.practice.doesthecatdie.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.doesthecatdie.models.UserRating;

@JsonSerialize
public class UserRatingDTO {
    @JsonSerialize
    private String category_id;

    @JsonSerialize
    private String rating;

    @JsonSerialize
    private String movie_id;

    public UserRatingDTO(UserRating rating) {
        this.category_id=rating.getCategory().getId()+"";
        this.rating=rating.getRating()+"";
        this.movie_id=rating.getMovie().getId()+"";
    }
}
