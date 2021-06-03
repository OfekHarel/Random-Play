package com.horizon.randomplay.components;

import com.horizon.randomplay.SeriesHolder;

import java.util.ArrayList;
import java.util.Arrays;

public enum Mood {
    ANYTHING("Literally Anything"),
    CRY("To Cry"),
    LAUGH("To Laugh"),
    HAPPY("When Your Happy"),
    ROMANTIC("Romantic Vibes"),
    BEST("Premium Super Deluxe +");

    private final String name;
    Mood(String name) {
        this.name = name;
    }

    public static ArrayList<String> getNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < Mood.values().length; i++) {
            arrayList.add(Mood.values()[i].getName());
        }
        return arrayList;
    }

    public static ArrayList<String> getNames(ArrayList<Mood> moods) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < moods.size(); i++) {
            arrayList.add(moods.get(i).getName());
        }
        return arrayList;
    }

    public String getName() {
        return name;
    }

    public static Mood getByValue(String value) {
        for (int i = 0; i < Mood.values().length; i++) {
            if (Mood.values()[i].getName().equals(value)) {
                return Mood.values()[i];
            }
        }
        return null;
    }
}
