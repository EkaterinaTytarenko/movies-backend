package com.practice.doesthecatdie.models;


import javax.persistence.*;
import java.util.Set;

/*full information about movies that will be stored in db*/

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String imdbid;

    private int year;

    private String genres;

    private String picture_url;

    //total ratings of all users
    @OneToMany(mappedBy = "movie")
    Set<TotalRating> total_ratings;

    //ratings from particular users
    @OneToMany(mappedBy = "movie")
    Set<UserRating> user_ratings;

    public void setId(Long id) {
        this.id = id;
    }

    public Movie() {
    }

    public Movie(String title, String imdb_id, int year, String genres, String picture_url) {
        this.title = title;
        this.imdbid = imdb_id;
        this.year = year;
        this.genres = genres;
        this.picture_url = picture_url;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdb_id() {
        return imdbid;
    }

    public void setImdb_id(String imdb_id) {
        this.imdbid = imdb_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public Set<TotalRating> getTotal_ratings() {
        return total_ratings;
    }

    public void setTotal_ratings(Set<TotalRating> total_ratings) {
        this.total_ratings = total_ratings;
    }

    public Set<UserRating> getUser_ratings() {
        return user_ratings;
    }

    public void setUser_ratings(Set<UserRating> user_ratings) {
        this.user_ratings = user_ratings;
    }

    public void add_UserRating(UserRating user_rating){
        this.user_ratings.add(user_rating);
    }

    public void add_TotalRating(TotalRating total_rating){
        this.total_ratings.add(total_rating);
    }
}
