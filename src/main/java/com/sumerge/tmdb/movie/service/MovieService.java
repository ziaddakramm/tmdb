package com.sumerge.tmdb.movie.service;


import com.sumerge.tmdb.movie.dao.MovieRepository;
import com.sumerge.tmdb.movie.entities.Movie;
import com.sumerge.tmdb.movie.exception.MovieNotFoundException;
import com.sumerge.tmdb.movie.exception.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    //to  be updated to handle pagination
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Page<Movie> findPaged(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 2);

        Page<Movie> page= movieRepository.findAll(pageable);

        if(page.getContent().isEmpty())
        {
            throw new PageNotFoundException("The page number ("+pageNumber+") provided exceeded the total number of pages ("+page.getTotalPages()+")");
        }
        else {
            return page;
        }
    }


    public Movie findById(int id){


        Optional<Movie> result =movieRepository.findById(id);
        Movie movie=null;
        if(result.isPresent())
        {
            movie=result.get();
            return movie;
        }
        else
        {
            //to be updated
            throw new MovieNotFoundException("Did not find employee with id - "+ id);
        }


    }
}
