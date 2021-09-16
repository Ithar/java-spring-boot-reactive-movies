package com.ithar.malik.movies.controller;

import com.ithar.malik.movies.service.MovieService;
import com.ithar.malik.movies.domain.Movie;
import com.ithar.malik.movies.domain.MovieEvent;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Flux<Movie>> getMovies() {
        Flux<Movie> moviesFlux =  movieService.getAllMovies();
        return ResponseEntity.ok(moviesFlux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Movie>> getMovieById(@PathVariable String id) {

        if (id.isEmpty()) {
            System.out.println("Invalid movie id");
        }

        Mono<Movie> movieMono = movieService.getMoveById(id);
        return ResponseEntity.ok(movieMono);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<MovieEvent>> getMovieEvents(@PathVariable String id) {

        if (id.isEmpty()) {
            System.out.println("Invalid movie id, cannot generate events");
        }

        Flux<MovieEvent> eventsFlux = movieService.getMovieEvents(id);
        return ResponseEntity.ok(eventsFlux);
    }


}
