package com.example.inShorts.models.users;

public class JWTRequest {
    String name;
    String password;
    public JWTRequest(){

    }

    public String getName() {
        return name;
    }

    public JWTRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JWTRequest{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
