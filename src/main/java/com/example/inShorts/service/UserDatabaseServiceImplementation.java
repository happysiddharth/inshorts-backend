package com.example.inShorts.service;

import com.example.inShorts.dao.UserDAO;
import com.example.inShorts.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDatabaseServiceImplementation implements UserDatabaseService{

    @Autowired
    UserDAO userDAO;

    public UserDatabaseServiceImplementation(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Iterable<UserEntity> getUserByEmail(String email) {
        return userDAO.findAll();
    }

    @Override
    public void createNewUser(UserEntity userEntity) {
        userDAO.save(userEntity);
    }
}
