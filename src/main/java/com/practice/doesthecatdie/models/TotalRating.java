package com.practice.doesthecatdie.models;

import javax.persistence.*;

/*total ratings of all users for particular movie and particular category*/

@Entity
public class TotalRating {

    @EmbeddedId
    TotalRatingKey id;

    @ManyToOne
    @MapsId("movieId")
    //@JoinColumn(name = "movie_id")
    Movie movie;

    @ManyToOne
    @MapsId("categoryId")
    //@JoinColumn(name = "category_id")
    Category category;

    @Column(nullable = false)
    private Integer rating;

    public TotalRating() {
        id=new TotalRatingKey();
        rating=0;
    }


    public TotalRatingKey getId() {
        return id;
    }

    public void setId(TotalRatingKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        this.id.setMovieId(movie.getId());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.id.setCategoryId(category.getId());
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
