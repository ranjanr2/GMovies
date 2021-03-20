package com.movies.Gmovies;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter

public class GMovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String title;
    private String director;
    private String actors;
    private String release;
    private String description;
    private ArrayList<Integer> starRating;
    private ArrayList<String> reviews;


    public GMovieEntity(String title, String director, String actors, String release, String description, ArrayList<Integer> starRating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.release = release;
        this.description = description;
        this.starRating = starRating;
        this.reviews = new ArrayList<>();
    }

    void addRating(int r){
        starRating.add(r);
    }

    void addReview(String review){
        reviews.add(review);
    }

    Integer getRating(){
        int total=0;
        for (Integer integer : starRating) {
            total = total + integer;
        }

        if (starRating.size()>0)
            return total/starRating.size();
        else
            return null;
    }
}
