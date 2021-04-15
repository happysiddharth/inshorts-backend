package com.example.inShorts.models.favourite;

public class FavouriteRequest {
    private String url;

    public FavouriteRequest() {
    }

    public FavouriteRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
