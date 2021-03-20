package com.movies.Gmovies;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GMoviesIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    //POST Test
//    Given a new movie has released
//    When I submit this new movie to GMDB movies
//    Then I should see that movie in GMDB movies
    @Test
    @DirtiesContext
    public void createAMovieTest() throws Exception {
        GMovieDto movieDto = new GMovieDto("The Avengers", "Joss Whedon", "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                null);
        mockMvc.perform(post("/GMovies/Movies")
                .content(objectMapper.writeValueAsString(movieDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    //GET Tests
//    Given the GBDB is empty
//    When I visit GMDB movies
//    Then I should see no movies
    @Test
    @DirtiesContext
    public void getAllMoviesNullTest() throws Exception {
        mockMvc.perform(get("/GMovies/Movies")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0));
    }

    //    Given the GBDB has many movies
//    When I visit GMDB movies
//    Then I should see a list with that movie
    @Test
    @DirtiesContext
    public void getAllMovies() throws Exception {
        //Post the get data - Set Up
        for (int i = 0; i < movies.size(); i++) {
            mockMvc.perform(post("/GMovies/Movies")
                    .content(objectMapper.writeValueAsString(movies.get(i)))
                    .contentType(MediaType.APPLICATION_JSON));

        }

        mockMvc.perform(get("/GMovies/Movies")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(7));
    }


    //    Given the GBDB has a movie
//    When I visit GMDB movies
//    Then I should see that movie in GMDB movies
    @Test
    @DirtiesContext
    public void getOneMovie() throws Exception {
        GMovieDto movieDto = new GMovieDto("The Avengers", "Joss Whedon", "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                null);

        mockMvc.perform(post("/GMovies/Movies")
                .content(objectMapper.writeValueAsString(movieDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

        mockMvc.perform(get("/GMovies/Movies")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .equals(jsonPath("[0].title").value("The Avengers"));
    }

//    Given the GBDB has many movies
//    When I visit GMDB movies with an existing title
//    Then I should see that movie's details
    @Test
    @DirtiesContext
    public void getMovieByTitleTest() throws Exception {
        postAllMoviesFromJson();

        mockMvc.perform(get("/GMovies/Movies/Unbreakable")
        ).andExpect(status().isOk())
                .equals(jsonPath("$.title").value("Unbreakable"));
    }

//    Given the GBDB has many movies
//    When I visit GMDB movies with a non-existing title
//    Then I receive a friendly message that it doesn't exist
    @Test
    @DirtiesContext
    public void getMovieByInvalidTitleTest() throws Exception {
        postAllMoviesFromJson();

        mockMvc.perform(get("/GMovies/Movies/XYZ")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Requested movie does not exist"));
    }

//    Given an existing movie
//    When I submit a 5 star rating
//    Then I can see it in the movie details.
    @Test
    @DirtiesContext
    public void submitRatingTest() throws Exception {
        postAllMoviesFromJson();

        MovieReviewDto movieReviewDto = new MovieReviewDto("Unbreakable", 5);

        mockMvc.perform(put("/GMovies/Movies")
                .content(objectMapper.writeValueAsString(movieReviewDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/GMovies/Movies/Unbreakable")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.starRating")
                        .value(5));
    }

//    Given a movie with one 5 star rating and one 3 star rating
//    When I view the movie details
//    Then I expect the star rating to be 4.
    @Test
    @DirtiesContext
    public void getAverageRatingTest() throws Exception {
        postAllMoviesFromJson();

        MovieReviewDto movieReviewDto1 = new MovieReviewDto("Unbreakable", 3);
        MovieReviewDto movieReviewDto2 = new MovieReviewDto("Unbreakable", 5);

        mockMvc.perform(put("/GMovies/Movies")
                .content(objectMapper.writeValueAsString(movieReviewDto1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(put("/GMovies/Movies")
                .content(objectMapper.writeValueAsString(movieReviewDto2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/GMovies/Movies/Unbreakable")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.starRating")
                        .value(4));
    }

//Given an existing movie
//When I submit a star rating and text review
//Then I can see my contribution on the movie details.
@Test
@DirtiesContext
public void submitReviewsTest() throws Exception {
    postAllMoviesFromJson();

    MovieReviewDto movieReviewDto = new MovieReviewDto("Unbreakable", 5,"Very Bad Movie");

    mockMvc.perform(put("/GMovies/Movies")
            .content(objectMapper.writeValueAsString(movieReviewDto))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    mockMvc.perform(get("/GMovies/Movies/Unbreakable")
    ).andExpect(status().isOk())
            .andExpect(jsonPath("$.reviews[0]")
                    .value("Very Bad Movie"));
}




    //Helper method
    private void postAllMoviesFromJson() throws Exception{
        for (int i = 0; i < movies.size(); i++) {
            mockMvc.perform(post("/GMovies/Movies")
                    .content(objectMapper.writeValueAsString(movies.get(i)))
                    .contentType(MediaType.APPLICATION_JSON));

        }
    }

    //Data Set up
    ObjectMapper mapper;
    ArrayList<GMovieDto> movies;

    String moviesJsonPath = "src/test/data/movies.json";

    @BeforeEach
    void setUp() throws IOException {
        initializeMoviesData();
    }

    // TEST UTILITIES ----------------------------------------------------

    private void initializeMoviesData() throws IOException {
        mapper = new ObjectMapper();
        File moviesFile = new File(moviesJsonPath);
        movies = mapper.readValue(moviesFile, new TypeReference<ArrayList<GMovieDto>>() {
        });
    }
}
