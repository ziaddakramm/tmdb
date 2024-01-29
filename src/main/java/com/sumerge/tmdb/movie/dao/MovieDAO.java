package com.sumerge.tmdb.movie.dao;

import com.sumerge.tmdb.movie.entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAO {
    private EntityManager entityManager;


    //Entity manager gets injected automatically by spring boot
    //no need for autowiring
    public MovieDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Transactional
    public void save(Movie movie) {
        //save instructor
        //CascadeType.ALL will make entity manager also persist the instructor detail
        entityManager.persist(movie);
    }

    @Transactional
    public void delete(int id) {
        Movie movie=entityManager.find(Movie.class,id);

        entityManager.remove(movie);
    }



}
