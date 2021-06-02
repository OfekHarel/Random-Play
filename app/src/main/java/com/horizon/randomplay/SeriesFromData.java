package com.horizon.randomplay;

import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.Season;
import com.horizon.randomplay.components.Series;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class SeriesFromData {

    private final static Path PATH = FileSystems.getDefault().getPath("data");

    public static Series get(String name) throws FileNotFoundException {
        System.out.println(PATH);
        File f = Paths.get(PATH.toString(), name.replace(" ", "") + ".txt").toFile();
        Scanner reader = new Scanner(f);

        int seasonIndex = 1;
        int episodeIndex = 0;
        Series series = new Series(name);
        Season season = new Season(seasonIndex);

        String data;
        do {
            data = reader.nextLine();

            if (data.contains("Season")) {
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
        } while (reader.hasNextLine());
        series.addSeason(season);

        reader.close();
        return series;
    }
}