package com.horizon.randomplay.Activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.horizon.randomplay.Generator;
import com.horizon.randomplay.R;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.HistoryComp;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.series.MoodsSeries;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Tuple;
import com.horizon.randomplay.util.Vars;

import java.util.ArrayList;
import java.util.Objects;

public class ResultActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SeriesHolder.init(this);

        Mood mood = Vars.choice.y;
        Tuple<MoodsSeries, Episode> gen = Generator.getInstance(this).genEpisode(Vars.choice.x, mood);
        MoodsSeries series = gen.x;
        Episode episode = gen.y;
        int seasonNum = series.getSeason(episode).getNumber();

        SeriesLogosManager.showLogo(this, Objects.requireNonNull(SeriesHolder.SeriesKind.getByValue(series.getName())));

        TextView genInfo = findViewById(R.id.gen_info);
        genInfo.setText(String.format("Season %d Episode %d", seasonNum, episode.getNumber()));

        TextView epName = findViewById(R.id.episode_name);
        epName.setText(episode.getName());

        TextView moodViewer = findViewById(R.id.episode_moods);
        if (mood.equals(Mood.ANYTHING)) {
            ArrayList<String> moodsViewArr = SeriesHolder.getModesByEpisode(series, episode);
            String moodsText = String.join(", ", moodsViewArr);
            if (moodsViewArr.size() >= 1) {
                moodViewer.setText(String.format("Episode Mood: %s.", moodsText));
                moodViewer.setVisibility(View.VISIBLE);
            }
        } else {
            moodViewer.setVisibility(View.INVISIBLE);
        }

        preformVibration(7);

        SharedData.getInstance().addHistory(new HistoryComp(series.getName(), seasonNum, episode.getNumber()).toString());
    }

    public void clickRegenerate(View view) {
        redirectActivity(this, RandomActivity.class);
        preformVibration(view);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }

}