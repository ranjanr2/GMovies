package com.movies.Gmovies;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GMovieDto {
    private String title;
    private String director;
    private String actors;
    private String release;
    private String description;
    private Integer starRating;
}
