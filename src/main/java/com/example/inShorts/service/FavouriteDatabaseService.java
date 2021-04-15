package com.example.inShorts.service;

import com.example.inShorts.entity.FavouriteEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FavouriteDatabaseService {
    List<FavouriteEntity> getAllFavourite();
    void saveFavourite(FavouriteEntity favouriteEntity);
}
