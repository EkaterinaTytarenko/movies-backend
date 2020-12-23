package com.practice.doesthecatdie.security;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class AuthenticationRequest {

    @JsonSerialize
    private String username;

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

    //default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
}
