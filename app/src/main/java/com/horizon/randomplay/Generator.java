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
import com.horizon.randomplay.util.ClosedBoundDynamicArray;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Tuple;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Generator {
    @SuppressLint("StaticFieldLeak")
    private static Generator instance = null;

    private final ClosedBoundDynamicArray<String> recentEpisodes;
    private final ClosedBoundDynamicArray<String> recentSeries;
    private final ClosedBoundDynamicArray<String> recentMovies;
    private final ClosedBoundDynamicArray<String> recentMovieCollections;
    private final Random randomGen;
    private final Context context;

    private final SeriesHandler seriesHandler = new SeriesHandler();
    private final MovieHandler movieHandler = new MovieHandler();

    public final static int RECENT_BOUND = 3;

    public static Generator getInstance(Context context) {
        return instance == null ? instance = new Generator(context) : instance;
    }

    private Generator(Context context) {
        this.randomGen = new Random();
        this.recentEpisodes = new ClosedBoundDynamicArray<>(RECENT_BOUND);
        this.recentSeries = new ClosedBoundDynamicArray<>(RECENT_BOUND);
        this.recentMovies = new ClosedBoundDynamicArray<>(RECENT_BOUND);
        this.recentMovieCollections = new ClosedBoundDynamicArray<>(RECENT_BOUND);
        this.context = context;

        MoviesHolder.init(context);
        SeriesHolder.init(context);
        SharedData.getInstance(context);
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
                recentMovieCollections.retrieveSize();
                return MoviesHolder.getAllMovies().get(chosen.get(movieNum));
            }
            return null;
        }

        private MoodMovieCollection genRandMovieCollection(Mood mood) {
            int movieNum;
            ArrayList<MoodMovieCollection> moodsMovieCollection = MoviesHolder.getMoviesBasedOnMood(mood);
            if (moodsMovieCollection.size() == 1) {
                return moodsMovieCollection.get(0);
            }
            do {
                movieNum = numGen(moodsMovieCollection.size());
            } while (recentMovieCollections.isExist(MoviesHolder.MovieKind.ANYTHING.getName()) ||
                    Objects.equals(MoviesHolder.MovieKind.getByValue(moodsMovieCollection.get(movieNum).getName()), MoviesHolder.MovieKind.ANYTHING));

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
            do {
                movieNum = numGen(moods.size());
            } while (recentMovies.isExist(moods.get(movieNum).getName()));

            return moods.get(movieNum);
        }

        public Tuple<MoodMovieCollection, Movie> generate(MoviesHolder.MovieKind movieKind, Mood mood) {
            MoodMovieCollection collection = MoviesHolder.getAllMovies().get(movieKind.getName());
            Movie movie;
            if (movieKind.equals(MoviesHolder.MovieKind.ANYTHING)) {
                collection = mood.equals(Mood.ANYTHING) ? genRandMovieCollection() : genRandMovieCollection(mood);
                assert collection != null;
                recentMovieCollections.insert(collection.getName());
            }

            // Safety checkup
            if (!mood.equals(Mood.ANYTHING)) {
                if (Objects.requireNonNull(MoviesHolder.getAllMovies()
                        .get(movieKind.getName())).getMovieByMoods(mood).isEmpty()) {
                    mood = Mood.ANYTHING;
                }
            }

            assert collection != null;
            if (mood.equals(Mood.ANYTHING)) {
                movie = genRandMovie(collection);
            } else {
                movie = genRandMovie(collection, mood);
            }
            recentMovies.insert(movie.getName());

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
                recentSeries.retrieveSize();

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
            int seasonNum, episodeNum;
            do {
                seasonNum = numGen(series.getSeasons().size());
                episodeNum = numGen(series.getSeasons().get(seasonNum).getEpisodes().size());

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
                series = mood.equals(Mood.ANYTHING) ? genRandSeries() : genRandSeries(mood);
                assert series != null;
                recentSeries.insert(series.getName());
            }

            // Safety checkup
            if (!mood.equals(Mood.ANYTHING)) {
                if (Objects.requireNonNull(SeriesHolder.getAllSeries()
                        .get(seriesKind.getName())).getEpisodesByMoods(mood).isEmpty()) {
                    mood = Mood.ANYTHING;
                }
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

    /**
     * @param bound - random number between 0 - (bound-1)
     * @return the random number
     */
    private int numGen(int bound) {
        return bound < 1 ? 0 : randomGen.nextInt(bound);
    }
}
