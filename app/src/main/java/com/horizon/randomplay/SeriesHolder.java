package com.horizon.randomplay;


import android.content.Context;

import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.MoodsSeries;
import com.horizon.randomplay.components.Season;
import com.horizon.randomplay.components.Series;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SeriesHolder {

    public enum SeriesKind {
        ANYTHING("IDK Bro"),
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

            setFriendsMoods();
            setHowIMetYourMotherMoods();
            setTheBigBangTheoryMoods();
            setBrooklyn99();
            setTheOffice();
            setRickAndMorty();
            setModernFamily();
            setGraysAnatomy();
            setAtla();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private static Series getSeriesFromRaw(String name, Context context) throws IOException {
        InputStream input = context.getResources().openRawResource(context.getResources()
                .getIdentifier(name.replace(" ", "_").toLowerCase(),
                        "raw", context.getPackageName()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        int seasonIndex = 1;
        int episodeIndex = 0;
        Series series = new Series(name);
        Season season = new Season(seasonIndex);

        String data = "";
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

            } else if (!data.replace(" ", "").isEmpty()) {
                episodeIndex++;
                season.addEpisode(new Episode(episodeIndex, data));
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
                allSeries.get(SeriesKind.ANYTHING.getName()).addMood(mood);
            }
       }
    }

    @SuppressWarnings("unchecked")
    private static void setFriendsMoods() {
        allSeries.get(SeriesKind.FRIENDS.getName()).setMood(Mood.CRY, new Tuple<>(3, 16), new Tuple<>(4, 12), new Tuple<>(5, 3), new Tuple<>(6, 24),
                new Tuple<>(6, 25), new Tuple<>(9, 21), new Tuple<>(10, 8), new Tuple<>(10, 17));

        allSeries.get(SeriesKind.FRIENDS.getName()).setMood(Mood.LAUGH, new Tuple<>(1, 13), new Tuple<>(2, 23), new Tuple<>(3, 8), new Tuple<>(4, 20),
                new Tuple<>(5, 14), new Tuple<>(6, 8), new Tuple<>(6, 17), new Tuple<>(7, 6), new Tuple<>(8, 15),
                new Tuple<>(9, 7), new Tuple<>(10, 3));

        allSeries.get(SeriesKind.FRIENDS.getName()).setMood(Mood.HAPPY, new Tuple<>(1, 10), new Tuple<>(2, 11), new Tuple<>(3, 9), new Tuple<>(5, 13),
                new Tuple<>(6, 7), new Tuple<>(7, 7), new Tuple<>(8, 6), new Tuple<>(9, 4), new Tuple<>(10, 4));

        allSeries.get(SeriesKind.FRIENDS.getName()).setMood(Mood.ROMANTIC, new Tuple<>(2, 7), new Tuple<>(2, 14), new Tuple<>(2, 15),
                new Tuple<>(3, 13), new Tuple<>(6, 25), new Tuple<>(9, 19), new Tuple<>(10, 12), new Tuple<>(10, 17));

        allSeries.get(SeriesKind.FRIENDS.getName()).setMood(Mood.BEST, new Tuple<>(1, 24), new Tuple<>(2, 14), new Tuple<>(4, 12),
                new Tuple<>(5, 8), new Tuple<>(5, 14), new Tuple<>(6, 24), new Tuple<>(6, 25), new Tuple<>(8, 2),
                new Tuple<>(8, 4), new Tuple<>(8, 9), new Tuple<>(10, 17));
    }

    @SuppressWarnings("unchecked")
    private static void setHowIMetYourMotherMoods() {
        allSeries.get(SeriesKind.HOW_I_MET_YOUR_MOTHER.getName()).setMood(Mood.CRY, new Tuple<>(3, 13), new Tuple<>(6, 14), new Tuple<>(6, 19),
                new Tuple<>(7, 10), new Tuple<>(8, 12), new Tuple<>(8, 20), new Tuple<>(9, 16), new Tuple<>(9, 23),
                new Tuple<>(9, 24));

        allSeries.get(SeriesKind.HOW_I_MET_YOUR_MOTHER.getName()).setMood(Mood.LAUGH, new Tuple<>(1, 10), new Tuple<>(2, 7), new Tuple<>(4, 4),
                new Tuple<>(8, 5), new Tuple<>(6, 2), new Tuple<>(7, 14), new Tuple<>(7, 20));

        allSeries.get(SeriesKind.HOW_I_MET_YOUR_MOTHER.getName()).setMood(Mood.HAPPY, new Tuple<>(1, 10), new Tuple<>(2, 1), new Tuple<>(2, 21),
                new Tuple<>(7, 23), new Tuple<>(8, 12));

        allSeries.get(SeriesKind.HOW_I_MET_YOUR_MOTHER.getName()).setMood(Mood.BEST, new Tuple<>(1, 22), new Tuple<>(2, 9), new Tuple<>(3, 13),
                new Tuple<>(4, 2), new Tuple<>(4, 4), new Tuple<>(4, 13), new Tuple<>(4, 14), new Tuple<>(5, 8),
                new Tuple<>(5, 12), new Tuple<>(6, 13));
    }

    @SuppressWarnings("unchecked")
    private static void setTheBigBangTheoryMoods() {
        allSeries.get(SeriesKind.THE_BIG_BANG_THEORY.getName()).setMood(Mood.BEST, new Tuple<>(12, 24), new Tuple<>(11, 24), new Tuple<>(10, 24),
                new Tuple<>(9, 11), new Tuple<>(8, 15), new Tuple<>(7, 9), new Tuple<>(6, 13), new Tuple<>(5, 24),
                new Tuple<>(4, 1), new Tuple<>(3, 23), new Tuple<>(2, 11), new Tuple<>(1, 17));

        allSeries.get(SeriesKind.THE_BIG_BANG_THEORY.getName()).setMood(Mood.CRY, new Tuple<>(6, 19), new Tuple<>(8, 18), new Tuple<>(8, 24),
                new Tuple<>(3, 19), new Tuple<>(12, 24), new Tuple<>(7, 22), new Tuple<>(9, 9), new Tuple<>(7, 24),
                new Tuple<>(12, 9), new Tuple<>(7, 12));

        allSeries.get(SeriesKind.THE_BIG_BANG_THEORY.getName()).setMood(Mood.LAUGH, new Tuple<>(3, 22), new Tuple<>(5, 10), new Tuple<>(4, 24),
                new Tuple<>(4, 21), new Tuple<>(4, 18), new Tuple<>(3, 23), new Tuple<>(3, 8), new Tuple<>(2, 11),
                new Tuple<>(3, 11), new Tuple<>(4, 10));

        allSeries.get(SeriesKind.THE_BIG_BANG_THEORY.getName()).setMood(Mood.SMART, new Tuple<>(1, 4), new Tuple<>(1, 6), new Tuple<>(1, 17),
                new Tuple<>(2, 18), new Tuple<>(3, 10), new Tuple<>(2, 23), new Tuple<>(11, 24), new Tuple<>(11, 9),
                new Tuple<>(10, 2), new Tuple<>(9, 16), new Tuple<>(8, 14), new Tuple<>(5, 24), new Tuple<>(4, 12), new Tuple<>(4, 2),
                new Tuple<>(3, 22), new Tuple<>(3, 14));
    }

    private static void setBrooklyn99() {
        allSeries.get(SeriesKind.BROOKLYN_NINE_NINE.getName()).setMood(Mood.LAUGH, new Tuple<>(1, 16), new Tuple<>(2, 12), new Tuple<>(3, 10),
                new Tuple<>(4, 5), new Tuple<>(5, 19));

        allSeries.get(SeriesKind.BROOKLYN_NINE_NINE.getName()).setMood(Mood.HAPPY, new Tuple<>(1, 13), new Tuple<>(2, 3), new Tuple<>(3, 4),
                new Tuple<>(3, 13), new Tuple<>(4, 17));

        allSeries.get(SeriesKind.BROOKLYN_NINE_NINE.getName()).setMood(Mood.BEST, new Tuple<>(1, 10), new Tuple<>(1, 13), new Tuple<>(2, 3),
                new Tuple<>(2, 12), new Tuple<>(3, 5), new Tuple<>(3, 10), new Tuple<>(4, 1), new Tuple<>(4, 2),
                new Tuple<>(4, 3), new Tuple<>(5, 4), new Tuple<>(5, 9), new Tuple<>(5, 10));
    }

    private static void setTheOffice() {
        allSeries.get(SeriesKind.THE_OFFICE.getName()).setMood(Mood.LAUGH, new Tuple<>(4, 13), new Tuple<>(5, 14), new Tuple<>(5, 15),
                new Tuple<>(3, 20), new Tuple<>(4, 7), new Tuple<>(4, 8));

        allSeries.get(SeriesKind.THE_OFFICE.getName()).setMood(Mood.CRY, new Tuple<>(7, 22), new Tuple<>(2, 22), new Tuple<>(9, 22),
                new Tuple<>(3, 17));

        allSeries.get(SeriesKind.THE_OFFICE.getName()).setMood(Mood.HAPPY, new Tuple<>(9, 24), new Tuple<>(5, 1), new Tuple<>(5, 2),
                new Tuple<>(7, 19), new Tuple<>(6, 4), new Tuple<>(6, 5), new Tuple<>(3, 23));
    }

    private static void setRickAndMorty() {
        allSeries.get(SeriesKind.RICK_AND_MORTY.getName()).setMood(Mood.SMART, new Tuple<>(1, 5), new Tuple<>(1, 2), new Tuple<>(2, 10),
                new Tuple<>(2, 6), new Tuple<>(2, 4), new Tuple<>(3, 1), new Tuple<>(3, 6), new Tuple<>(3, 7));
    }

    private static void setModernFamily() {
        allSeries.get(SeriesKind.MODERN_FAMILY.getName()).setMood(Mood.LAUGH, new Tuple<>(5, 18), new Tuple<>(2, 13), new Tuple<>(4, 13),
                new Tuple<>(6, 24), new Tuple<>(1, 15), new Tuple<>(2, 16), new Tuple<>(2, 2), new Tuple<>(3, 24),
                new Tuple<>(3, 16), new Tuple<>(3, 7), new Tuple<>(4, 6), new Tuple<>(4, 12), new Tuple<>(4, 2),
                new Tuple<>(5, 24), new Tuple<>(6, 16), new Tuple<>(4, 2));
    }

    private static void setGraysAnatomy() {
        allSeries.get(SeriesKind.GRAYS_ANATOMY.getName()).setMood(Mood.CRY, new Tuple<>(8, 24), new Tuple<>(11, 21), new Tuple<>(8, 10),
                new Tuple<>(6, 24), new Tuple<>(14, 7), new Tuple<>(9, 1), new Tuple<>(12, 9), new Tuple<>(4, 17),
                new Tuple<>(2, 27), new Tuple<>(5, 19), new Tuple<>(3, 16), new Tuple<>(6, 1), new Tuple<>(2, 6),
                new Tuple<>(7, 20), new Tuple<>(2, 17), new Tuple<>(8, 19), new Tuple<>(11, 11), new Tuple<>(9, 7),
                new Tuple<>(5, 18), new Tuple<>(7, 17), new Tuple<>(10, 12));

        allSeries.get(SeriesKind.GRAYS_ANATOMY.getName()).setMood(Mood.LAUGH, new Tuple<>(1, 9), new Tuple<>(6, 21), new Tuple<>(8, 7),
                new Tuple<>(8, 18));

        allSeries.get(SeriesKind.GRAYS_ANATOMY.getName()).setMood(Mood.ROMANTIC, new Tuple<>(6, 8), new Tuple<>(6, 10), new Tuple<>(7, 20),
                new Tuple<>(10, 12));
    }

    private static void setAtla() {
        allSeries.get(SeriesKind.AVATAR_THE_LAST_AIRBENDER.getName()).setMood(Mood.HAPPY, new Tuple<>(1, 4), new Tuple<>(1, 5), new Tuple<>(2, 2),
                new Tuple<>(2, 15), new Tuple<>(2, 19), new Tuple<>(3, 2), new Tuple<>(3, 4), new Tuple<>(3, 13), new Tuple<>(3, 17));
    }

    public static Map<String, MoodsSeries> getAllSeries() {
        return allSeries;
    }

    public static ArrayList<MoodsSeries> getSeriesBasedOnMood(Mood mood) {
        ArrayList<MoodsSeries> arrayList = new ArrayList<>();

        for (String s: SharedData.getInstance().getChosen()) {
            if (allSeries.get(s).getAvailableMoods().contains(mood)) {
                arrayList.add(allSeries.get(s));
            }
        }

        ArrayList<String> sData = SharedData.getInstance().getChosen();
        ArrayList<MoodsSeries> fromSData = new ArrayList<>();
        for (int i = 0; i < sData.size(); i++) {
            fromSData.add(allSeries.get(sData.get(i)));
        }

        arrayList.retainAll(fromSData);
        return arrayList;
    }


    public static ArrayList<Mood> getAviMoods() {
        ArrayList<String> choose = SharedData.getInstance().getChosen();
        ArrayList<Mood> ret = new ArrayList<>();

        for (String s: choose) {
            ret.addAll(allSeries.get(s).getAvailableMoods());
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
