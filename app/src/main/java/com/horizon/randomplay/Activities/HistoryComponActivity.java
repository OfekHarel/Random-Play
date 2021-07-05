package com.horizon.randomplay.Activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.horizon.randomplay.R;
import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.MoodsSeries;
import com.horizon.randomplay.util.Vars;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryComponActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_compon);
        SeriesHolder.init(this);

        MoodsSeries series = Vars.historyCompon.x;
        Episode episode = Vars.historyCompon.y;
        int seasonNum = series.getSeason(episode).getNumber();

        SeriesLogosManager.showLogo(this, Objects.requireNonNull(SeriesHolder.SeriesKind.getByValue(series.getName())));

        TextView genInfo = findViewById(R.id.h_info);
        genInfo.setText(String.format("Season %d Episode %d", seasonNum, episode.getNumber()));

        TextView epName = findViewById(R.id.h_episode_name);
        epName.setText(episode.getName());

        TextView moodViewer = findViewById(R.id.h_e_moods);
        ArrayList<String> moodsViewArr = SeriesHolder.getModesByEpisode(series, episode);
        String moodsText = String.join(", ", moodsViewArr);
        if (moodsViewArr.size() > 0) {
            moodViewer.setText(String.format("Episode Mood: %s.", moodsText));
            moodViewer.setVisibility(View.VISIBLE);
        } else {
            moodViewer.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }
}