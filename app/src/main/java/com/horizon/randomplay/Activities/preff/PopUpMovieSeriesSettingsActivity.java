package com.horizon.randomplay.Activities.preff;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Vars;

import java.util.ArrayList;
import java.util.Collections;

public class PopUpMovieSeriesSettingsActivity extends BaseActivity {

    private ListView listView;
    private ListView toggle;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> toggleAdapter;

    private final String ALL = "Toggle All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_pref_movies_series);

        TextView title = findViewById(R.id.pref_title);
        title.setText(String.format("%s you watch", Vars.isSeries ? "Series" : "Movies"));

        this.toggle = findViewById(R.id.pref_list_set);
        ArrayList<String> toggleArr = new ArrayList<>();
        toggleArr.add(ALL);
        this.toggleAdapter = new ArrayAdapter<>(this, R.layout.list_black_text_choose, toggleArr);
        this.toggle.setAdapter(this.toggleAdapter);
        this.toggle.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.toggle.setOnItemClickListener((parent, view, position, id) -> {
            preformVibration(2);
            if (this.toggleAdapter.getItem(position).equals(ALL)) {
                checkAll(this.toggle.getCheckedItemPositions().get(0));
            }
            updatedChecked();
        });

        this.listView = findViewById(R.id.pref_list);
        ArrayList<String> arr = new ArrayList<>();

        if (Vars.isSeries) {
            Collections.addAll(arr, SeriesHolder.SeriesKind.getNames());
            arr.remove(SeriesHolder.SeriesKind.ANYTHING.getName());
        } else {
            Collections.addAll(arr, MoviesHolder.MovieKind.getNames());
            arr.remove(MoviesHolder.MovieKind.ANYTHING.getName());
        }

        this.adapter = new ArrayAdapter<>(this, R.layout.list_black_text_choose, arr);
        this.listView.setAdapter(this.adapter);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            preformVibration(2);
            updatedChecked();
        });

        check();
    }

    private void check() {
        ArrayList<String> arr = Vars.isSeries ?
                SharedData.getInstance().getSeriesHandler().getChosen() : SharedData.getInstance().getMovieHandler().getChosen();
        for (String s : arr) {
            this.listView.setItemChecked(this.adapter.getPosition(s), true);
        }
        updatedChecked();
    }

    private void checkAll(boolean is) {
        for (int i = 0; i < this.adapter.getCount(); i++) {
            this.listView.setItemChecked(i, is);
        }
    }

    private void updatedChecked() {
        int num = (Vars.isSeries ? SeriesHolder.SeriesKind.values().length : MoviesHolder.MovieKind.values().length) - 1;
        this.toggle.setItemChecked(0, this.listView.getCheckedItemCount() == num);

        SparseBooleanArray checked = this.listView.getCheckedItemPositions();
        ArrayList<String> toAdd = new ArrayList<>();
        for (int i = 0; i < this.adapter.getCount(); i++) {
            if (checked.get(i)) {
                toAdd.add(this.adapter.getItem(i));
            }
        }
        if (Vars.isSeries) {
            SharedData.getInstance().getSeriesHandler().setChosen(toAdd);
        } else {
            SharedData.getInstance().getMovieHandler().setChosen(toAdd);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (SharedData.getInstance().getSeriesHandler().getChosen().size() <= 0 ||
                SharedData.getInstance().getMovieHandler().getChosen().size() <= 0) {
            checkAll(true);
            updatedChecked();
        }
    }
}
