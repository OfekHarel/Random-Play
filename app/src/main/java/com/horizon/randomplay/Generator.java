package com.horizon.randomplay;

import android.content.Context;
import android.util.Log;

import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.MoodsSeries;
import com.horizon.randomplay.util.DynamicArray;
import com.horizon.randomplay.util.Tuple;

import java.util.Random;

public class Generator {
    private static Generator instance = null;

    private final DynamicArray<Episode> recentEpisodes;
    private final DynamicArray<String> recentSeries;
    private final Random randomGen;


    public static Generator getInstance() {
        return instance == null? instance = new Generator(): instance;
    }

    private Generator() {
        this.randomGen = new Random();
        this.recentEpisodes = new DynamicArray<>(6);

        this.recentSeries = new DynamicArray<>((SeriesHolder.SeriesKind.values().length - 1) / 3);
    }

    public Tuple<MoodsSeries, Episode> genEpisode(SeriesHolder.SeriesKind seriesKind, Mood mood) {
        MoodsSeries series = SeriesHolder.getAllSeries().get(seriesKind.getName());

        if (seriesKind.equals(SeriesHolder.SeriesKind.ANYTHING)) {
            int seriesNum;
            do {
                seriesNum = this.randomGen.nextInt(SeriesHolder.SeriesKind.values().length - 1);

            }  while(SeriesHolder.SeriesKind.values()[seriesNum].equals(SeriesHolder.SeriesKind.ANYTHING)
                    || this.recentSeries.isExist(SeriesHolder.SeriesKind.values()[seriesNum].getName()));
            series = SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.values()[seriesNum].getName());
            mood = Mood.ANYTHING;
            }
            this.recentSeries.insert(series.getName());

        Episode episode = null;
        if (mood.equals(Mood.ANYTHING)) {
            int seasonNum;
            int episodeNum;
            do {
                seasonNum = this.randomGen.nextInt(series.getSeasons().size() - 1);
                episodeNum = this.randomGen.nextInt(series.getSeasons().get(seasonNum).getEpisodes().size() - 1);

            } while (this.recentEpisodes.isExist(series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum)));

            episode = series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum);

        } else if (series.getAvailableMoods().size() > 1) {
            int randNum;
            do {
                randNum = this.randomGen.nextInt(series.getEpisodesByMoods(mood).size() - 1);
            } while (this.recentEpisodes.isExist(series.getEpisodesByMoods(mood).get(randNum)));

            episode = series.getEpisodesByMoods(mood).get(randNum);

        }
        this.recentEpisodes.insert(episode);
        return new Tuple<>(series, episode);
    }
}
