package com.example.inShorts.restController;

import com.example.inShorts.models.ResponseDTO;
import com.fasterxml.jackson.annotation.JacksonInject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
                    description = "Successful response",
                    content = {@Content(mediaType = "application/json")})


    })
    @GetMapping(path = "/news/{cat}")
    public ResponseDTO getNews(@RequestParam String cat) {
        ResponseDTO objects = restTemplate.getForObject("https://inshortsapi.vercel.app/news?category=politics", ResponseDTO.class);

        return objects;
    }


    @GetMapping(path = "/new")
    public List<Object> getnews() {
        Object[] objects = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Object[].class);

        return Arrays.asList(objects);
    }

}
