package com.horizon.randomplay.movies;

import android.content.Context;

import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.movies.MoodMovieCollection;
import com.horizon.randomplay.components.movies.Movie;
import com.horizon.randomplay.components.movies.MovieCollection;
import com.horizon.randomplay.util.SharedData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MoviesHolder {
    public enum MovieKind {
        ANYTHING("Literally Anything"),
        MARVEL("Marvel"),
        DISNEY_ANIM("Animated Disney"),
        LORD_OF_THE_RINGS("Lord of the Rings World"),
        HARRY_POTTER("Harry Potter");

        private final String name;
        MovieKind(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static String[] getNames() {
            String[] names = new String[MoviesHolder.MovieKind.values().length];
            for (int i = 0; i < MoviesHolder.MovieKind.values().length; i++) {
                names[i] = MoviesHolder.MovieKind.values()[i].getName();
            }
            return names;
        }

        public static MoviesHolder.MovieKind getByValue(String value) {
            for (int i = 0; i < MoviesHolder.MovieKind.values().length; i++) {
                if (MoviesHolder.MovieKind.values()[i].getName().equals(value)) {
                    return MoviesHolder.MovieKind.values()[i];
                }
            }
            return null;
        }
    }

    private static final Map<String, MoodMovieCollection> allMovies = new HashMap<>();
    private static final String ID_SEP = "?";

    public static void init(Context context) {
        try {
            for (int i = 0; i < MovieKind.values().length; i++) {
                if (!MovieKind.values()[i].equals(MovieKind.ANYTHING)) {
                    allMovies.put(MovieKind.getNames()[i],
                            new MoodMovieCollection(getMovieCollectionFromRaw(MovieKind.values()[i].getName(), context)));
                }
            }

            initAllMovies();
            MovieMoodsHolder.init();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private static MovieCollection getMovieCollectionFromRaw(String name, Context context) throws IOException {
        InputStream input = context.getResources().openRawResource(context.getResources()
                .getIdentifier("m_" + name.replace(" ", "_").toLowerCase(),
                        "raw", context.getPackageName()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        MovieCollection collection = new MovieCollection(name);

        String data;
        do {
            data = reader.readLine();
            if (data == null) {
                continue;
            }
            if (!data.isEmpty()) {
                if (data.contains(ID_SEP)) {
                    int sepIndex = data.indexOf(ID_SEP);
                    String movieName = data.substring(0, sepIndex - 1);
                    try {
                        Long id = Long.parseLong(data.substring(sepIndex + 1).replace(" ", ""));
                        Movie m = new Movie(movieName);
                        m.setMovieID(id);
                        collection.addMovie(m);
                    }
                    catch (Exception ignored) {

                    }
                } else {
                    collection.addMovie(new Movie(data));
                }
            }
        } while (data != null);

        return collection;
    }

    private static void initAllMovies() {
        allMovies.put(MovieKind.ANYTHING.getName(),
                new MoodMovieCollection(new MovieCollection(MovieKind.ANYTHING.getName())));

        for (Mood mood: Mood.values()) {
            if (!mood.equals(Mood.ANYTHING)) {
                Objects.requireNonNull(allMovies.get(MovieKind.ANYTHING.getName())).addMood(mood);
            }
        }
    }

    public static Map<String, MoodMovieCollection> getAllMovies() {
        return allMovies;
    }

    public static ArrayList<Mood> getAllAvailableMoods() {
        ArrayList<String> choose = SharedData.getInstance().getMovieHandler().getChosen();
        ArrayList<Mood> ret = new ArrayList<>();

        for (String s: choose) {
            ret.addAll(Objects.requireNonNull(allMovies.get(s)).getAvailableMoods());
        }
        return  (ArrayList<Mood>) ret.stream().distinct().collect(Collectors.toList());
    }

    public static ArrayList<MoodMovieCollection> getSeriesBasedOnMood(Mood mood) {
        ArrayList<MoodMovieCollection> arrayList = new ArrayList<>();

        for (String s: SharedData.getInstance().getMovieHandler().getChosen()) {
            if (Objects.requireNonNull(allMovies.get(s)).getAvailableMoods().contains(mood)) {
                arrayList.add(allMovies.get(s));
            }
        }

        ArrayList<String> sData = SharedData.getInstance().getMovieHandler().getChosen();
        ArrayList<MoodMovieCollection> fromSData = new ArrayList<>();
        for (int i = 0; i < sData.size(); i++) {
            fromSData.add(allMovies.get(sData.get(i)));
        }

        arrayList.retainAll(fromSData);
        return arrayList;
    }

    public static ArrayList<String> getModesByMovie(MoodMovieCollection collection, Movie m) {
        ArrayList<String> moods = new ArrayList<>();

        for (Mood mood: collection.getAvailableMoods()) {
            for (Movie moodMov: collection.getMovieByMoods(mood)) {
                if (moodMov.getName().equals(m.getName())) {
                    moods.add(mood.getName());
                }
            }
        }
        return moods;
    }
}
