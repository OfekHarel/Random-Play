package com.horizon.randomplay.util;

import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.series.MoodsSeries;

public class Vars {
    public static Tuple<SeriesHolder.SeriesKind, Mood> choice = new Tuple<>(SeriesHolder.SeriesKind.ANYTHING, Mood.ANYTHING);
    public static Tuple<MoodsSeries, Episode> historyCompon = new Tuple<>(null, null);
    public static SeriesHolder.SeriesKind prefInfoChoice = null;
}
