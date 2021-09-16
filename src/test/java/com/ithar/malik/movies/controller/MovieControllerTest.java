package com.ithar.malik.movies.controller;

import com.ithar.malik.movies.service.MovieService;
import com.ithar.malik.movies.domain.Movie;
import com.ithar.malik.movies.domain.MovieEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebFluxTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    MovieService movieService;

    Mono<Movie> testMovie;
    Flux<Movie> testMovies;
    Flux<MovieEvent> testMovieEvents;

    @BeforeEach
    void setUp() {
        testMovie = Mono.just(new Movie("1", "Test Movie"));

        testMovies = Flux.just( new Movie("1", "Test Movie"),
                                new Movie("2", "Test Movie 2"),
                                new Movie("3", "Test Movie 3"),
                                new Movie("4", "Test Movie 4"));

        testMovieEvents = Flux.just(new MovieEvent(UUID.randomUUID().toString(),"1", new Date()),
                                    new MovieEvent(UUID.randomUUID().toString(),"1", new Date()),
                                    new MovieEvent(UUID.randomUUID().toString(),"1",  new Date()),
                                    new MovieEvent(UUID.randomUUID().toString(),"1",  new Date()));
    }

    @Test
    void getMovies() {

        given(movieService.getAllMovies()).willReturn(testMovies);

        webTestClient.get()
                .uri("/api/v1/movies")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArrayList.class);
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

    @Test
    void getMovieEvents() {

        given(movieService.getMovieEvents(any())).willReturn(testMovieEvents);

        webTestClient.get()
                .uri("/api/v1/movies/1/events")
                //.accept(MediaType.TEXT_EVENT_STREAM_VALUE)
                .exchange()
                .expectStatus().isOk();
                //.expectBody(ArrayList.class);
    }

}