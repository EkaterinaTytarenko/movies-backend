package com.practice.doesthecatdie.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.doesthecatdie.dtos.UserRatingDTO;
import com.practice.doesthecatdie.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private boolean valid;

    @JsonSerialize
    private Set<UserRatingDTO> ratings;

    private String role;

    public MyUserDetails(User user) {
        this.id= user.getId();
        this.username= user.getUsername();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.valid= user.isValid();
        this.role=user.getRole();
        try {
            this.ratings = user
                    .getUser_ratings()
                    .stream()
                    .map(UserRatingDTO::new)
                    .collect(Collectors.toSet());
        }
        catch (Exception e){

        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return valid;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return valid;
    }

    public Long getId() {
        return id;
    }

    public Set<UserRatingDTO> getRatings() {
        return ratings;
    }

    public String getRole() {
        return role;
    }
}
