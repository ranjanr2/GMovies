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
                            null);
                })
                .collect(Collectors.toList());
    }
}
