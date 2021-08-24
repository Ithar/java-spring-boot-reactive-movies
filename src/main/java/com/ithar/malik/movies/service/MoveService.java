package com.ithar.malik.movies.service;

import com.ithar.malik.movies.repository.MovieRepository;
import domain.Movie;
import domain.MovieEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service
public class MoveService {

    private final MovieRepository movieRepository;

    public MoveService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Mono<Movie> getMoveById(String id) {
        return movieRepository.findById(id);
    }

    public Flux<Movie> getAllMovies() {

        MovieEvent me = new MovieEvent(generateEventId(), "1", new Date());
        System.out.println(me.toString());

        return movieRepository.findAll();
    }

    public Flux<MovieEvent> generateEvents(String id) {
        return Flux.<MovieEvent>generate(event -> {
            event.next(new MovieEvent(generateEventId(), id, new Date()));
        }).delayElements(Duration.ofSeconds(3));
    }

    private String generateEventId() {
        return UUID.randomUUID().toString().replace("-","").substring(7,31);
    }
}

