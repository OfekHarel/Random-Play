package com.horizon.randomplay.Activities.series;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.Activities.MainActivity;
import com.horizon.randomplay.Activities.RandomActivity;
import com.horizon.randomplay.Generator;
import com.horizon.randomplay.R;
import com.horizon.randomplay.components.stream.StreamHelper;
import com.horizon.randomplay.components.stream.StreamingServices;
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

public class SeriesResultActivity extends BaseActivity {

    private Episode episode;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_result);

        SeriesHolder.init(this);

        Mood mood = Vars.series_choice.y;
        Tuple<MoodsSeries, Episode> gen = Generator.getInstance(this).getSeriesHandler().generate(Vars.series_choice.x, mood);
        MoodsSeries series = gen.x;
        this.episode = gen.y;
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
                moodViewer.setText(String.format("Episode Moods: %s.", moodsText));
                moodViewer.setVisibility(View.VISIBLE);
            }
        } else {
            moodViewer.setVisibility(View.INVISIBLE);
        }

        Button watchNowBtn = findViewById(R.id.series_watch_now);
        if (episode.getId() != null) {
            watchNowBtn.setVisibility(View.VISIBLE);
        } else {
            watchNowBtn.setVisibility(View.INVISIBLE);
        }

        preformVibration(7);

        SharedData.getInstance().getSeriesHandler().addHistory(new HistoryComp(series.getName(), seasonNum, episode.getNumber()).toString());
    }

    public void clickRegenerate(View view) {
        Vars.isSeries = true;
        redirectActivity(this, RandomActivity.class);
        preformVibration(view);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }

    public void clickWatchNow(View view) {
        StreamHelper.open(StreamingServices.NETFLIX, this.episode.getId(), this);
    }
}