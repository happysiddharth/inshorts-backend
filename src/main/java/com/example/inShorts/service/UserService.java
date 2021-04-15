package com.example.inShorts.service;

import com.example.inShorts.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDatabaseServiceImplementation userDatabaseServiceImplementation;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //logic to user from dactabase
        //System.out.println("stirng"+s);
        List<UserEntity> users = (List<UserEntity>) userDatabaseServiceImplementation.getUserByEmail("");
        UserEntity current_user = new UserEntity();
        boolean is_found = false;
        for (int i=0;i<users.size();i++){
            if (users.get(i).getEmail().toLowerCase(Locale.ROOT).contains(s.toLowerCase(Locale.ROOT))){
                current_user = users.get(i);
                is_found = true;
                break;
            }
        }
        if (!is_found){
            throw new UsernameNotFoundException("User name not found");
        }
        return new User(current_user.getEmail(),current_user.getPassword(),new ArrayList<>());
    }
}
