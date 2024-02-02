package com.sumerge.tmdb.movie.config;

import com.github.tomakehurst.wiremock.WireMockServer;

import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class MovieTestConfiguration {
//    @MockBean
//    private MovieRepository movieRepository;

    @Bean
    public WireMockServer wireMockServer(){
        WireMockServer server= new WireMockServer(8080);
        server.start();
        return server;
    }


//    @Bean()
//    public ServerList<Server> ribbonServerList(WireMockServer wms, ConfigurableApplicationContext applicationContext) {
//        return new StaticServerList<>(new Server("localhost", wms.port()));
//
//    }

//    @Bean MovieService movieService()
//    {
//        return new MovieService(movieRepository);
//    }

}

