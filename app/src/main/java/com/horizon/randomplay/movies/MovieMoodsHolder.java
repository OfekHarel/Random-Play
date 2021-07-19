package com.horizon.randomplay.movies;

import com.horizon.randomplay.components.Mood;

import java.util.Objects;

public class MovieMoodsHolder {

    private static void marvel() {
        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MARVEL.getName())).
                setMood(Mood.CRY, 22);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MARVEL.getName())).
                setMood(Mood.LAUGH, 17, 15, 10);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MARVEL.getName())).
                setMood(Mood.BEST, 22, 19, 18, 17, 15, 1);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MARVEL.getName())).
                setMood(Mood.SMART, 1, 3, 7, 14);
    }

    public static void init() {
        marvel();
    }
}
