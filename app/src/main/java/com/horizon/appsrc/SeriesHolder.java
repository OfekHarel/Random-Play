package com.horizon.appsrc;

import com.horizon.appsrc.components.Mood;
import com.horizon.appsrc.components.MoodsSeries;
import com.horizon.appsrc.util.Tuple;

import java.io.FileNotFoundException;

public class SeriesHolder {

    private static SeriesHolder inst = null;

    private MoodsSeries friends = null;
    private MoodsSeries theBigBangTheory = null;
    private MoodsSeries howIMetYourMother = null;

    private SeriesHolder() {
        try {
            this.friends = new MoodsSeries(SeriesFromData.get("Friends"));
            setFriendsMoods();

            this.theBigBangTheory = new MoodsSeries(SeriesFromData.get("The Big Bang Theory"));
            setTheBigBangTheoryMoods();

            this.howIMetYourMother = new MoodsSeries(SeriesFromData.get("How I Met Your Mother"));
            setHowIMetYourMotherMoods();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static SeriesHolder getInstance() {
        if (inst == null) {
            inst = new SeriesHolder();
        }
        return inst;
    }

    public MoodsSeries getFriends() {
        return friends;
    }

    public MoodsSeries getHowIMetYourMother() {
        return howIMetYourMother;
    }

    public MoodsSeries getTheBigBangTheory() {
        return theBigBangTheory;
    }

    @SuppressWarnings("unchecked")
    private void setFriendsMoods() {
        this.friends.setMood(Mood.CRY, new Tuple<>(3, 16), new Tuple<>(4, 12), new Tuple<>(5, 3), new Tuple<>(6, 24),
                new Tuple<>(6, 25), new Tuple<>(9, 21), new Tuple<>(10, 8), new Tuple<>(10, 17));

        this.friends.setMood(Mood.LAUGH, new Tuple<>(1, 13), new Tuple<>(2, 23), new Tuple<>(3, 8), new Tuple<>(4, 20),
                new Tuple<>(5, 14), new Tuple<>(6, 8), new Tuple<>(6, 17), new Tuple<>(7, 6), new Tuple<>(8, 15),
                new Tuple<>(9, 7), new Tuple<>(10, 3));

        this.friends.setMood(Mood.HAPPY, new Tuple<>(1, 10), new Tuple<>(2, 11), new Tuple<>(3, 9), new Tuple<>(5, 13),
                new Tuple<>(6, 7), new Tuple<>(7, 7), new Tuple<>(8, 6), new Tuple<>(9, 4), new Tuple<>(10, 4));

        this.friends.setMood(Mood.ROMANTIC, new Tuple<>(2, 7), new Tuple<>(2, 14), new Tuple<>(2, 15),
                new Tuple<>(3, 13), new Tuple<>(6, 25), new Tuple<>(9, 19), new Tuple<>(10, 12), new Tuple<>(10, 17));

        this.friends.setMood(Mood.BEST, new Tuple<>(24, 1), new Tuple<>(14, 2), new Tuple<>(12, 4),
                new Tuple<>(8, 5), new Tuple<>(14, 5), new Tuple<>(24, 6), new Tuple<>(25, 6), new Tuple<>(2, 8),
                new Tuple<>(4, 8), new Tuple<>(9, 8), new Tuple<>(17, 10));
    }

    @SuppressWarnings("unchecked")
    private void setHowIMetYourMotherMoods() {
        this.howIMetYourMother.setMood(Mood.CRY, new Tuple<>(6, 13), new Tuple<>(6, 14), new Tuple<>(6, 19),
                new Tuple<>(7, 10), new Tuple<>(7, 12), new Tuple<>(8, 20), new Tuple<>(9, 16), new Tuple<>(9, 23),
                new Tuple<>(9, 24));

        this.howIMetYourMother.setMood(Mood.LAUGH, new Tuple<>(1, 10), new Tuple<>(2, 7), new Tuple<>(4, 4),
                new Tuple<>(5, 8), new Tuple<>(6, 22), new Tuple<>(7, 14), new Tuple<>(7, 20));

        this.howIMetYourMother.setMood(Mood.HAPPY, new Tuple<>(1, 10), new Tuple<>(2, 11), new Tuple<>(2, 21),
                new Tuple<>(7, 23), new Tuple<>(8, 12));

        this.howIMetYourMother.setMood(Mood.BEST, new Tuple<>(22, 1), new Tuple<>(9, 2), new Tuple<>(13, 3),
                new Tuple<>(2, 4), new Tuple<>(4, 4), new Tuple<>(13, 4), new Tuple<>(14, 4), new Tuple<>(8, 5),
                new Tuple<>(12, 5), new Tuple<>(13, 6));
    }

    @SuppressWarnings("unchecked")
    private void setTheBigBangTheoryMoods() {
        this.theBigBangTheory.setMood(Mood.BEST, new Tuple<>(24, 12), new Tuple<>(24, 11), new Tuple<>(24, 10),
                new Tuple<>(11, 9), new Tuple<>(15, 8), new Tuple<>(9, 7), new Tuple<>(13, 6), new Tuple<>(24, 5),
                new Tuple<>(1, 4), new Tuple<>(23, 3), new Tuple<>(11, 2), new Tuple<>(17, 1));
    }
}