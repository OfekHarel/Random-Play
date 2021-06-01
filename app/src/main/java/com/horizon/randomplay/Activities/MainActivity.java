package com.horizon.randomplay.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.horizon.randomplay.R;
import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.util.Vars;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private Spinner seriesSpinner;
    private Spinner moodSpinner;

    private ArrayAdapter<String> seriesSpinnerAdapter;
    private ArrayAdapter<String> moodSpinnerAdapter;

    ArrayList<String> moods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeriesHolder.init(this);

        moods = Mood.getNames();

        this.seriesSpinner = findViewById(R.id.series_spinner);
        this.moodSpinner = findViewById(R.id.mood_spinner);

        this.seriesSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                SeriesHolder.SeriesKind.getNames());
        this.seriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.seriesSpinner.setAdapter(this.seriesSpinnerAdapter);
        this.seriesSpinnerAdapter.notifyDataSetChanged();
        this.seriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                SeriesHolder.SeriesKind series = SeriesHolder
                        .SeriesKind.getByValue(seriesSpinnerAdapter.getItem(position));
                Vars.choice.x = series;

                moods = Mood.getNames(SeriesHolder.getAllSeries()
                                .get(series.getName()).getAvailableMoods());
                runOnUiThread(() -> updateMoods(moods));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        this.moodSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                moods);
        this.moodSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.moodSpinner.setAdapter(this.moodSpinnerAdapter);
        this.moodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Vars.choice.y = Mood.getByValue(moodSpinnerAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

    }

    private void updateMoods(ArrayList<String> newArr) {
        moodSpinnerAdapter.clear();
        moodSpinnerAdapter.add(Mood.ANYTHING.getName());
        for (String newS: newArr) {
            moodSpinnerAdapter.add(newS);
        }
        moodSpinner.setSelection(0, true);
    }

    public void clickGenerate(View view) {
        redirectActivity(this, RandomActivity.class);
    }
}