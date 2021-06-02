package com.horizon.randomplay.components;

import com.horizon.randomplay.util.Tuple;

import java.util.ArrayList;

public class Series {

    private final String name;
    private ArrayList<Season> seasons;

    public Series(String name, ArrayList<Season> seasons) {
        this.name = name;
        this.seasons = seasons;
    }

    public Series(String name) {
        this(name, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void addSeason(Season season) {
        this.seasons.add(season);
    }

    @Override
    public String toString() {
        return this.name + " has: " + this.seasons.size() + " seasons";
    }

    public Season getSeason(Episode episode) {
        for (Season season: this.seasons) {
            if (season.getEpisodes().contains(episode)) {
                return season;
            }
        }
        return null;
    }
}