package com.practice.doesthecatdie.models;

import javax.persistence.*;

/*ratings of particular user for particular movie and particular category*/

@Entity
public class UserRating {

    @EmbeddedId
    UserRatingKey id;

    @ManyToOne
    @MapsId("movieId")
    //@JoinColumn(name = "movie_id")
    Movie movie;

    @ManyToOne
    @MapsId("categoryId")
    //@JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @MapsId("userId")
    //@JoinColumn(name = "user_id")
    User user;

    //must be -1,0 or 1(one user can`t vote the same twice)
    @Column(nullable = false)
    private Integer rating;

    public UserRating() {
        this.id=new UserRatingKey();
        this.rating=0;
    }

    public UserRatingKey getId() {
        return id;
    }

    public void setId(UserRatingKey id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.user.setId(user.getId());
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
