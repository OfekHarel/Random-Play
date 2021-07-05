package com.horizon.randomplay.util;

import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.MoodsSeries;

public class Vars {
    public static Tuple<SeriesHolder.SeriesKind, Mood> choice = new Tuple<>(SeriesHolder.SeriesKind.ANYTHING, Mood.ANYTHING);
    public static Tuple<MoodsSeries, Episode> historyCompon = new Tuple<>(null, null);
}
