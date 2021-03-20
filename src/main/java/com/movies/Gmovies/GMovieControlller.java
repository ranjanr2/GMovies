package com.movies.Gmovies;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/Movies/{title}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable String title) {
        GMovieDto movieDto = this.movieService.getMovieByTitle(title);
        if (movieDto != null) {
            return new ResponseEntity<>(movieDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Exception("Requested movie does not exist"), HttpStatus.OK);
        }
    }

    @PutMapping("/Movies")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovie(@RequestBody MovieReviewDto movieReviewDto){
        this.movieService.updateMovie(movieReviewDto);
    }

}
