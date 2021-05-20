package com.example.movieservice;

import com.example.movieservice.entities.Movie;
import com.example.movieservice.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author bkariuki
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "test")
public class MovieTests {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void createMovie() {
        Movie movie = new Movie();
        movie.setTitle("Gangster");
        movie.setDescription("new release");
        movieRepository.save(movie);
    }

    @Test
    public void movieExists() {
        Movie movie = movieRepository.findByTitle("Gangster");
        assertThat(movie).isNotNull();
    }

    @Test
    public void getAllSystemUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/all-movies")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
