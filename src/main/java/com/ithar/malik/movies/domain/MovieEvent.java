package com.ithar.malik.movies.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
public class MovieEvent {

    private String id;
    private String movieId;
    private Date date;
}
