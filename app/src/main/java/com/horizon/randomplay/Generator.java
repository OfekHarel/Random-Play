package com.horizon.randomplay;

import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.MoodsSeries;
import com.horizon.randomplay.util.DynamicArray;
import com.horizon.randomplay.util.Tuple;

import java.util.Random;

public class Generator {
    private static Generator instance = null;

    private final DynamicArray<String> recentEpisodes;
    private final DynamicArray<String> recentSeries;
    private final Random randomGen;

    public static Generator getInstance() {
        return instance == null ? instance = new Generator() : instance;
    }

    private Generator() {
        this.randomGen = new Random();
        this.recentEpisodes = new DynamicArray<>(5);
        this.recentSeries = new DynamicArray<>((SeriesHolder.SeriesKind.values().length - 1) / 3);
    }

    private MoodsSeries genRandSeries() {
        int seriesNum;
        do {
            seriesNum = this.randomGen.nextInt(SeriesHolder.SeriesKind.values().length - 1);

        } while (SeriesHolder.SeriesKind.values()[seriesNum].equals(SeriesHolder.SeriesKind.ANYTHING)
                || this.recentSeries.isExist(SeriesHolder.SeriesKind.values()[seriesNum].getName()));
        return SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.values()[seriesNum].getName());
    }

    private MoodsSeries genRandSeries(Mood mood) {
        int seriesNum;
        do {
            seriesNum = this.randomGen.nextInt(SeriesHolder.getSeriesBasedOnMood(mood).size() - 1);

        } while (SeriesHolder.SeriesKind.values()[seriesNum].equals(SeriesHolder.SeriesKind.ANYTHING));
        return SeriesHolder.getAllSeries().get(SeriesHolder.getSeriesBasedOnMood(mood).get(seriesNum).getName());
    }

    private Episode genRandEpisode(MoodsSeries series) {
        int seasonNum;
        int episodeNum;
        do {
            seasonNum = this.randomGen.nextInt(series.getSeasons().size() - 1);
            episodeNum = this.randomGen.nextInt(series.getSeasons().get(seasonNum).getEpisodes().size() - 1);

        } while (this.recentEpisodes.isExist(series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum).getName()));

        return series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum);
    }

    private Episode genRandEpisode(MoodsSeries series, Mood mood) {
        int randNum;
        do {
            randNum = this.randomGen.nextInt(series.getEpisodesByMoods(mood).size() - 1);
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
