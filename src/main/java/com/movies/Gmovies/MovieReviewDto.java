package com.movies.Gmovies;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class MovieReviewDto {
    String title;
    int rating;
    String review;

    public MovieReviewDto(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }
    public MovieReviewDto(String title, int rating,String review) {
        this.title = title;
        this.rating = rating;
        this.review = review;
    }
}
