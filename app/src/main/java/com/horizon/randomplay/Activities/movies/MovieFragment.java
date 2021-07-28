package com.horizon.randomplay.Activities.movies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.horizon.randomplay.Activities.base.BaseFragment;
import com.horizon.randomplay.Activities.RandomActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Vars;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.Objects;

public class MovieFragment extends BaseFragment {

    private NumberPicker movieScroll;
    private NumberPicker moodScroll;

    private String[] moods;

    public MovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        SharedData.getInstance(getContext());
        MoviesHolder.init(getContext());

        this.movieScroll = rootView.findViewById(R.id.movies_scroll);
        this.moodScroll = rootView.findViewById(R.id.movies_scroll_mood);
        ImageButton genBtn = rootView.findViewById(R.id.movies_gen_btn);

        moods = updateMoodsArr(Mood.getNames(Objects.requireNonNull(MoviesHolder.getAllMovies()
                .get(Vars.movie_choice.x.getName())).getAvailableMoods()));

        initPicker(getChosenAsArr(), this.movieScroll);
        initPicker(moods, this.moodScroll);

        this.movieScroll.setOnValueChangedListener((picker, oldVal, newVal) -> {

            MoviesHolder.MovieKind movie = MoviesHolder.MovieKind
                    .getByValue(getChosenAsArr()[picker.getValue() - 1]);
            Vars.movie_choice.x = movie;

            assert movie != null;
            moods = updateMoodsArr(Mood.getNames(Objects.requireNonNull(MoviesHolder.getAllMovies()
                    .get(movie.getName())).getAvailableMoods()));
            initPicker(moods, moodScroll);

        });

        this.movieScroll.setOnScrollListener((picker, scrollState) -> {
            preformVibration(requireContext(), 2);
            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                preformVibration(picker, HapticFeedbackConstants.CLOCK_TICK);
            }
        });

        this.moodScroll.setOnValueChangedListener((picker, oldVal, newVal) -> Vars.movie_choice.y = Mood.getByValue(moods[newVal - 1]));
        this.moodScroll.setOnScrollListener((picker, scrollState) -> {
            preformVibration(requireContext(), 2);
            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                preformVibration(picker, HapticFeedbackConstants.CLOCK_TICK);
            }
        });
        restoreLatestPick();

        genBtn.setOnClickListener(this::clickGenerate);

        return rootView;
    }

    private void initPicker(String[] arr, NumberPicker picker) {
        picker.setDisplayedValues(null);
        picker.setWrapSelectorWheel(true);
        picker.setMinValue(1);
        picker.setMaxValue(arr.length);
        picker.setDisplayedValues(arr);
        picker.setValue(1);
        if (arr.length <= 1) {
            picker.setOnClickListener(v -> picker.stopNestedScroll());
        }
    }

    private String[] updateMoodsArr(String[] newMoods) {

        if (Vars.movie_choice.x.equals(MoviesHolder.MovieKind.ANYTHING)) {
            ArrayList<Mood> moods = MoviesHolder.getAllAvailableMoods();
            Objects.requireNonNull(MoviesHolder.getAllMovies().get(Vars.movie_choice.x.getName())).removeMoods();

            String[] arr = new String[moods.size() + 1];
            arr[0] = Mood.ANYTHING.getName();

            for (int i = 0; i < moods.size() ;i++) {
                arr[i + 1] = moods.get(i).getName();
            }

            return arr;

        } else {
            String[] arr = new String[newMoods.length + 1];
            arr[0] = Mood.ANYTHING.getName();
            System.arraycopy(newMoods, 0, arr, 1, arr.length - 1);

            return arr;
        }
    }

    private void restoreLatestPick() {
        // movie
        for (int i = 0; i < MoviesHolder.MovieKind.getNames().length; i ++) {
            if (MoviesHolder.MovieKind.getNames()[i].equals(Vars.movie_choice.x.getName())) {
                this.movieScroll.setValue(i + 1);
            }
        }

        // mood
        moods = updateMoodsArr(Mood.getNames(Objects.requireNonNull(MoviesHolder.getAllMovies()
                .get(Vars.movie_choice.x.getName())).getAvailableMoods()));
        initPicker(moods, moodScroll);
        for (int i = 0; i < moods.length; i ++) {
            if (moods[i].equals(Vars.movie_choice.y.getName())) {
                this.moodScroll.setValue(i + 1);
            }
        }

    }

    private void clickGenerate(View view) {
        preformVibration(view, HapticFeedbackConstants.LONG_PRESS);
        Vars.isSeries = false;
        redirectActivity((AppCompatActivity) getActivity(), RandomActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        moods = updateMoodsArr(Mood.getNames(Objects.requireNonNull(MoviesHolder.getAllMovies()
                .get(Vars.movie_choice.x.getName())).getAvailableMoods()));

        initPicker(getChosenAsArr(), this.movieScroll);
        initPicker(moods, this.moodScroll);

        restoreLatestPick();
    }

    private String[] getChosenAsArr() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> sharedArr = SharedData.getInstance().getMovieHandler().getChosen();
        if (sharedArr.size() > 1) {
            arrayList.add(MoviesHolder.MovieKind.ANYTHING.getName());
        }
        arrayList.addAll(sharedArr);
        String[] temp = new String[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            temp[i] = arrayList.get(i);
        }
        return temp;
    }
}