package com.horizon.randomplay.series;


import android.content.Context;

import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.series.MoodsSeries;
import com.horizon.randomplay.components.series.Season;
import com.horizon.randomplay.components.series.Series;
import com.horizon.randomplay.util.SharedData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SeriesHolder {

    public enum SeriesKind {
        ANYTHING("Literally Anything"),
        FRIENDS("Friends"),
        THE_BIG_BANG_THEORY("The Big Bang Theory"),
        HOW_I_MET_YOUR_MOTHER("How I Met Your Mother"),
        BROOKLYN_NINE_NINE("Brooklyn Nine Nine"),
        THE_OFFICE("The Office"),
        RICK_AND_MORTY("Rick And Morty"),
        MODERN_FAMILY("Modern Family"),
        GRAYS_ANATOMY("Grays Anatomy"),
        AVATAR_THE_LAST_AIRBENDER("Avatar The Last Airbender"),
        THE_LEGEND_OF_KORRA("The Legend Of Korra"),
        BLACK_MIRROR("Black Mirror");

        private final String name;
        SeriesKind(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static String[] getNames() {
            String[] names = new String[SeriesKind.values().length];
            for (int i = 0; i < SeriesKind.values().length; i++) {
                names[i] = SeriesKind.values()[i].getName();
            }
            return names;
        }

        public static SeriesKind getByValue(String value) {
            for (int i = 0; i < SeriesKind.values().length; i++) {
                if (SeriesKind.values()[i].getName().equals(value)) {
                    return SeriesKind.values()[i];
                }
            }
            return null;
        }

    }

    private static final Map<String, MoodsSeries> allSeries = new HashMap<>();
    public static void init(Context context) {
        try {
            for (int i = 0; i < SeriesKind.values().length; i++) {
                if (!SeriesKind.values()[i].equals(SeriesKind.ANYTHING)) {
                    allSeries.put(SeriesKind.getNames()[i],
                            new MoodsSeries(getSeriesFromRaw(SeriesKind.values()[i].getName(), context)));
                }
            }

            initAllSeries();
            SeriesMoodHolder.init();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private static Series getSeriesFromRaw(String name, Context context) throws IOException {
        InputStream input = context.getResources().openRawResource(context.getResources()
                .getIdentifier("s_" + name.replace(" ", "_").toLowerCase(),
                        "raw", context.getPackageName()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        int seasonIndex = 1;
        int episodeIndex = 0;
        Series series = new Series(name);
        Season season = new Season(seasonIndex);
        Long id = null;

        String data;
        do {
            data = reader.readLine();
            if (data == null) {
                continue;
            }

            if (data.contains("Season ")) {
                if (episodeIndex > 0) {
                    series.addSeason(season);
                    seasonIndex++;
                    season = new Season(seasonIndex);
                    episodeIndex = 0;
                }

                if (data.contains("?")) {
                    String rawId = data.substring(data.indexOf("?") + 1).replace(" ", "");
                    id = Long.parseLong(rawId);
                    id--;
                }

            } else if (!data.replace(" ", "").isEmpty()) {
                episodeIndex++;
                Episode e = new Episode(episodeIndex, data);
                if (id != null) {
                    id++;
                    e.setId(id);
                }
                season.addEpisode(e);
            }
        } while (data != null);
        series.addSeason(season);

        reader.close();
        return series;
    }

    private static void initAllSeries() {
        allSeries.put(SeriesKind.ANYTHING.getName(),
                new MoodsSeries(new Series(SeriesKind.ANYTHING.getName())));

        for (Mood mood: Mood.values()) {
            if (!mood.equals(Mood.ANYTHING)) {
                Objects.requireNonNull(allSeries.get(SeriesKind.ANYTHING.getName())).addMood(mood);
            }
       }
    }

    public static Map<String, MoodsSeries> getAllSeries() {
        return allSeries;
    }

    public static ArrayList<MoodsSeries> getSeriesBasedOnMood(Mood mood) {
        ArrayList<MoodsSeries> arrayList = new ArrayList<>();

        for (String s: SharedData.getInstance().getSeriesHandler().getChosen()) {
            if (Objects.requireNonNull(allSeries.get(s)).getAvailableMoods().contains(mood)) {
                arrayList.add(allSeries.get(s));
            }
        }

        ArrayList<String> sData = SharedData.getInstance().getSeriesHandler().getChosen();
        ArrayList<MoodsSeries> fromSData = new ArrayList<>();
        for (int i = 0; i < sData.size(); i++) {
            fromSData.add(allSeries.get(sData.get(i)));
        }

        arrayList.retainAll(fromSData);
        return arrayList;
    }


    public static ArrayList<Mood> getAllAvailableMoods() {
        ArrayList<String> choose = SharedData.getInstance().getSeriesHandler().getChosen();
        ArrayList<Mood> ret = new ArrayList<>();

        for (String s: choose) {
            ret.addAll(Objects.requireNonNull(allSeries.get(s)).getAvailableMoods());
        }
        return  (ArrayList<Mood>) ret.stream().distinct().collect(Collectors.toList());
    }

    public static ArrayList<String> getModesByEpisode(MoodsSeries ms, Episode e) {
        ArrayList<String> moods = new ArrayList<>();

        for (Mood mood: ms.getAvailableMoods()) {
            for (Episode moodEpi: ms.getEpisodesByMoods(mood)) {
                if (moodEpi.getName().equals(e.getName())) {
                    moods.add(mood.getName());
                }
            }
        }
        return moods;
    }
}
