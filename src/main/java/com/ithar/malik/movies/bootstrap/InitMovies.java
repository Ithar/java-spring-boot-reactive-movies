package com.ithar.malik.movies.bootstrap;

import domain.Movie;
import com.ithar.malik.movies.repository.MovieRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitMovies implements ApplicationListener<ContextRefreshedEvent>  {

    private final MovieRepository repository;

    public InitMovies(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        repository.deleteAll();
        createMovies();
    }

    private void createMovies() {

        List<String> movieTitles = List.of("A space Odyssey", "The Godfather", "The Dark Night", "Pulp Fiction", "The Shawshank Redemption");
        List<Movie> movies = movieTitles.stream()
                .map(title -> Movie.builder().title(title).build()).collect(Collectors.toList());

        repository.saveAll(movies).subscribe(null, null, () -> repository.findAll().subscribe(System.out::println));

        System.out.println("#############################");
        System.out.println("Initialization completed ... ");
        System.out.println("#############################");
    }
}
