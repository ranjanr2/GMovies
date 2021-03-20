package com.movies.Gmovies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GMovieRepository extends JpaRepository<GMovieEntity, Long> {

}

