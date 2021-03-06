package com.movies.Gmovies;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//title, director, actors, release year, description and star rating.
@Service
public class GMovieService {

    private final GMovieRepository movieRepository;

    public GMovieService(GMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void create(GMovieDto movieDto) {

        movieRepository.save(
                new GMovieEntity(movieDto.getTitle(),
                        movieDto.getDirector(),
                        movieDto.getActors(),
                        movieDto.getRelease(),
                        movieDto.getDescription(),
                        new ArrayList<>()
                ));
    }

    public List<GMovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieEntity -> {
                    return new GMovieDto(movieEntity.getTitle(),
                            movieEntity.getDirector(),
                            movieEntity.getActors(),
                            movieEntity.getRelease(),
                            movieEntity.getDescription(),
                            movieEntity.getRating(),
                            movieEntity.getReviews() );
                })
                .collect(Collectors.toList());
    }

    public GMovieDto getMovieByTitle(String title) {
        return movieRepository.findAll()
                .stream()
                .filter(movieEntity -> movieEntity.getTitle().equals(title))
                .map(movieEntity -> {
                    return new GMovieDto(movieEntity.getTitle(),
                            movieEntity.getDirector(),
                            movieEntity.getActors(),
                            movieEntity.getRelease(),
                            movieEntity.getDescription(),
                            movieEntity.getRating(),
                            movieEntity.getReviews());
                }).collect(Collectors.toList())
                .stream().findFirst().orElse(null);
    }

    public void updateMovie(MovieReviewDto movieReviewDto) {
        GMovieEntity movieEntity = movieRepository.findAll()
                .stream()
                .filter(en ->
                        en.getTitle().equals(movieReviewDto.getTitle()))
                .collect(Collectors.toList()).stream().findFirst().orElse(null);

        if (movieEntity != null){
            movieEntity.addRating(movieReviewDto.getRating());
            movieEntity.addReview(movieReviewDto.getReview());
        }

        movieRepository.save(movieEntity);
    }
}
