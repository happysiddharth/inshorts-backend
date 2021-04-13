package com.example.inShorts.restController;

import com.example.inShorts.models.ResponseDTO;
import com.fasterxml.jackson.annotation.JacksonInject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InShortsRestController {

    @Autowired
    RestTemplate restTemplate;
    @Operation(summary = "news api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful response",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not able to load",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping(path = "/news/{cat}")
    public ResponseDTO getNews(@PathVariable String cat) {
        ResponseDTO response = restTemplate.getForObject("https://inshortsapi.vercel.app/news?category="+cat, ResponseDTO.class);
        return response;
    }





}
