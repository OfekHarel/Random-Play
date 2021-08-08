package com.horizon.randomplay.components;

import java.util.Calendar;

public class HistoryComp {
    private int season;
    private int episode;
    private String series;
    private String movieCol;
    private String movieName;
    private final boolean isSeries;

    public HistoryComp(String series, int season, int episode) {
        this.series = series;
        this.season = season;
        this.episode = episode;
        this.isSeries = true;
    }

    public HistoryComp(String movieCol, String movieName) {
        this.movieCol = movieCol;
        this.movieName = movieName;
        this.isSeries = false;
    }

    private String getDateString() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH);
    }

    @Override
    public String toString() {
        String out = getDateString() + " - ";
        out += isSeries ?
                series + ": S." + season + " E." + episode : movieCol + " : " + movieName;
        return out;
    }
}
