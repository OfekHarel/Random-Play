package com.horizon.randomplay.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.horizon.randomplay.SeriesHolder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class SharedData {
    private static SharedData instance = null;

    private final String HISTORY = "HISTORY";
    private final String CHOSEN = "CHOSEN";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Gson gson;

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
        final String NAME = "mem";
        this.sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
        this.gson = new Gson();

        final String FIRST_TIME = "firstTime";
        final boolean isFirstTime = this.sharedPreferences.getBoolean(FIRST_TIME, true);

        if (isFirstTime) {
            this.editor.putBoolean(FIRST_TIME, false);
            this.editor.putString(this.HISTORY, null);
            this.editor.putString(this.CHOSEN, null);
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

    private ArrayList<String> initChosen() {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(SeriesHolder.SeriesKind.getNames()));
        arr.remove(SeriesHolder.SeriesKind.ANYTHING.getName());
        return arr;
    }

    public void setChosen(ArrayList<String> arrayList) {
        String json = this.gson.toJson(arrayList);
        this.editor.putString(this.CHOSEN, json);
        this.editor.apply();
    }

    public ArrayList<String> getChosen() {
        String json = this.sharedPreferences.getString(this.CHOSEN, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arr = this.gson.fromJson(json, type);
        if (arr == null) {
            arr = initChosen();
        }
        return arr;
    }

    public void setHistory(ArrayList<String> arrayList) {
        String json = this.gson.toJson(arrayList);
        this.editor.putString(this.HISTORY, json);
        this.editor.apply();
    }

    public ArrayList<String> getHistory() {
        String json = this.sharedPreferences.getString(this.HISTORY, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arr = this.gson.fromJson(json, type);
        if (arr == null) {
            arr = new ArrayList<>();
        }
        return arr;
    }

    public void addHistory(String history) {
        ArrayList<String> arrayList = getHistory();
        arrayList.add(history);
        setHistory(arrayList);
    }
}