package com.horizon.randomplay;

import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.MoodsSeries;
import com.horizon.randomplay.components.Series;
import com.horizon.randomplay.util.DynamicArray;
import com.horizon.randomplay.util.Tuple;

import java.util.Random;

public class Genrator {
    private static Genrator instance = null;

    private MoodsSeries series;
    private Mood mood;
    private final DynamicArray<Episode> recent;
    private final Random randomGen;


    public static Genrator getInstance() {
        return instance == null? instance = new Genrator(): instance;
    }

    private Genrator() {
        this.randomGen = new Random();
        this.recent = new DynamicArray<>(6);
    }

    public Episode genEpisode(MoodsSeries series, Mood mood) {
        this.series = series;
        this.mood = mood;

        Episode episode = null;
        if (this.mood.equals(Mood.ANYTHING)) {
            int seasonNum = 0;
            int episodeNum = 0;
            do {
                seasonNum = this.randomGen.nextInt(this.series.getSeasons().size() - 1);
                episodeNum = this.randomGen.nextInt(this.series.getSeasons().get(seasonNum).getEpisodes().size() - 1);
            } while (this.recent.isExist(this.series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum)));

            episode = this.series.getSeasons().get(seasonNum).getEpisodes().get(episodeNum);

        } else {
            int randNum = 0;
            do {
                randNum = this.randomGen.nextInt(this.series.getEpisodesByMoods(mood).size() - 1);
            } while (this.recent.isExist(this.series.getEpisodesByMoods(this.mood).get(randNum)));

            episode = this.series.getEpisodesByMoods(this.mood).get(randNum);

        }
        this.recent.insert(episode);
        return episode;
    }
}
