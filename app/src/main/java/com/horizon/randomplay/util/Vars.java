package com.horizon.randomplay.util;

import com.horizon.randomplay.components.movies.MoodMovieCollection;
import com.horizon.randomplay.components.movies.Movie;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.series.MoodsSeries;

public class Vars {
    public static boolean DEBUG = false;

    public static Tuple<SeriesHolder.SeriesKind, Mood> series_choice = new Tuple<>(SeriesHolder.SeriesKind.ANYTHING, Mood.ANYTHING);
    public static Tuple<MoviesHolder.MovieKind, Mood> movie_choice = new Tuple<>(MoviesHolder.MovieKind.ANYTHING, Mood.ANYTHING);
    public static Tuple<MoodsSeries, Episode> s_historyComponent = new Tuple<>(null, null);
    public static Tuple<MoodMovieCollection, Movie> m_historyComponent = null;
    public static FragmentAdapter.Tabs currentTab = FragmentAdapter.Tabs.SERIES;
    public static boolean isSeries = true;
    public static boolean infoFlag = false;
}
