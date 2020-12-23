package com.practice.doesthecatdie.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRatingKey implements Serializable {

    //@Column(name = "movie_id")
    Long movieId;

    //@Column(name = "category_id")
    Long categoryId;

    //@Column(name = "user_id")
    Long userId;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
