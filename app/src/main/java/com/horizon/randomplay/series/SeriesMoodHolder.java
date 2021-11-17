package com.horizon.randomplay.series;

import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.util.Tuple;

import java.util.Objects;

public class SeriesMoodHolder {

    private static void friendsMoods() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.FRIENDS.getName())).setMood(
                Mood.CRY,
                new Tuple<>(3, 16), new Tuple<>(4, 12), new Tuple<>(5, 3), new Tuple<>(6, 24),
                new Tuple<>(6, 25), new Tuple<>(9, 21), new Tuple<>(10, 8), new Tuple<>(10, 17)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.FRIENDS.getName())).setMood(
                Mood.LAUGH,
                new Tuple<>(1, 13), new Tuple<>(2, 23), new Tuple<>(3, 8), new Tuple<>(4, 20),
                new Tuple<>(5, 14), new Tuple<>(6, 8), new Tuple<>(6, 17), new Tuple<>(7, 6), new Tuple<>(8, 15),
                new Tuple<>(9, 7), new Tuple<>(10, 3)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.FRIENDS.getName())).setMood(
                Mood.HAPPY,
                new Tuple<>(1, 10), new Tuple<>(2, 11), new Tuple<>(3, 9), new Tuple<>(5, 13),
                new Tuple<>(6, 7), new Tuple<>(7, 7), new Tuple<>(8, 6), new Tuple<>(9, 4), new Tuple<>(10, 4)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.FRIENDS.getName())).setMood(
                Mood.ROMANTIC,
                new Tuple<>(2, 7), new Tuple<>(2, 14), new Tuple<>(2, 15),
                new Tuple<>(3, 13), new Tuple<>(6, 25), new Tuple<>(9, 19), new Tuple<>(10, 12), new Tuple<>(10, 17)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.FRIENDS.getName())).setMood(
                Mood.BEST,
                new Tuple<>(1, 24), new Tuple<>(2, 14), new Tuple<>(4, 12),
                new Tuple<>(5, 8), new Tuple<>(5, 14), new Tuple<>(6, 24), new Tuple<>(6, 25), new Tuple<>(8, 2),
                new Tuple<>(8, 4), new Tuple<>(8, 9), new Tuple<>(10, 17)
        );
    }

    private static void howIMetYourMotherMoods() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.HOW_I_MET_YOUR_MOTHER.getName())).setMood(
                Mood.CRY,
                new Tuple<>(3, 13), new Tuple<>(6, 14), new Tuple<>(6, 19),
                new Tuple<>(7, 10), new Tuple<>(8, 12), new Tuple<>(8, 20), new Tuple<>(9, 16), new Tuple<>(9, 23),
                new Tuple<>(9, 24)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.HOW_I_MET_YOUR_MOTHER.getName())).setMood(
                Mood.LAUGH,
                new Tuple<>(1, 10), new Tuple<>(2, 7), new Tuple<>(4, 4),
                new Tuple<>(8, 5), new Tuple<>(6, 2), new Tuple<>(7, 14), new Tuple<>(7, 20)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.HOW_I_MET_YOUR_MOTHER.getName())).setMood(
                Mood.HAPPY,
                new Tuple<>(1, 10), new Tuple<>(2, 1), new Tuple<>(2, 21),
                new Tuple<>(7, 23), new Tuple<>(8, 12)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.HOW_I_MET_YOUR_MOTHER.getName())).setMood(
                Mood.BEST,
                new Tuple<>(1, 22), new Tuple<>(2, 9), new Tuple<>(3, 13),
                new Tuple<>(4, 2), new Tuple<>(4, 4), new Tuple<>(4, 13), new Tuple<>(4, 14), new Tuple<>(5, 8),
                new Tuple<>(5, 12), new Tuple<>(6, 13)
        );
    }

    private static void theBigBangTheoryMoods() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.THE_BIG_BANG_THEORY.getName())).setMood(
                Mood.BEST,
                new Tuple<>(12, 24), new Tuple<>(11, 24), new Tuple<>(10, 24),
                new Tuple<>(9, 11), new Tuple<>(8, 15), new Tuple<>(7, 9), new Tuple<>(6, 13), new Tuple<>(5, 24),
                new Tuple<>(4, 1), new Tuple<>(3, 23), new Tuple<>(2, 11), new Tuple<>(1, 17)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.THE_BIG_BANG_THEORY.getName())).setMood(
                Mood.CRY,
                new Tuple<>(6, 19), new Tuple<>(8, 18), new Tuple<>(8, 24),
                new Tuple<>(3, 19), new Tuple<>(12, 24), new Tuple<>(7, 22), new Tuple<>(9, 9), new Tuple<>(7, 24),
                new Tuple<>(12, 9), new Tuple<>(7, 12)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.THE_BIG_BANG_THEORY.getName())).setMood(
                Mood.LAUGH,
                new Tuple<>(3, 22), new Tuple<>(5, 10), new Tuple<>(4, 24),
                new Tuple<>(4, 21), new Tuple<>(4, 18), new Tuple<>(3, 23), new Tuple<>(3, 8), new Tuple<>(2, 11),
                new Tuple<>(3, 11), new Tuple<>(4, 10)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.THE_BIG_BANG_THEORY.getName())).setMood(
                Mood.SMART,
                new Tuple<>(1, 4), new Tuple<>(1, 6), new Tuple<>(1, 17),
                new Tuple<>(2, 18), new Tuple<>(3, 10), new Tuple<>(2, 23), new Tuple<>(11, 24), new Tuple<>(11, 9),
                new Tuple<>(10, 2), new Tuple<>(9, 16), new Tuple<>(8, 14), new Tuple<>(5, 24), new Tuple<>(4, 12), new Tuple<>(4, 2),
                new Tuple<>(3, 22), new Tuple<>(3, 14)
        );
    }

    private static void brooklyn99() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.BROOKLYN_NINE_NINE.getName())).setMood(
                Mood.LAUGH,
                new Tuple<>(1, 16), new Tuple<>(2, 12), new Tuple<>(3, 10),
                new Tuple<>(4, 5), new Tuple<>(5, 19)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.BROOKLYN_NINE_NINE.getName())).setMood(
                Mood.HAPPY,
                new Tuple<>(1, 13), new Tuple<>(2, 3), new Tuple<>(3, 4),
                new Tuple<>(3, 13), new Tuple<>(4, 17)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.BROOKLYN_NINE_NINE.getName())).setMood(
                Mood.BEST,
                new Tuple<>(1, 10), new Tuple<>(1, 13), new Tuple<>(2, 3),
                new Tuple<>(2, 12), new Tuple<>(3, 5), new Tuple<>(3, 10), new Tuple<>(4, 1), new Tuple<>(4, 2),
                new Tuple<>(4, 3), new Tuple<>(5, 4), new Tuple<>(5, 9), new Tuple<>(5, 10)
        );
    }

    private static void theOffice() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.THE_OFFICE.getName())).setMood(
                Mood.LAUGH,
                new Tuple<>(4, 13), new Tuple<>(5, 14), new Tuple<>(5, 15),
                new Tuple<>(3, 20), new Tuple<>(4, 7), new Tuple<>(4, 8)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.THE_OFFICE.getName())).setMood(
                Mood.CRY,
                new Tuple<>(7, 22), new Tuple<>(2, 22), new Tuple<>(9, 22),
                new Tuple<>(3, 17)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.THE_OFFICE.getName())).setMood(
                Mood.HAPPY,
                new Tuple<>(9, 23), new Tuple<>(5, 1), new Tuple<>(5, 2),
                new Tuple<>(7, 19), new Tuple<>(6, 4), new Tuple<>(6, 5), new Tuple<>(3, 23)
        );
    }

    private static void rickAndMorty() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.RICK_AND_MORTY.getName())).setMood(
                Mood.SMART,
                new Tuple<>(1, 5), new Tuple<>(1, 2), new Tuple<>(2, 10), new Tuple<>(5, 10),
                new Tuple<>(2, 6), new Tuple<>(2, 4), new Tuple<>(3, 1), new Tuple<>(3, 6), new Tuple<>(3, 7)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.RICK_AND_MORTY.getName())).setMood(
                Mood.BEST,
                new Tuple<>(1, 11), new Tuple<>(4, 2), new Tuple<>(1, 5), new Tuple<>(2, 4), new Tuple<>(3, 3),
                new Tuple<>(4, 8), new Tuple<>(3, 7), new Tuple<>(1, 8), new Tuple<>(3, 8), new Tuple<>(2, 2),
                new Tuple<>(5, 10)
        );
    }

    private static void modernFamily() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.MODERN_FAMILY.getName())).setMood(
                Mood.LAUGH,
                new Tuple<>(5, 18), new Tuple<>(2, 13), new Tuple<>(4, 13),
                new Tuple<>(6, 24), new Tuple<>(1, 15), new Tuple<>(2, 16), new Tuple<>(2, 2), new Tuple<>(3, 24),
                new Tuple<>(3, 16), new Tuple<>(3, 7), new Tuple<>(4, 6), new Tuple<>(4, 12), new Tuple<>(4, 2),
                new Tuple<>(5, 24), new Tuple<>(6, 16), new Tuple<>(4, 2)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.MODERN_FAMILY.getName())).setMood(
                Mood.BEST,
                new Tuple<>(1, 1), new Tuple<>(1, 9), new Tuple<>(1, 15), new Tuple<>(2, 13), new Tuple<>(3, 22),
                new Tuple<>(4, 2), new Tuple<>(4, 24), new Tuple<>(5, 1), new Tuple<>(5, 23),
                new Tuple<>(5, 24), new Tuple<>(6, 16)
        );
    }

    private static void graysAnatomy() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.GRAYS_ANATOMY.getName())).setMood(
                Mood.CRY,
                new Tuple<>(8, 24), new Tuple<>(11, 21), new Tuple<>(8, 10),
                new Tuple<>(6, 24), new Tuple<>(14, 7), new Tuple<>(9, 1), new Tuple<>(12, 9), new Tuple<>(4, 17),
                new Tuple<>(2, 27), new Tuple<>(5, 19), new Tuple<>(3, 16), new Tuple<>(6, 1), new Tuple<>(2, 6),
                new Tuple<>(7, 20), new Tuple<>(2, 17), new Tuple<>(8, 19), new Tuple<>(11, 11), new Tuple<>(9, 7),
                new Tuple<>(5, 18), new Tuple<>(7, 17), new Tuple<>(10, 12)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.GRAYS_ANATOMY.getName())).setMood(
                Mood.LAUGH,
                new Tuple<>(1, 9), new Tuple<>(6, 21), new Tuple<>(8, 7),
                new Tuple<>(8, 18)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.GRAYS_ANATOMY.getName())).setMood(
                Mood.ROMANTIC,
                new Tuple<>(6, 8), new Tuple<>(6, 10), new Tuple<>(7, 20),
                new Tuple<>(10, 12)
        );
    }

    private static void atla() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.AVATAR_THE_LAST_AIRBENDER.getName())).setMood(
                Mood.HAPPY,
                new Tuple<>(1, 4), new Tuple<>(1, 5), new Tuple<>(2, 2), new Tuple<>(2, 19),
                new Tuple<>(3, 2), new Tuple<>(3, 4), new Tuple<>(3, 13), new Tuple<>(3, 17)
        );

        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.AVATAR_THE_LAST_AIRBENDER.getName())).setMood(
                Mood.BEST,
                new Tuple<>(2, 8), new Tuple<>(1, 5), new Tuple<>(2, 13), new Tuple<>(3, 12),
                new Tuple<>(1, 3), new Tuple<>(3, 8), new Tuple<>(1, 4), new Tuple<>(2, 18),
                new Tuple<>(3, 15), new Tuple<>(2, 15), new Tuple<>(3, 13), new Tuple<>(2, 6),
                new Tuple<>(2, 7)
        );
    }

    private static void tlok() {
        Objects.requireNonNull(SeriesHolder.getAllSeries().get(SeriesHolder.SeriesKind.THE_LEGEND_OF_KORRA.getName())).setMood(
                Mood.BEST,
                new Tuple<>(3, 8), new Tuple<>(1, 10), new Tuple<>(1, 11), new Tuple<>(1, 6),
                new Tuple<>(3, 10), new Tuple<>(4, 10), new Tuple<>(4, 13), new Tuple<>(4, 12),
                new Tuple<>(1, 12), new Tuple<>(3, 11), new Tuple<>(4, 2), new Tuple<>(2, 7),
                new Tuple<>(3, 12), new Tuple<>(2, 8), new Tuple<>(3, 13)
        );
    }

    public static void init() {
        friendsMoods();
        howIMetYourMotherMoods();
        theBigBangTheoryMoods();
        brooklyn99();
        theOffice();
        rickAndMorty();
        modernFamily();
        graysAnatomy();
        atla();
        tlok();
    }
}
