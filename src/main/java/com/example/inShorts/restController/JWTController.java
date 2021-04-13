package com.example.inShorts.restController;

import com.example.inShorts.models.users.JWTRequest;
import com.example.inShorts.models.users.JWTResponse;
import com.example.inShorts.service.UserService;
import com.example.inShorts.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JWTUtility jwtUtility;

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<?> genertateToken(@RequestBody JWTRequest jwtRequest) throws Exception {

        System.out.println(jwtRequest);
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getName(),jwtRequest.getPassword()));

        }catch (UsernameNotFoundException e){
            throw e;
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad credential");
        }

        UserDetails userDetails = this.userService.loadUserByUsername(jwtRequest.getName());
        String token = this.jwtUtility.generateToken(new JWTRequest("sid","password"));
        return ResponseEntity.ok(new JWTResponse(token));
    }
}
