package com.movies.Gmovies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GMovieServiceTest {

    @Mock
    GMovieRepository mockMovieRepository;

    @InjectMocks
    GMovieService subject;

    @Test
    void createAMovieTest() {
        GMovieDto movieDto = new GMovieDto("The Avengers","Joss Whedon","Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                null);
        subject.create(movieDto);
        verify(mockMovieRepository).save(
                new GMovieEntity("The Avengers","Joss Whedon","Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                        "2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                        new ArrayList<Integer>()));
    }

    @Test
    public void findByTitle(){
        //Setup
        when(mockMovieRepository.findAll()).thenReturn(
                List.of(
                        new GMovieEntity("The Avengers", "Joss Whedon", "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                                "2012", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                                null),
                        new GMovieEntity("The Avengers2", "Joss Whedon", "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                                "2012", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                                null))


        );

        //Exercise
        GMovieDto actual = subject.getMovieByTitle("The Avengers");

        //Assert
        assertThat(actual).isEqualTo(
                (
                        new GMovieDto("The Avengers", "Joss Whedon", "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
                                "2012", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                                null)
                )
        );
    }
}
