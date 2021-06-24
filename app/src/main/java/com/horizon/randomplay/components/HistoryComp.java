package com.horizon.randomplay.components;

import java.util.Calendar;

public class HistoryComp {
    private final int season;
    private final int episode;
    private final String series;

    public HistoryComp(String series, int season, int episode) {
        this.series = series;
        this.season = season;
        this.episode = episode;
    }

    private String getDateString() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH);
    }

    @Override
    public String toString() {
        return getDateString() + " - " +  series + ": S." + season + " E." + episode;
    }
}
