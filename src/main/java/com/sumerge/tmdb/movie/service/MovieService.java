package com.sumerge.tmdb.movie.service;


import com.sumerge.tmdb.movie.repository.MovieRepository;
import com.sumerge.tmdb.movie.entities.Movie;
import com.sumerge.tmdb.movie.exception.MovieNotFoundException;
import com.sumerge.tmdb.movie.exception.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;





    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }




    public Page<Movie> findPaged(int pageNumber,int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Movie> page= movieRepository.findAll(pageable);

        if(page.getContent().isEmpty())
        {
            throw new PageNotFoundException("The page number ("+pageNumber+") provided exceeded the total number of pages ("+page.getTotalPages()+")");
        }
        else {
            return page;
        }
    }



    @Cacheable(value = "movie"/*,keyGenerator = "customKeyGenerator"*/,key = "#id")
    public Movie findById(int id){

        System.out.println("Fetched from db");
        Optional<Movie> result =movieRepository.findById(id);
        Movie movie=null;
        if(result.isPresent())
        {
            movie=result.get();
            return movie;
        }
        else
        {

            throw new MovieNotFoundException("Did not find movie with id - "+ id);
        }


    }


}
