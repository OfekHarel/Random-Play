package com.horizon.randomplay;

import android.annotation.SuppressLint;
import android.content.Context;

import com.horizon.randomplay.components.movies.MoodMovieCollection;
import com.horizon.randomplay.components.movies.Movie;
import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.series.MoodsSeries;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.util.DynamicArray;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Generator {
    @SuppressLint("StaticFieldLeak")
    private static Generator instance = null;

    private final DynamicArray<String> recentEpisodes;
    private final DynamicArray<String> recentSeries;
    private final DynamicArray<String> recentMovies;
    private final DynamicArray<String> recentMovieCollections;
    private final Random randomGen;
    private final Context context;

    private final SeriesHandler seriesHandler = new SeriesHandler();
    private final MovieHandler movieHandler = new MovieHandler();

    public static Generator getInstance(Context context) {
        return instance == null ? instance = new Generator(context) : instance;
    }

    private Generator(Context context) {
        this.randomGen = new Random();
        this.recentEpisodes = new DynamicArray<>(5);
        this.recentSeries = new DynamicArray<>(DynamicArray.getIdealSize(SeriesHolder.SeriesKind.values().length));
        this.recentMovies = new DynamicArray<>(5);
        this.recentMovieCollections  = new DynamicArray<>(DynamicArray.getIdealSize(MoviesHolder.MovieKind.values().length));
        this.context = context;
    }

    public SeriesHandler getSeriesHandler() {
        return seriesHandler;
    }

    public MovieHandler getMovieHandler() {
        return movieHandler;
    }

    public class MovieHandler {
        private MoodMovieCollection genRandMovieCollection() {
            int movieNum;
            ArrayList<String> chosen = SharedData.getInstance(context).getMovieHandler().getChosen();
            if (chosen != null) {
                recentMovieCollections.changeSize(chosen.size());
                do {
                    movieNum = numGen(chosen.size());
                } while (chosen.get(movieNum).equals(MoviesHolder.MovieKind.ANYTHING.getName()) ||
                        recentMovieCollections.isExist(MoviesHolder.MovieKind.ANYTHING.getName()));
                return MoviesHolder.getAllMovies().get(chosen.get(movieNum));
            }
            return null;
        }

        private MoodMovieCollection genRandMovieCollection(Mood mood) {
            int movieNum;
            ArrayList<MoodMovieCollection> moodsMovieCollection = MoviesHolder.getSeriesBasedOnMood(mood);
            if (moodsMovieCollection.size() == 1) {
                return moodsMovieCollection.get(0);
            }
            do {
                movieNum = numGen(moodsMovieCollection.size());
            } while (Objects.equals(MoviesHolder.MovieKind.getByValue(moodsMovieCollection.get(movieNum).getName()), MoviesHolder.MovieKind.ANYTHING));
            return MoviesHolder.getAllMovies().get(moodsMovieCollection.get(movieNum).getName());
        }

        private Movie genRandMovie(MoodMovieCollection collection) {
            int movieNum;
            do {
                movieNum = numGen(collection.getMovies().size());
            } while (recentMovies.isExist(collection.getMovies().get(movieNum).getName()));

            return collection.getMovies().get(movieNum);
        }

        private Movie genRandMovie(MoodMovieCollection collection, Mood mood) {
            int movieNum;
            ArrayList<Movie> moods = collection.getMovieByMoods(mood);
            System.out.println(moods.toString());
            do {
                movieNum = numGen(moods.size());
            } while (recentMovies.isExist(moods.get(movieNum).getName()));
            return moods.get(movieNum);
        }

        public Tuple<MoodMovieCollection, Movie> generate(MoviesHolder.MovieKind movieKind, Mood mood) {
            MoodMovieCollection collection = MoviesHolder.getAllMovies().get(movieKind.getName());
            Movie movie;
            if (movieKind.equals(MoviesHolder.MovieKind.ANYTHING)) {
                collection = mood.equals(Mood.ANYTHING)? genRandMovieCollection(): genRandMovieCollection(mood);
                assert collection != null;
                recentSeries.insert(collection.getName());
            }

            assert collection != null;
            if (mood.equals(Mood.ANYTHING)) {
                movie = genRandMovie(collection);
            } else {
                movie = genRandMovie(collection, mood);
            }
            recentEpisodes.insert(movie.getName());
            return new Tuple<>(collection, movie);
        }
    }

    public class SeriesHandler {
        private MoodsSeries genRandSeries() {
            int seriesNum;
            ArrayList<String> chosen = SharedData.getInstance(context).getSeriesHandler().getChosen();
            if (chosen != null) {
                recentSeries.changeSize(chosen.size());
                do {
                    seriesNum = numGen(chosen.size());
                } while (chosen.get(seriesNum).equals(SeriesHolder.SeriesKind.ANYTHING.getName()) ||
                            recentSeries.isExist(SeriesHolder.SeriesKind.ANYTHING.getName()));
                return SeriesHolder.getAllSeries().get(SharedData.getInstance(context).getSeriesHandler().getChosen().get(seriesNum));
            }
            return null;
        }

        private MoodsSeries genRandSeries(Mood mood) {
            int seriesNum;
            ArrayList<MoodsSeries> moodsSeries = SeriesHolder.getSeriesBasedOnMood(mood);
            if (moodsSeries.size() == 1) {
                return moodsSeries.get(0);
            }
            do {
                seriesNum = numGen(moodsSeries.size());
            } while (Objects.equals(SeriesHolder.SeriesKind.getByValue(moodsSeries.get(seriesNum).getName()), SeriesHolder.SeriesKind.ANYTHING));
            return SeriesHolder.getAllSeries().get(moodsSeries.get(seriesNum).getName());
        }

        private Episode genRandEpisode(MoodsSeries series) {
            int seasonNum;
            int episodeNum;
            do {
                seasonNum = numGen(series.getSeasons().size());
                episodeNum = numGen(series.getSeasons().get(seasonNum).getEpisodes().size() - 1);

            } while (recentEpisodes.isExist(series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum).getName()));

            return series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum);
        }

        private Episode genRandEpisode(MoodsSeries series, Mood mood) {
            int randNum;
            ArrayList<Episode> moods = series.getEpisodesByMoods(mood);
            do {
                randNum = numGen(moods.size());
            } while (recentEpisodes.isExist(moods.get(randNum).getName()));

            return moods.get(randNum);
        }

        public Tuple<MoodsSeries, Episode> generate(SeriesHolder.SeriesKind seriesKind, Mood mood) {
            MoodsSeries series = SeriesHolder.getAllSeries().get(seriesKind.getName());
            Episode episode;
            if (seriesKind.equals(SeriesHolder.SeriesKind.ANYTHING)) {
                series = mood.equals(Mood.ANYTHING)? genRandSeries(): genRandSeries(mood);
                assert series != null;
                recentSeries.insert(series.getName());
            }

            assert series != null;
            if (mood.equals(Mood.ANYTHING)) {
                episode = genRandEpisode(series);
            } else {
                episode = genRandEpisode(series, mood);
            }
            recentEpisodes.insert(episode.getName());
            return new Tuple<>(series, episode);
        }
    }
    private int numGen(int threshold) {
        return threshold <= 0? 1 : randomGen.nextInt(threshold);
    }
}
