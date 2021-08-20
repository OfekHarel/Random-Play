package com.horizon.randomplay.components;

import java.util.ArrayList;

public enum Mood {
    ANYTHING("Literally Anything"),
    CRY("To Cry"),
    LAUGH("To Laugh"),
    HAPPY("Feeling Good"),
    ROMANTIC("Romantic Vibes"),
    BEST("Premium Super Deluxe +"),
    SMART("High IQ WARNING!"),
    WAR("PEW PEW BOOM BOOM"),
    NOSTALGIC("Relive Your Childhood"),
    SOUND_SCORE("For The Sound Of It");

    private final String name;

    Mood(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return getName();
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
