package com.practice.doesthecatdie.security;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class AuthorizationRequest {
    @JsonSerialize
    private String username;

    @JsonSerialize
    private String email;

    @JsonSerialize
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //default constructor for JSON Parsing
    public AuthorizationRequest()
    {

    }

    public AuthorizationRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
