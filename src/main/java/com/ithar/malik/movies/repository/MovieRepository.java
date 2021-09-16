package com.ithar.malik.movies.repository;

import com.ithar.malik.movies.domain.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}
