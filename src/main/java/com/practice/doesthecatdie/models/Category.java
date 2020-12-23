package com.practice.doesthecatdie.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    Set<TotalRating> total_ratings;

    @OneToMany(mappedBy = "category")
    Set<UserRating> user_ratings;

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TotalRating> getTotal_ratings() {
        return total_ratings;
    }

    public void setTotal_ratings(Set<TotalRating> total_Ratings) {
        this.total_ratings = total_Ratings;
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

    public Category(String name) {
        this.name = name;
    }

}
