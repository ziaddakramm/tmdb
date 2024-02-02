package com.sumerge.tmdb.movie.repository;

import com.sumerge.tmdb.movie.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {


}
