package com.horizon.randomplay.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.horizon.randomplay.BuildConfig;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.series.SeriesHolder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class SharedData {
    private static SharedData instance = null;

    private final String HISTORY = "HISTORY";
    private final String S_CHOOSE = "S_CHOOSE";
    private final String M_CHOOSE = "M_CHOOSE";
    private final String PREF_VERSION_CODE_KEY = "VERSION_CODE";
    private final String FIRST_TIME = "FIRST_TIME";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Gson gson;

    private final SeriesHandler seriesHandler = new SeriesHandler();
    private final MovieHandler movieHandler = new MovieHandler();


    /**
     * Open the shared memory and its entries.
     *
     * @param context the context which it called from
     */
    @SuppressLint("CommitPrefEdits")
    private SharedData(Context context) {
        /*
         * Entry names
         */
        final String NAME = "RP_MEM";
        this.sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
        this.gson = new Gson();

        final boolean isFirstTime = this.sharedPreferences.getBoolean(FIRST_TIME, true);

        if (isFirstTime) {
            this.editor.putBoolean(FIRST_TIME, false);
            this.editor.putString(this.HISTORY, null);
            this.editor.putString(this.S_CHOOSE, null);
            this.editor.putString(this.M_CHOOSE, null);
            this.editor.apply();
        }
    }

    /**
     * @param context the context which it called from
     * @return The instance
     */
    public static SharedData getInstance(Context context) {
        if (instance == null) {
            instance = new SharedData(context);
        }
        return instance;
    }

    public static SharedData getInstance() {
        return instance;
    }

    public SeriesHandler getSeriesHandler() {
        return seriesHandler;
    }

    public MovieHandler getMovieHandler() {
        return movieHandler;
    }

    private interface SharedMem {
        ArrayList<String> initChosen();

        void setChosen(ArrayList<String> arrayList);

        ArrayList<String> getChosen();

        void setHistory(ArrayList<String> arrayList);

        ArrayList<String> getHistory();

        void addHistory(String history);
    }

    public class MovieHandler implements SharedMem {
        @Override
        public ArrayList<String> initChosen() {
            ArrayList<String> arr = new ArrayList<>(Arrays.asList(MoviesHolder.MovieKind.getNames()));
            arr.remove(MoviesHolder.MovieKind.ANYTHING.getName());
            return arr;
        }

        @Override
        public void setChosen(ArrayList<String> arrayList) {
            String json = gson.toJson(arrayList);
            editor.putString(M_CHOOSE, json);
            editor.apply();
        }

        @Override
        public ArrayList<String> getChosen() {
            String json = sharedPreferences.getString(M_CHOOSE, null);
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            ArrayList<String> arr = gson.fromJson(json, type);
            if (arr == null) {
                arr = initChosen();
            }
            return arr;
        }

        @Override
        public void setHistory(ArrayList<String> arrayList) {
            String json = gson.toJson(arrayList);
            editor.putString(HISTORY, json);
            editor.apply();
        }

        @Override
        public ArrayList<String> getHistory() {
            String json = sharedPreferences.getString(HISTORY, null);
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            ArrayList<String> arr = gson.fromJson(json, type);
            if (arr == null) {
                arr = new ArrayList<>();
            }
            return arr;
        }

        @Override
        public void addHistory(String history) {
            ArrayList<String> arrayList = getHistory();
            arrayList.add(history);
            setHistory(arrayList);
        }
    }

    public class SeriesHandler implements SharedMem {
        @Override
        public ArrayList<String> initChosen() {
            ArrayList<String> arr = new ArrayList<>(Arrays.asList(SeriesHolder.SeriesKind.getNames()));
            arr.remove(SeriesHolder.SeriesKind.ANYTHING.getName());
            return arr;
        }

        @Override
        public void setChosen(ArrayList<String> arrayList) {
            String json = gson.toJson(arrayList);
            editor.putString(S_CHOOSE, json);
            editor.apply();
        }

        @Override
        public ArrayList<String> getChosen() {
            String json = sharedPreferences.getString(S_CHOOSE, null);
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            ArrayList<String> arr = gson.fromJson(json, type);
            if (arr == null) {
                arr = initChosen();
            }
            return arr;
        }

        @Override
        public void setHistory(ArrayList<String> arrayList) {
            String json = gson.toJson(arrayList);
            editor.putString(HISTORY, json);
            editor.apply();
        }

        @Override
        public ArrayList<String> getHistory() {
            String json = sharedPreferences.getString(HISTORY, null);
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            ArrayList<String> arr = gson.fromJson(json, type);
            if (arr == null) {
                arr = new ArrayList<>();
            }
            return arr;
        }

        @Override
        public void addHistory(String history) {
            ArrayList<String> arrayList = getHistory();
            arrayList.add(history);
            setHistory(arrayList);
        }
    }

    public boolean isFirstRunAfterInstall(Context context) {
        int currentVersionCode = BuildConfig.VERSION_CODE;

        final int DOES_NOT_EXIST = -1;
        int savedVersionCode = this.sharedPreferences.getInt(PREF_VERSION_CODE_KEY, DOES_NOT_EXIST);

        // Edit to clear s_preff
        if (savedVersionCode < Vars.CLEAN_VERS) {
            this.sharedPreferences.getBoolean(FIRST_TIME, true);
            sharedPreferences.edit().clear().apply();
            new SharedData(context);
        }

        this.sharedPreferences.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();

        return savedVersionCode == DOES_NOT_EXIST;
    }
}
