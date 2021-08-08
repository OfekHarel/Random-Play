package com.horizon.randomplay.components.series;

import java.util.ArrayList;

public class Series {

    private final String name;
    private final ArrayList<Season> seasons;

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
        for (Season season : this.seasons) {
            if (season.getEpisodes().contains(episode)) {
                return season;
            }
        }
        return null;
    }

    public Episode getEpisodeByString(String s, String e) {
        int season = Integer.parseInt(s);
        int episode = Integer.parseInt(e);

        return this.seasons.get(season - 1).getEpisodes().get(episode - 1);
    }

    public int countEpisodes() {
        int count = 0;
        for (Season s : this.getSeasons()) {
            for (Episode e : s.getEpisodes()) {
                count++;
            }
        }
        return count;
    }
}