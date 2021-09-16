package com.ithar.malik.movies.route;

import com.ithar.malik.movies.domain.Movie;
import com.ithar.malik.movies.domain.MovieEvent;
import com.ithar.malik.movies.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_NDJSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@RequiredArgsConstructor
@Component
public class MovieHandler {

    private final MovieService movieService;

    public Mono<ServerResponse> fetchMovies(ServerRequest request){
        int size = Integer.parseInt(request.queryParam("size").orElse("3"));
        return ok().contentType(APPLICATION_JSON)
                .body(movieService.getAllMovies().take(size), Movie.class);
    }

    public Mono<ServerResponse> streamMovieEvents(ServerRequest request){

        String movieId = request.pathVariable("movieId");

        return ok().contentType(APPLICATION_NDJSON)
                .body(movieService.getMovieEvents(movieId), MovieEvent.class);
    }
}
