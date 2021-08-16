package com.horizon.randomplay.Activities.preff;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.horizon.randomplay.Activities.base.BaseFragment;
import com.horizon.randomplay.R;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.util.SharedData;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class PrefFragment extends BaseFragment {

    private ListView listView;
    private ListView toggle;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> toggleAdapter;
    private Dialog dialog;
    private View dialogView;
    private View rootView;

    private final String ALL = "Toggle All";

    public PrefFragment() { }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_pref, container, false);
        
        dialog = new Dialog(getContext());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        DisplayMetrics dm = new DisplayMetrics();
        dialog.setContentView(R.layout.pop_up_pref_movies_series);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        dialog.getWindow().setLayout((int) (width * 0.98), (int) (height * 0.655));

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        params.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(params);

        Button seriesPreff = rootView.findViewById(R.id.series_pref_btn);
        seriesPreff.setOnClickListener(v -> openSeriesMoviesPop(true));

        Button moviePreff = rootView.findViewById(R.id.movie_pref_btn);
        moviePreff.setOnClickListener(v -> openSeriesMoviesPop(false));

        Button about = rootView.findViewById(R.id.info_btn);
        about.setOnClickListener(v -> { openAbout(); });

        Button donate = rootView.findViewById(R.id.donate_bt);
        donate.setOnClickListener(v -> { });

        Button suggest = rootView.findViewById(R.id.suggest_btn);
        suggest.setOnClickListener(v -> { });

        return rootView;
    }

    private void openSeriesMoviesPop(boolean isSeries) {
        dialogView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_up_pref_movies_series,
                rootView.findViewById(R.id.pop_container)
        );

        handlePop(isSeries);
        this.dialog.setContentView(dialogView);
        this.dialog.show();
    }

    private void openAbout() {
        dialogView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_up_pref_about,
                rootView.findViewById(R.id.pop_container)
        );

        this.dialog.setContentView(dialogView);
        this.dialog.show();
    }

    private void check(boolean isSeries) {
        ArrayList<String> arr = isSeries ?
                SharedData.getInstance().getSeriesHandler().getChosen() : SharedData.getInstance().getMovieHandler().getChosen();
        for (String s : arr) {
            this.listView.setItemChecked(this.adapter.getPosition(s), true);
        }
        updatedChecked(isSeries);
    }

    private void checkAll(boolean is) {
        for (int i = 0; i < this.adapter.getCount(); i++) {
            this.listView.setItemChecked(i, is);
        }
    }

    private void updatedChecked(boolean isSeries) {
        int num = (isSeries ? SeriesHolder.SeriesKind.values().length : MoviesHolder.MovieKind.values().length) - 1;
        this.toggle.setItemChecked(0, this.listView.getCheckedItemCount() == num);

        SparseBooleanArray checked = this.listView.getCheckedItemPositions();
        ArrayList<String> toAdd = new ArrayList<>();
        for (int i = 0; i < this.adapter.getCount(); i++) {
            if (checked.get(i)) {
                toAdd.add(this.adapter.getItem(i));
            }
        }
        if (isSeries) {
            SharedData.getInstance().getSeriesHandler().setChosen(toAdd);
        } else {
            SharedData.getInstance().getMovieHandler().setChosen(toAdd);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if (SharedData.getInstance().getSeriesHandler().getChosen().size() <= 0) {
            checkAll(true);
            updatedChecked(true);
        } else if (SharedData.getInstance().getMovieHandler().getChosen().size() <= 0) {
            checkAll(true);
            updatedChecked(false);
        }
    }
    
    private void handlePop(boolean isSeries) {
        TextView title = dialogView.findViewById(R.id.pref_title);
        title.setText(String.format("%s you watch", isSeries ? "Series" : "Movies"));

        this.toggle = dialogView.findViewById(R.id.pref_list_set);
        ArrayList<String> toggleArr = new ArrayList<>();
        toggleArr.add(ALL);
        this.toggleAdapter = new ArrayAdapter<>(getContext(), R.layout.list_black_text_choose, toggleArr);
        this.toggle.setAdapter(this.toggleAdapter);
        this.toggle.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.toggle.setOnItemClickListener((parent, view, position, id) -> {
            preformVibration(dialogView, HapticFeedbackConstants.CLOCK_TICK);
            if (this.toggleAdapter.getItem(position).equals(ALL)) {
                checkAll(this.toggle.getCheckedItemPositions().get(0));
            }
            updatedChecked(isSeries);
        });

        this.listView = dialogView.findViewById(R.id.pref_list);
        ArrayList<String> arr = new ArrayList<>();

        if (isSeries) {
            Collections.addAll(arr, SeriesHolder.SeriesKind.getNames());
            arr.remove(SeriesHolder.SeriesKind.ANYTHING.getName());
        } else {
            Collections.addAll(arr, MoviesHolder.MovieKind.getNames());
            arr.remove(MoviesHolder.MovieKind.ANYTHING.getName());
        }

        this.adapter = new ArrayAdapter<>(getContext(), R.layout.list_black_text_choose, arr);
        this.listView.setAdapter(this.adapter);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            preformVibration(dialogView, HapticFeedbackConstants.CLOCK_TICK);
            updatedChecked(isSeries);
        });

        check(isSeries);

        dialog.setOnDismissListener(dialog -> {
            if (this.listView.getCheckedItemCount() <= 0) {
                preformVibration(dialogView, HapticFeedbackConstants.LONG_PRESS);
            }
        });
    }
}
