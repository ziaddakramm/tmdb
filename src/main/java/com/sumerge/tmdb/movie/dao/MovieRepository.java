package com.sumerge.tmdb.movie.dao;

import com.sumerge.tmdb.movie.entities.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {


}
