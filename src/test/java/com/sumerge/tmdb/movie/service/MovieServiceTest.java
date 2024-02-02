package com.sumerge.tmdb.movie.service;

import com.sumerge.tmdb.movie.repository.MovieRepository;
import com.sumerge.tmdb.movie.entities.Movie;
import com.sumerge.tmdb.movie.exception.MovieNotFoundException;
import com.sumerge.tmdb.movie.exception.PageNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    private AutoCloseable autoCloseable;
    private MovieService underTest;
    @Before
    public void setUp() throws Exception {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MovieService(movieRepository);
    }

    @After
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void findPaged() {




        Page<Movie> mockPage = new PageImpl(movies);

        Pageable pageable = PageRequest.of(0, 2);

        Mockito.when(movieRepository.findAll(pageable)).thenReturn(mockPage);

        underTest.findPaged(0,2).equals(mockPage);
    }

    @Test
    public void findById() {
       Movie testMovie=new Movie(
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
                19365);

        Mockito.when(movieRepository.findById(1)).thenReturn(Optional.of(testMovie));
        underTest.findById(1).equals(testMovie);
    }


    @Test
    //whenExceptionThrown_thenAssertionSucceeds
    public void MovieNotFoundException() throws MovieNotFoundException {
        int id = 1;

        given(movieRepository.findById(id)).willReturn(Optional.empty());

        Exception exception = assertThrows(MovieNotFoundException.class, () -> {
            underTest.findById(id);
        });

        String expectedMessage = "Did not find movie with id - "+ id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    //whenExceptionThrown_thenAssertionSucceeds
    public void PageNotFoundException() throws PageNotFoundException {

        int pageNumber=4;
        int pageSize=2;
        Page<Movie> mockPage = new PageImpl<>(Collections.emptyList());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        given(movieRepository.findAll(pageable)).willReturn(mockPage);

        Exception exception = assertThrows(PageNotFoundException.class, () -> {
            underTest.findPaged(pageNumber,pageSize);
        });

        String expectedMessage =    "The page number ("+pageNumber+") provided exceeded the total number of pages ("+mockPage.getTotalPages()+")";

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
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