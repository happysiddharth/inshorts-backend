package com.example.inShorts.service;

import com.example.inShorts.dao.FavouriteDAO;
import com.example.inShorts.entity.FavouriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteDatabaseServiceImplementation implements FavouriteDatabaseService{

    @Autowired
    FavouriteDAO favouriteDAO;

    @Override
    public List<FavouriteEntity> getAllFavourite() {
        return (List<FavouriteEntity>) favouriteDAO.findAll();
    }

    @Override
    public void saveFavourite(FavouriteEntity favouriteEntity) {
        favouriteDAO.save(favouriteEntity);
    }
}
