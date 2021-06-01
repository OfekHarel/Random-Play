package com.horizon.appsrc.components;

import com.horizon.appsrc.util.Tuple;

import java.util.ArrayList;
import java.util.Hashtable;

public class MoodsSeries extends Series {

    private final Hashtable<Mood, ArrayList<Episode>> episodesMoods;

    public MoodsSeries(String name, ArrayList<Season> seasons) {
        super(name, seasons);

        this.episodesMoods = new Hashtable<>();
        for (int i = 0; i < Mood.values().length; i++) {
            this.episodesMoods.put(Mood.values()[i], new ArrayList<>());
        }

    }

    public MoodsSeries(Series series) {
        this(series.getName(), series.getSeasons());
    }

    public void setMood(Mood mood, Tuple<Integer, Integer>... episodes) {
        ArrayList<Episode> es = new ArrayList<>();
        for (int i = 0; i < episodes.length; i++) {
            es.add(fromSetToEpisode(episodes[i]));
        }
        this.episodesMoods.get(mood).addAll(es);
    }

    private Episode fromSetToEpisode(Tuple<Integer, Integer> tuple) {
        return this.getSeasons().get(tuple.x - 1).getEpisodes().get(tuple.y - 1);
    }

    public ArrayList<Episode> getEpisodesMoods(Mood mood) {
        return this.episodesMoods.get(mood);
    }

}
