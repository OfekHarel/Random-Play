package com.horizon.randomplay.Activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
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

public class ResultActivity extends BaseActivity {

    private enum LogoSeries {
        THE_BIG_BANG_THEORY(R.id.tbbt_logo_logo),
        HOW_I_MET_YOUR_MOTHER(R.id.himym_logo),
        FRIENDS(R.id.friends_logo),
        BROOKLYN_NINE_NINE(R.id.bnn_logo),
        THE_OFFICE(R.id.the_office_logo);

        private final int logoId;
        LogoSeries(int id) {
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

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        MoodsSeries series = SeriesHolder.getAllSeries().get(Vars.choice.x.getName());
        Mood mood = Vars.choice.y;
        Episode episode = Genrator.getInstance().genEpisode(series, mood);
        int seasonNum = series.getSeason(episode).getNumber();

        showLogo();

        TextView genInfo = findViewById(R.id.gen_info);
        genInfo.setText(String.format("Season %d Episode %d", seasonNum, episode.getNumber()));

        TextView epName = findViewById(R.id.episode_name);
        epName.setText(episode.getName());
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
            case BROOKLYN_NINE_NINE:
                LogoSeries.BROOKLYN_NINE_NINE.display(this);
                break;
            case THE_OFFICE:
                LogoSeries.THE_OFFICE.display(this);
                break;
        }
    }

    public void clickRegenerate(View view) {
        redirectActivity(this, RandomActivity.class);
        preformVibration(view, HapticFeedbackConstants.LONG_PRESS);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }
}