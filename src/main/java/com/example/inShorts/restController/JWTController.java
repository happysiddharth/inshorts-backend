package com.example.inShorts.restController;

import com.example.inShorts.dao.UserDAO;
import com.example.inShorts.entity.FavouriteEntity;
import com.example.inShorts.entity.UserEntity;
import com.example.inShorts.models.favourite.FavouriteRequest;
import com.example.inShorts.models.users.JWTRequest;
import com.example.inShorts.models.users.JWTResponse;
import com.example.inShorts.service.FavouriteDatabaseServiceImplementation;
import com.example.inShorts.service.UserDatabaseServiceImplementation;
import com.example.inShorts.service.UserService;
import com.example.inShorts.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class JWTController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JWTUtility jwtUtility;

    @Autowired
    FavouriteDatabaseServiceImplementation favouriteDatabaseServiceImplementation;

    @Autowired
    UserDatabaseServiceImplementation userDatabaseServiceImplementation;

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public ResponseEntity<?> genertateToken(@RequestBody JWTRequest jwtRequest) throws Exception {

        userDatabaseServiceImplementation.createNewUser(new UserEntity(0,jwtRequest.getName(),jwtRequest.getPassword()));
//        try{
//            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getName(),jwtRequest.getPassword()));
//
//        }catch (UsernameNotFoundException e){
//            throw e;
//        }catch (BadCredentialsException e){
//            e.printStackTrace();
//            throw new Exception("Bad credential");
//        }

        UserDetails userDetails = this.userService.loadUserByUsername(jwtRequest.getName());
        String token = this.jwtUtility.generateToken(new JWTRequest(jwtRequest.getName(),jwtRequest.getPassword()));
        return ResponseEntity.ok(new JWTResponse(token));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> genertateTokenLogin(@RequestBody JWTRequest jwtRequest) throws Exception {

        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getName(),jwtRequest.getPassword()));

        }catch (UsernameNotFoundException e){
            throw e;
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad credential");
        }

        UserDetails userDetails = this.userService.loadUserByUsername(jwtRequest.getName());
        String token = this.jwtUtility.generateToken(new JWTRequest(jwtRequest.getName(),jwtRequest.getPassword()));
        return ResponseEntity.ok(new JWTResponse(token));
    }

    @RequestMapping(value = "/favourite",method = RequestMethod.GET)
    public void addToFavourite(@RequestBody FavouriteRequest favouriteRequest, @RequestHeader("Authorization") String token){
        String toke = this.jwtUtility.extractUsername(token.substring(7));
        System.out.println(favouriteRequest.getUrl());
        UserEntity entity = new UserEntity();
        List<UserEntity> users = (List<UserEntity>) userDatabaseServiceImplementation.getUserByEmail("");
        for (int i=0;i<users.size();i++){
            if (users.get(i).getEmail().toLowerCase(Locale.ROOT).contains(toke.toLowerCase(Locale.ROOT))){
                entity = users.get(i);
                break;
            }
        }

        favouriteDatabaseServiceImplementation.saveFavourite(new FavouriteEntity(0,entity.getId(),favouriteRequest.getUrl()));



    }
}
