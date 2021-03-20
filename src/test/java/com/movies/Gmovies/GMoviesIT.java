package com.movies.Gmovies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    @Test
    public void createAMovie() throws Exception {
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




}
