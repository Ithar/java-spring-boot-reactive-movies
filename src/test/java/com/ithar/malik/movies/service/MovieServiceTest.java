package com.ithar.malik.movies.service;

import com.ithar.malik.movies.repository.MovieRepository;
import domain.MovieEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

@WebFluxTest({MovieService.class, MovieRepository.class})
class MovieServiceTest {

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    private Flux<MovieEvent> testMovieEvents;

    @BeforeEach
    void setUp() {

        testMovieEvents = Flux.just(new MovieEvent(UUID.randomUUID().toString(), "1", new Date()),
                new MovieEvent(UUID.randomUUID().toString(), "1", new Date()),
                new MovieEvent(UUID.randomUUID().toString(), "1" , new Date()),
                new MovieEvent(UUID.randomUUID().toString(), "1" , new Date()));
    }

    @Test
    void generateEvents() throws InterruptedException {

        // Given
        String movieId = "1";

        Consumer<MovieEvent> quoteConsumer = System.out::println;

        Consumer<Throwable> throwableConsumer = e -> System.out.println(e.getMessage());

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable done = countDownLatch::countDown;

        // When
        Flux<MovieEvent> quotesFlux = movieService.generateEvents(movieId);

        // Then
        quotesFlux.take(5).subscribe(quoteConsumer, throwableConsumer, done);

        countDownLatch.await();
    }

}