package com.horizon.randomplay.Activities;

import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.horizon.randomplay.R;
import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.Series;
import com.horizon.randomplay.util.Vars;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends BaseActivity {

    private ScrollChoice seriesScroll;
    private ScrollChoice moodScroll;

    ArrayList<String> moods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeriesHolder.init(this);

        moods = Mood.getNames();

        this.seriesScroll = findViewById(R.id.scroll_series);
        this.moodScroll = findViewById(R.id.scroll_mood);
        this.moodScroll.animate();


        this.seriesScroll.addItems(SeriesHolder.SeriesKind.getNames(), SeriesHolder.SeriesKind.getNames().indexOf(Vars.choice.x.getName()));
        this.seriesScroll.setOnItemSelectedListener((scrollChoice, position, name) -> {
           SeriesHolder.SeriesKind series = SeriesHolder
                   .SeriesKind.getByValue(seriesScroll.getCurrentSelection());
           Vars.choice.x = series;

            assert series != null;
            moods = Mood.getNames(Objects.requireNonNull(SeriesHolder.getAllSeries()
                   .get(series.getName())).getAvailableMoods());
           runOnUiThread(() -> updateMoods(moods));
            this.moodScroll.notifyDatasetChanged();
            preformVibration(seriesScroll, HapticFeedbackConstants.CLOCK_TICK);
        });


        this.moodScroll.addItems(moods, moods.indexOf(Vars.choice.y.getName()));
        this.moodScroll.setOnItemSelectedListener((scrollChoice, position, name) -> {
            Vars.choice.y = Mood.getByValue(moodScroll.getCurrentSelection());
            preformVibration(seriesScroll, HapticFeedbackConstants.CLOCK_TICK);
        });
    }

    private void updateMoods(ArrayList<String> newArr) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(Mood.ANYTHING.getName());
        temp.addAll(newArr);
        moods = temp;
        moodScroll.addItems(moods, 0);
        moodScroll.animate();
    }

    public void clickGenerate(View view) {
        preformVibration(view, HapticFeedbackConstants.LONG_PRESS);
        redirectActivity(this, RandomActivity.class);
    }
}