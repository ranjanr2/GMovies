package com.movies.Gmovies;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @Test
    public void createAMovieTest() throws Exception {
        GMovieDto movieDto = new GMovieDto("The Avengers","Joss Whedon","Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                null);
        mockMvc.perform(post("/GMovies/Movies")
                .content(objectMapper.writeValueAsString(movieDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
//        mockMvc.perform(get("/Gmovies/Movies")
//        ).andExpect(status().isOk())
//                .andExpect(jsonPath("length()").value(1));
    }

    //GET Tests
    @Test
    public void getAllMoviesNullTest() throws Exception {
        mockMvc.perform(get("/GMovies/Movies")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0));
    }


    @Test
    public void getAllMovies() throws Exception {
        //Post the get data - Set Up
        for(int i =0 ; i< movies.size();i++)
        {
            mockMvc.perform(post("/GMovies/Movies")
                    .content(objectMapper.writeValueAsString(movies.get(i)))
                    .contentType(MediaType.APPLICATION_JSON));

        }

        mockMvc.perform(get("/GMovies/Movies")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(7));
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
        movies = mapper.readValue(moviesFile, new TypeReference<ArrayList<GMovieDto>>() {});
    }
}
