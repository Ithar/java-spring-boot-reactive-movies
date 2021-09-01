package com.ithar.malik.movies.controller;

import com.ithar.malik.movies.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.BDDMockito.given;

@WebFluxTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    MovieService movieService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getMovieById() {

        given(movieService.getMoveById("")).willReturn(null);


    }

}