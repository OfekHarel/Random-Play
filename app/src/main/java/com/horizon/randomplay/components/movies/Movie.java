package com.horizon.randomplay.components.movies;

public class Movie {

    private final String name;
    private Long movieID;

    public Movie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }
}
