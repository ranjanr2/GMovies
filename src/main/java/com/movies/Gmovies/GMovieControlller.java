package com.movies.Gmovies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GMovieControlller {

    @Autowired
    GMovieService movieService;
    public GMovieControlller(GMovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("GMovies/Movies")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAMovie(@RequestBody GMovieDto movieDto){
        this.movieService.create(movieDto);
    }


}
