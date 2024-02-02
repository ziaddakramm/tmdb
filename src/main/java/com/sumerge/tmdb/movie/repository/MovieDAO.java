package com.sumerge.tmdb.movie.repository;

import com.sumerge.tmdb.movie.entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAO {
    private EntityManager entityManager;


    public MovieDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Transactional
    public void save(Movie movie) {
        entityManager.persist(movie);
    }

    @Transactional
    public void delete(int id) {
        Movie movie=entityManager.find(Movie.class,id);

        entityManager.remove(movie);
    }



}
