package com.horizon.randomplay.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.horizon.randomplay.R;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Vars;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.Objects;

public class SeriesFragment extends BaseFragment {

    private NumberPicker seriesScroll;
    private NumberPicker moodScroll;

    private String[] moods;


    public SeriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SharedData.getInstance(getContext());
        SeriesHolder.init(getContext());

        this.seriesScroll = rootView.findViewById(R.id.series_scroll);
        this.moodScroll = rootView.findViewById(R.id.scroll_mood);
        ImageButton genBtn = rootView.findViewById(R.id.gen_btn);

        moods = updateMoodsArr(Mood.getNames(Objects.requireNonNull(SeriesHolder.getAllSeries()
                .get(Vars.choice.x.getName())).getAvailableMoods()));

        initPicker(getChosenAsArr(), this.seriesScroll);
        initPicker(moods, this.moodScroll);


        this.seriesScroll.setOnValueChangedListener((picker, oldVal, newVal) -> {

            SeriesHolder.SeriesKind series = SeriesHolder
                    .SeriesKind.getByValue(getChosenAsArr()[picker.getValue() - 1]);
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

        if (Vars.choice.x.equals(SeriesHolder.SeriesKind.ANYTHING)) {
            ArrayList<Mood> moods = SeriesHolder.getAviMoods();
            SeriesHolder.getAllSeries().get(Vars.choice.x.getName()).removeMoods();

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

    private void clickGenerate(View view) {
        preformVibration(view, HapticFeedbackConstants.LONG_PRESS);
        redirectActivity((AppCompatActivity) getActivity(), RandomActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        moods = updateMoodsArr(Mood.getNames(Objects.requireNonNull(SeriesHolder.getAllSeries()
                .get(Vars.choice.x.getName())).getAvailableMoods()));

        initPicker(getChosenAsArr(), this.seriesScroll);
        initPicker(moods, this.moodScroll);

        restoreLatestPick();
    }

    private String[] getChosenAsArr() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> sharedArr = SharedData.getInstance().getChosen();
        if (sharedArr.size() > 1) {
            arrayList.add(SeriesHolder.SeriesKind.ANYTHING.getName());
        }
        arrayList.addAll(sharedArr);
        String[] temp = new String[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            temp[i] = arrayList.get(i);
        }
        return temp;
    }

}