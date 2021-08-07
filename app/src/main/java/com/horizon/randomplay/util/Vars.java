package com.horizon.randomplay.util;

import com.horizon.randomplay.components.movies.MoodMovieCollection;
import com.horizon.randomplay.components.movies.Movie;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.series.MoodsSeries;

public class Vars {
    public static Tuple<SeriesHolder.SeriesKind, Mood> series_choice = new Tuple<>(SeriesHolder.SeriesKind.ANYTHING, Mood.ANYTHING);
    public static Tuple<MoviesHolder.MovieKind, Mood> movie_choice = new Tuple<>(MoviesHolder.MovieKind.ANYTHING, Mood.ANYTHING);
    public static Tuple<MoodsSeries, Episode> s_historyCompon = new Tuple<>(null, null);
    public static Tuple<MoodMovieCollection, Movie> m_historyCompon = null;
    public static SeriesHolder.SeriesKind prefInfoChoice = null;
    public static boolean isSeries = true;
}
