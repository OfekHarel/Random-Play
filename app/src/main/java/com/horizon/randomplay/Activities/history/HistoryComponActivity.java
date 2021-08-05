package com.horizon.randomplay.Activities.history;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.horizon.randomplay.Activities.MainActivity;
import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.Activities.series.SeriesLogosManager;
import com.horizon.randomplay.R;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.series.MoodsSeries;
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

        MoodsSeries series = Vars.s_historyCompon.x;
        Episode episode = Vars.s_historyCompon.y;

        TextView moodViewer = findViewById(R.id.h_e_moods);
        ArrayList<String> moodsViewArr = SeriesHolder.getModesByEpisode(series, episode);
        String moodsText = String.join(", ", moodsViewArr);
        if (moodsViewArr.size() > 0) {
            moodViewer.setText(String.format("Episode Mood: %s.", moodsText));
            moodViewer.setVisibility(View.VISIBLE);
        } else {
            moodViewer.setVisibility(View.INVISIBLE);
        }

        SeriesLogosManager.showLogo(this, Objects.requireNonNull(SeriesHolder.SeriesKind.getByValue(series.getName())));

        int seasonNum = series.getSeason(episode).getNumber();
        TextView genInfo = findViewById(R.id.h_info);
        genInfo.setText(String.format("Season %d Episode %d", seasonNum, episode.getNumber()));

        TextView epName = findViewById(R.id.h_episode_name);
        epName.setText(episode.getName());
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }

}