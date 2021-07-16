package com.horizon.randomplay.components.movies;

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

    public MoodMovieCollection(String name) {
        this(name, null);
    }

    public MoodMovieCollection(MovieCollection collection) {
        this(collection.getName(), collection.getMovies());
    }

    public final void setMood(Mood mood, int... movies) {
        ArrayList<Movie> es = new ArrayList<>();
        for (int movie : movies) {
            es.add(fromIndexToMovie(movie));
        }
        Objects.requireNonNull(this.moviesMoods.get(mood)).addAll(es);
    }

    public void addMood(Mood mood) {
        Objects.requireNonNull(this.moviesMoods.get(mood)).add(new Movie( "0"));
    }

    private Movie fromIndexToMovie(int movie) {
        return this.getMovies().get(movie);
    }

    public ArrayList<Movie> getMovieByMoods(Mood mood) {
        return this.moviesMoods.get(mood);
    }

    public ArrayList<Mood> getAvailableMoods() {
        ArrayList<Mood> arrayList = new ArrayList<>();
        for (int i = 0; i < Mood.values().length; i++) {
            if (this.moviesMoods.get(Mood.values()[i]).size() > 0) {
                arrayList.add(Mood.values()[i]);
            }
        }
        return arrayList;
    }

    public void removeMoods() {
        for (Mood mood: Mood.values()) {
            Objects.requireNonNull(moviesMoods.get(mood)).clear();
        }
    }
}
