package com.sumerge.tmdb.movie.controller;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.sumerge.tmdb.movie.component.AuthFilter;
import com.sumerge.tmdb.movie.config.MovieTestConfiguration;
import com.sumerge.tmdb.movie.repository.MovieRepository;
import com.sumerge.tmdb.movie.entities.Movie;
import com.sumerge.tmdb.movie.service.MovieService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest()
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MovieTestConfiguration.class})
public class MovieControllerInteg {

    @Autowired
    WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    AuthFilter authFilter;

    @MockBean
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;


    private AutoCloseable autoCloseable;

    @Before
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(authFilter).build();

    }

    @After
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void validation() throws Exception {

        Page<Movie> mockPage = new PageImpl(movies);

        this.wireMockServer.stubFor(get("/api/auth/validate").willReturn(aResponse().withStatus(200)));

        Mockito.when(movieService.findPaged(0,2)).thenReturn(mockPage);



        mockMvc.perform(
                        MockMvcRequestBuilders.get("/tmdb/movies").queryParam("page", "0").queryParam("pageSize","2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization",
                                        "Bearer " +
                                                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZW1haWwxQGdtYWlsLmNvbSIsImlhdCI6MTcwNjY0Nzk5NSwiZXhwIjoxNzA2NjQ4ODU5fQ.MrF1umv-GzrmMoi9eI2i_R7lky6AMGIPTrbDnj_y9SQ"
                                )
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.content[0]").value(movies.get(0)));
    }

    List<Movie> movies = Arrays.asList(new Movie(
                    false,
                    "/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
                    "en",
                    "The Godfather 4",
                    "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                    112.113,
                    "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                    "1972-03-14",
                    "The Godfather",
                    false,
                    8.708,
                    19365),
            new Movie(
                    false,
                    "/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
                    "en",
                    "Shawshank redemption",
                    "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                    112.113,
                    "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                    "1972-03-14",
                    "The Godfather",
                    false,
                    8.708,
                    19365));
}
