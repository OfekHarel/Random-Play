package com.horizon.randomplay.components.movies;

import com.horizon.randomplay.Generator;
import com.horizon.randomplay.components.Mood;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

public class MoodMovieCollection extends MovieCollection {

    private final Hashtable<Mood, ArrayList<Movie>> moviesMoods;

    public MoodMovieCollection(String name, ArrayList<Movie> movies) {
        super(name, movies);

        this.moviesMoods = new Hashtable<>();
        for (int i = 0; i < Mood.values().length; i++) {
            this.moviesMoods.put(Mood.values()[i], new ArrayList<>());
        }
    }

    public MoodMovieCollection(MovieCollection collection) {
        this(collection.getName(), collection.getMovies());
    }

    public final void setMood(Mood mood, int... movies) {
        if (movies.length <= Generator.RECENT_BOUND) {
            return;
        }

        ArrayList<Movie> es = new ArrayList<>();
        for (int movie : movies) {
            es.add(this.getMovies().get(movie - 1));
        }
        Objects.requireNonNull(this.moviesMoods.get(mood)).addAll(es);
    }

    public void addMood(Mood mood) {
        Objects.requireNonNull(this.moviesMoods.get(mood)).add(new Movie("0"));
    }

    public ArrayList<Movie> getMovieByMoods(Mood mood) {
        return this.moviesMoods.get(mood);
    }

    public ArrayList<Mood> getAvailableMoods() {
        ArrayList<Mood> arrayList = new ArrayList<>();
        for (int i = 0; i < Mood.values().length; i++) {
            if (Objects.requireNonNull(this.moviesMoods.get(Mood.values()[i])).size() > 0) {
                arrayList.add(Mood.values()[i]);
            }
        }
        return arrayList;
    }

    public void removeMoods() {
        for (Mood mood : Mood.values()) {
            Objects.requireNonNull(moviesMoods.get(mood)).clear();
        }
    }

    public Movie getMovieByString(String m) {
        for (Movie movie : getMovies()) {
            if (movie.getName().equals(m)) {
                return movie;
            }
        }
        return null;
    }
}
