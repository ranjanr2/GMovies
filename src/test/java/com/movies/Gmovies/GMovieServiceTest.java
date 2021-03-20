package com.movies.Gmovies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GMovieServiceTest {

    @Mock
    GMovieRepository mockMovieRepository;

    @InjectMocks
    GMovieService subject;


    @Test
    void createAMovie() {
        GMovieDto movieDto = new GMovieDto("The Avengers","Joss Whedon","Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                null);
        subject.create(movieDto);
        verify(mockMovieRepository).save(
                new GMovieEntity("The Avengers","Joss Whedon","Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                        "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                        new ArrayList<Integer>()));
    }
}
