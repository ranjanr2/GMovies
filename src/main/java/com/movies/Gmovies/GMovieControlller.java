package com.movies.Gmovies;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/GMovies")
public class GMovieControlller {

    @Autowired
    GMovieService movieService;
    public GMovieControlller(GMovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("/Movies")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAMovie(@RequestBody GMovieDto movieDto){
        this.movieService.create(movieDto);
    }

    @GetMapping("/Movies")
    public List<GMovieDto> getAllMovies() throws Exception {
        return this.movieService.getAllMovies();
    }


}
