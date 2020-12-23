package com.practice.doesthecatdie.models;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean valid;

    @OneToMany(mappedBy = "user")
    Set<UserRating> user_ratings;

    private String role;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public User() {
    }

    @OneToMany(mappedBy = "user")
    public Set<UserRating> getUser_ratings() {
        return user_ratings;
    }


    public User(String username, String email, String password, String role) {
        this.id=UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.valid=false;
    }

    public void setUser_ratings(Set<UserRating> user_ratings) {
        this.user_ratings = user_ratings;
    }

    public void add_UserRating(UserRating user_rating){
        this.user_ratings.add(user_rating);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
