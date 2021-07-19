package com.horizon.randomplay.Activities.preff;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.horizon.randomplay.Activities.base.BaseFragment;
import com.horizon.randomplay.Activities.series.SeriesInfoActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Vars;

import java.util.ArrayList;
import java.util.Collections;

public class PrefFragment extends BaseFragment {

    private ListView listView;
    private ListView settingsListView;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> settingAdapter;


    private final String ALL = "Toggle All";

    public PrefFragment() {
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_pref, container, false);

        this.settingsListView = rootView.findViewById(R.id._series_pref_list_set);
        ArrayList<String> arr = new ArrayList<>();
        arr.add(ALL);
        this.settingAdapter = new ArrayAdapter<>(getContext(), R.layout.list_black_text_choose, arr);
        this.settingsListView.setAdapter(this.settingAdapter);
        this.settingsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.settingsListView.setOnItemClickListener((parent, view, position, id) -> {
            preformVibration(requireContext(), 2);
            if (this.settingAdapter.getItem(position).equals(ALL)) {
                checkAll(this.settingsListView.getCheckedItemPositions().get(0));
            }
            updatedChecked();
        });
        this.settingsListView.setOnTouchListener((v, event) -> (event.getAction() == MotionEvent.ACTION_MOVE));

        this.listView = rootView.findViewById(R.id.series_pref_list);
        ArrayList<String> series = new ArrayList<>();
        Collections.addAll(series, SeriesHolder.SeriesKind.getNames());
        series.remove(SeriesHolder.SeriesKind.ANYTHING.getName());
        this.adapter = new ArrayAdapter<>(getContext(), R.layout.list_black_text_choose, series);
        this.listView.setAdapter(this.adapter);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            preformVibration(requireContext(), 2);
            updatedChecked();
        });

        this.listView.setOnItemLongClickListener((arg0, arg1, pos, id) -> {
            Vars.prefInfoChoice = SeriesHolder.SeriesKind.getByValue(this.adapter.getItem(pos));
            redirectActivity((AppCompatActivity) getActivity(), SeriesInfoActivity.class);
            return true;
        });

        check();
        return rootView;
    }

    private void check() {
        for (String s: SharedData.getInstance().getSeriesHandler().getChosen()) {
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
        this.settingsListView.setItemChecked(0, this.listView.getCheckedItemCount() == SeriesHolder.SeriesKind.values().length - 1);

        SparseBooleanArray checked = this.listView.getCheckedItemPositions();
        ArrayList<String> toAdd = new ArrayList<>();
        for (int i = 0; i < this.adapter.getCount(); i++) {
            if (checked.get(i)) {
                toAdd.add(this.adapter.getItem(i));
            }
        }
        SharedData.getInstance().getSeriesHandler().setChosen(toAdd);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (SharedData.getInstance().getSeriesHandler().getChosen().size() <= 0 ) {
            checkAll(true);
            updatedChecked();
        }
    }
}