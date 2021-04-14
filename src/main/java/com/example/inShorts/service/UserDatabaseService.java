package com.example.inShorts.service;

import com.example.inShorts.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserDatabaseService {
    Iterable<UserEntity> getUserByEmail(String email);
    void createNewUser(UserEntity userEntity);
}
