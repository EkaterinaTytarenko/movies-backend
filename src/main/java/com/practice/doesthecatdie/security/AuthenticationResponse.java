package com.practice.doesthecatdie.security;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.doesthecatdie.dtos.UserRatingDTO;

import java.util.Set;

@JsonSerialize
public class AuthenticationResponse {

    @JsonSerialize
    private final String jwt;

    @JsonSerialize
    private final String userId;

    @JsonSerialize
    private final String userRole;

    @JsonSerialize
    private final Set<UserRatingDTO> userRatings;

    public AuthenticationResponse(String jwt, Long userId, String userRole, Set<UserRatingDTO> userRatings) {
        this.jwt = jwt;
        this.userId = userId+"";
        this.userRole = userRole;
        this.userRatings = userRatings;
    }
}
