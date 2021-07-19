package com.horizon.randomplay.components.movies;

import java.util.ArrayList;

public class MovieCollection {
    private final ArrayList<Movie> movies;
    private final String name;

    public MovieCollection(String name, ArrayList<Movie> movies) {
        this.movies = movies;
        this.name = name;
    }

    public MovieCollection(String name) {
        this(name, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie m) {
        this.movies.add(m);
    }
}
