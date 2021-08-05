package com.horizon.randomplay.components.movies;

import java.util.Set;

public class Movie {

    private final String name;
    private Long movieID;

    public Movie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }
}
