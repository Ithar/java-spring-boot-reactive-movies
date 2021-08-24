package com.ithar.malik.movies.controller;

import com.ithar.malik.movies.service.MoveService;
import domain.Movie;
import domain.MovieEvent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MoveService moveService;

    public MovieController(MoveService moveService) {
        this.moveService = moveService;
    }

    @GetMapping
    public Flux<Movie> getMovies() {
        return moveService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Mono<Movie> getMovieById(@PathVariable String id) {

        if (id.isEmpty()) {
            System.out.println("Invalid movie id");
        }

        return moveService.getMoveById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> generateEvents(@PathVariable String id) {

        if (id.isEmpty()) {
            System.out.println("Invalid movie id");
        }

        return moveService.generateEvents(id);
    }


}
