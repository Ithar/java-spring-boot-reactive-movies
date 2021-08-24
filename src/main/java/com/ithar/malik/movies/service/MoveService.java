package com.ithar.malik.movies.service;

import com.ithar.malik.movies.repository.MovieRepository;
import domain.Movie;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        return movieRepository.findAll();
    }
}
