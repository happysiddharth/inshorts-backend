package com.example.inShorts.entity;

import javax.persistence.*;

@Entity
@Table(name = "favourite")
public class FavouriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name ="user_id" )
    int user_id;

    @Column(name = "url")
    String url;

    public FavouriteEntity(){}

    public FavouriteEntity(int id, int user_id, String url) {
        this.id = id;
        this.user_id = user_id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUrl() {
        return url;
    }
}
