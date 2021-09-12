package com.horizon.randomplay.movies;

import com.horizon.randomplay.components.Mood;

import java.util.Objects;

public class MovieMoodsHolder {

    private static void marvel() {
        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MARVEL.getName())).
                setMood(Mood.CRY, 25, 22);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MARVEL.getName())).
                setMood(Mood.LAUGH, 20, 18, 13);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MARVEL.getName())).
                setMood(Mood.BEST, 25, 22, 21, 20, 18, 4);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MARVEL.getName())).
                setMood(Mood.SMART, 4, 6, 10, 17);
    }

    private static void goldens() {
        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MY_GOLDENS.getName())).
                setMood(Mood.SMART, 1, 2, 3, 8, 12);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MY_GOLDENS.getName())).
                setMood(Mood.WAR, 22, 20, 19, 16, 9, 7, 6);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MY_GOLDENS.getName())).
                setMood(Mood.CRY, 13, 9, 3, 4);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.MY_GOLDENS.getName())).
                setMood(Mood.SOUND_SCORE, 22, 20, 17, 7, 6, 4, 1, 2, 3);
    }

    private static void disney() {
        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.DISNEY_ANIM.getName())).
                setMood(Mood.NOSTALGIC, 7, 15, 23, 32, 34, 36, 37, 38, 45);

        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.DISNEY_ANIM.getName())).
                setMood(Mood.CRY, 40);
    }

    private static void dreamworks() {
        Objects.requireNonNull(MoviesHolder.getAllMovies().get(MoviesHolder.MovieKind.DREAM_WORKS.getName())).
                setMood(Mood.NOSTALGIC, 1, 5, 8, 9);
    }

    public static void init() {
        marvel();
        goldens();
        disney();
        dreamworks();
    }
}
