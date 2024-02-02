package com.sumerge.tmdb.movie.controller;


import com.sumerge.tmdb.movie.entities.Movie;
import com.sumerge.tmdb.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/tmdb")
public class MovieController {


    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
        public Page<Movie> findAllPaged(@RequestParam int page,@RequestParam int pageSize) {

        return movieService.findPaged(page,pageSize);
    }



    @GetMapping("/movies/{id}")
    public Movie findById(@PathVariable int id) {

        return movieService.findById(id);
    }

}
