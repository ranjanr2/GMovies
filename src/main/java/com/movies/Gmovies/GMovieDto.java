package com.movies.Gmovies;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GMovieDto {
    private String title;
    private String director;
    private String actors;
    private String release;
    private String description;
    private Integer starRating;
    private List<String> reviews;

    public GMovieDto(String title, String director, String actors, String release, String description, Integer starRating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.release = release;
        this.description = description;
        this.starRating = starRating;
    }
}
