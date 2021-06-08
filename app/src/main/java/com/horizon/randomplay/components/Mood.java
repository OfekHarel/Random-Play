package com.horizon.randomplay.components;

import java.util.ArrayList;

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

    public static String[] getNames() {
        String[] arrayList = new String[Mood.values().length];
        for (int i = 0; i < Mood.values().length; i++) {
            arrayList[i] = Mood.values()[i].getName();
        }
        return arrayList;
    }

    public static String[] getNames(ArrayList<Mood> moods) {
        String[] arrayList = new String[moods.size()];
        for (int i = 0; i < moods.size(); i++) {
            arrayList[i] = moods.get(i).getName();
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
