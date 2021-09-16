package com.ithar.malik.movies.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_NDJSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovieRouteConfig {

    public static final String MOVIE_PATH = "/api/v2/movies";
    public static final String MOVIE_EVENTS_PATH = "/api/v2/movies/{movieId}/events";

    @Bean
    public RouterFunction<ServerResponse> movieRoutes(MovieHandler handler) {

        return route().GET(MOVIE_PATH, accept(APPLICATION_JSON), handler::fetchMovies)
            .GET(MOVIE_EVENTS_PATH, accept(APPLICATION_NDJSON), handler::streamMovieEvents)
                .build();
    }
}
