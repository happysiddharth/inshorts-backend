package com.example.inShorts.configuration;

import com.example.inShorts.service.UserService;
import com.example.inShorts.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtility jwtUtility;
    @Autowired
    UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (authorization!=null && authorization.startsWith("Bearer")){
            jwtToken = authorization.substring(7);
            try {
                username = this.jwtUtility.extractUsername(jwtToken);

            }catch (Exception e){
                e.printStackTrace();
                try {
                    throw new Exception("token error");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            UserDetails userDetails = this.userService.loadUserByUsername(username);
            System.out.println(username);
            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                System.out.println("token not validated");
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
