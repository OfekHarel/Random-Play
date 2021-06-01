package com.horizon.appsrc.components;

import java.util.ArrayList;

public class Season {
    
    private final int number;
    private ArrayList<Episode> episodes;

    public Season(int number, ArrayList<Episode> episodes) {
        this.number = number;
        this.episodes = episodes;
    }

    public Season(int number) {
        this(number, new ArrayList<>());
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public int getNumber() {
        return number;
    }

    public void addEpisode(Episode episode) {
        this.episodes.add(episode);
    }

    @Override
    public String toString() {
        return "Season " + this.number + " has: " + this.episodes.size() + " episodes";
    }
}