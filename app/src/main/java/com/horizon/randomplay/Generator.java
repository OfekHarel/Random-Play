package com.horizon.randomplay;

import android.annotation.SuppressLint;
import android.content.Context;

import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.series.MoodsSeries;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.util.DynamicArray;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Tuple;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    @SuppressLint("StaticFieldLeak")
    private static Generator instance = null;

    private final DynamicArray<String> recentEpisodes;
    private final DynamicArray<String> recentSeries;
    private final Random randomGen;
    private final Context context;

    public static Generator getInstance(Context context) {
        return instance == null ? instance = new Generator(context) : instance;
    }

    private Generator(Context context) {
        this.randomGen = new Random();
        this.recentEpisodes = new DynamicArray<>(5);
        this.recentSeries = new DynamicArray<>((SeriesHolder.SeriesKind.values().length - 1) / 3);
        this.context = context;
    }

    private MoodsSeries genRandSeries() {
        int seriesNum;
        ArrayList<String> chosen = SharedData.getInstance(context).getChosen();
        if (chosen != null) {
            this.recentSeries.changeSize(chosen.size());
            do {
                seriesNum = this.randomGen.nextInt(chosen.size());
            } while (chosen.get(seriesNum).equals(SeriesHolder.SeriesKind.ANYTHING.getName()));
            return SeriesHolder.getAllSeries().get(SharedData.getInstance(context).getChosen().get(seriesNum));
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
            seriesNum = this.randomGen.nextInt(moodsSeries.size());
        } while (SeriesHolder.SeriesKind.getByValue(moodsSeries.get(seriesNum).getName()).equals(SeriesHolder.SeriesKind.ANYTHING));
        return SeriesHolder.getAllSeries().get(moodsSeries.get(seriesNum).getName());
    }

    private Episode genRandEpisode(MoodsSeries series) {
        int seasonNum;
        int episodeNum;
        do {
            seasonNum = this.randomGen.nextInt(series.getSeasons().size());
            episodeNum = this.randomGen.nextInt(series.getSeasons().get(seasonNum).getEpisodes().size() - 1);

        } while (this.recentEpisodes.isExist(series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum).getName()));

        return series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum);
    }

    private Episode genRandEpisode(MoodsSeries series, Mood mood) {
        int randNum;
        do {
            randNum = this.randomGen.nextInt(series.getEpisodesByMoods(mood).size());
        } while (this.recentEpisodes.isExist(series.getEpisodesByMoods(mood).get(randNum).getName()));

        return series.getEpisodesByMoods(mood).get(randNum);
    }

    public Tuple<MoodsSeries, Episode> genEpisode(SeriesHolder.SeriesKind seriesKind, Mood mood) {
        MoodsSeries series = SeriesHolder.getAllSeries().get(seriesKind.getName());
        Episode episode;
        if (seriesKind.equals(SeriesHolder.SeriesKind.ANYTHING)) {
            series = mood.equals(Mood.ANYTHING)? genRandSeries(): genRandSeries(mood);
            this.recentSeries.insert(series.getName());
        }

        episode = mood.equals(Mood.ANYTHING)? genRandEpisode(series): genRandEpisode(series, mood);
        this.recentEpisodes.insert(episode.getName());
        return new Tuple<>(series, episode);
    }
}
