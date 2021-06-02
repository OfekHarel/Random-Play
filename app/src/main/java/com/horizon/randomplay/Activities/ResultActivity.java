package com.horizon.randomplay.Activities;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.horizon.randomplay.Genrator;
import com.horizon.randomplay.R;
import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.MoodsSeries;
import com.horizon.randomplay.util.Vars;

import java.util.Random;

public class ResultActivity extends BaseActivity {

    private enum LogoSeries {
        THE_BIG_BANG_THEORY(R.id.tbbt_logo_logo),
        HOW_I_MET_YOUR_MOTHER(R.id.himym_logo),
        FRIENDS(R.id.friends_logo);

        private final int logoId;
        private LogoSeries(int id) {
            this.logoId = id;
        }

        public void display(BaseActivity activity) {
            for (LogoSeries s: LogoSeries.values()) {
                activity.findViewById(s.getLogoId()).setVisibility(View.INVISIBLE);
            }
            ImageView imageView = activity.findViewById(this.logoId);
            imageView.setVisibility(View.VISIBLE);

        }

        public int getLogoId() {
            return logoId;
        }
    }

    private MoodsSeries series;
    private Mood mood;
    private Episode episode;
    private int seasonNum;

    private TextView genInfo;
    private TextView epName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.series = SeriesHolder.getAllSeries().get(Vars.choice.x.getName());
        this.mood = Vars.choice.y;
        this.episode = Genrator.getInstance().genEpisode(this.series, this.mood);
        this.seasonNum = this.series.getSeason(this.episode).getNumber();

        showLogo();

        this.genInfo = findViewById(R.id.gen_info);
        this.genInfo.setText("Season " + this.seasonNum + " Episode " + this.episode.getNumber());

        this.epName = findViewById(R.id.episode_name);
        this.epName.setText(this.episode.getName());
    }

    private void showLogo() {
        switch (Vars.choice.x) {
            case FRIENDS:
                LogoSeries.FRIENDS.display(this);
                break;
            case HOW_I_MET_YOUR_MOTHER:
                LogoSeries.HOW_I_MET_YOUR_MOTHER.display(this);
                break;
            case THE_BIG_BANG_THEORY:
                LogoSeries.THE_BIG_BANG_THEORY.display(this);
                break;
        }
    }

    public void clickRegenerate(View view) {
        redirectActivity(this, RandomActivity.class);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }
}