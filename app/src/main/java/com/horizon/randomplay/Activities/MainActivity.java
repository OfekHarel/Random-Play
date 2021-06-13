package com.horizon.randomplay.Activities;

import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.HapticFeedbackConstants;
import android.view.View;

import com.horizon.randomplay.R;
import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.util.Vars;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.Objects;

public class MainActivity extends BaseActivity {

    private NumberPicker seriesScroll;
    private NumberPicker moodScroll;

    private String[] moods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeriesHolder.init(this);

        this.seriesScroll = findViewById(R.id.series_scroll);
        this.moodScroll = findViewById(R.id.scroll_mood);

        moods = Mood.getNames();

        initPicker(SeriesHolder.SeriesKind.getNames(), this.seriesScroll);
        initPicker(moods, this.moodScroll);

        this.seriesScroll.setOnValueChangedListener((picker, oldVal, newVal) -> {

            SeriesHolder.SeriesKind series = SeriesHolder
                .SeriesKind.values()[picker.getValue() - 1];
            Vars.choice.x = series;

            moods = updateMoodsArr(Mood.getNames(Objects.requireNonNull(SeriesHolder.getAllSeries()
                    .get(series.getName())).getAvailableMoods()));
            initPicker(moods, moodScroll);

            });

        this.seriesScroll.setOnScrollListener((picker, scrollState) -> {
            preformVibration(picker, HapticFeedbackConstants.CLOCK_TICK);
            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                preformVibration(picker, HapticFeedbackConstants.CLOCK_TICK);
            }
        });

        this.moodScroll.setOnValueChangedListener((picker, oldVal, newVal) -> Vars.choice.y = Mood.getByValue(moods[newVal - 1]));
        this.moodScroll.setOnScrollListener((picker, scrollState) -> {
            preformVibration(2, VibrationEffect.DEFAULT_AMPLITUDE);
            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                preformVibration(picker, HapticFeedbackConstants.CLOCK_TICK);
            }
        });
        restoreLatestPick();
    }

    private void initPicker(String[] arr, NumberPicker picker) {
        picker.setDisplayedValues(null);
        picker.setMinValue(1);
        picker.setMaxValue(arr.length);
        picker.setDisplayedValues(arr);
        picker.setWrapSelectorWheel(true);
        picker.setValue(1);
    }

    private String[] updateMoodsArr(String[] newMoods) {
        String[] arr = new String[newMoods.length + 1];
        arr[0] = Mood.ANYTHING.getName();
        System.arraycopy(newMoods, 0, arr, 1, arr.length - 1);
        return arr;
    }

    private void restoreLatestPick() {
        // series
        for (int i = 0; i < SeriesHolder.SeriesKind.getNames().length; i ++) {
            if (SeriesHolder.SeriesKind.getNames()[i].equals(Vars.choice.x.getName())) {
                this.seriesScroll.setValue(i + 1);
            }
        }

        // mood
        moods = updateMoodsArr(Mood.getNames(Objects.requireNonNull(SeriesHolder.getAllSeries()
                .get(Vars.choice.x.getName())).getAvailableMoods()));
        initPicker(moods, moodScroll);
        for (int i = 0; i < moods.length; i ++) {
            if (moods[i].equals(Vars.choice.y.getName())) {
                this.moodScroll.setValue(i + 1);
            }
        }

    }

    public void clickGenerate(View view) {
        preformVibration(view, HapticFeedbackConstants.LONG_PRESS);
        redirectActivity(this, RandomActivity.class);
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLatestPick();
    }
}