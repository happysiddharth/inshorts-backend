package com.example.inShorts.models.users;

public class JWTResponse {
    String token;
    public JWTResponse(){

    }

    public JWTResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
