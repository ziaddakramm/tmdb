package com.sumerge.tmdb.movie.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "jplaceholder", url ="http://localhost:8080/auth")
public interface AuthClient {



    @GetMapping( value = "/validate")
    ResponseEntity<Void> getValidation( @RequestHeader("Authorization") String jwtToken);



}
