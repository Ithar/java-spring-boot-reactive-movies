package com.ithar.malik.movies.controller;

import com.ithar.malik.movies.service.MovieService;
import domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebFluxTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    MovieService movieService;

    Mono<Movie> testMovie;

    @BeforeEach
    void setUp() {
        testMovie = Mono.just(new Movie("1", "Test Movie"));
    }

    @Test
    void getMovieById() {

        given(movieService.getMoveById(any())).willReturn(testMovie);

        webTestClient.get()
                .uri("/api/v1/movies/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Movie.class);
    }

}