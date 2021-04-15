package com.example.inShorts.dao;

import com.example.inShorts.entity.FavouriteEntity;
import org.springframework.data.repository.CrudRepository;

public interface FavouriteDAO extends CrudRepository<FavouriteEntity,Integer> {
}
