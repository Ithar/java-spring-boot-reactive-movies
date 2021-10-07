package com.ithar.malik.movies.bootstrap;

import com.ithar.malik.movies.domain.Movie;
import com.ithar.malik.movies.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class InitMovies implements ApplicationListener<ContextRefreshedEvent>  {

    private final MovieRepository repository;

    public InitMovies(final MovieRepository repository) {
        this.repository = repository;
        this.repository.deleteAll();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createMovies();
    }

    private void createMovies() {

        List<String> movieTitles = List.of("A space Odyssey", "The Godfather", "The Dark Night", "Pulp Fiction", "The Shawshank Redemption");
        List<Movie> movies = movieTitles.stream()
                .map(title -> Movie.builder().title(title).build()).collect(Collectors.toList());

        repository.saveAll(movies).subscribe(savedMovie -> log.info("Saving movie:" + savedMovie.getTitle()),
                                            throwable -> log.info("Error occurred saving to Mongo" + throwable.getMessage()),
                                            () -> repository.findAll().subscribe(System.out::println));

        log.info("#############################");
        log.info("Initialization completed ... ");
        log.info("#############################");
    }
}
