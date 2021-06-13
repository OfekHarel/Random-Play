package com.horizon.randomplay.Activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.horizon.randomplay.Generator;
import com.horizon.randomplay.R;
import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.components.Episode;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.components.MoodsSeries;
import com.horizon.randomplay.util.Tuple;
import com.horizon.randomplay.util.Vars;

import java.util.Objects;

public class ResultActivity extends BaseActivity {

    private enum LogoSeries {
        THE_BIG_BANG_THEORY(R.id.tbbt_logo_logo),
        HOW_I_MET_YOUR_MOTHER(R.id.himym_logo),
        FRIENDS(R.id.friends_logo),
        BROOKLYN_NINE_NINE(R.id.bnn_logo),
        THE_OFFICE(R.id.the_office_logo),
        RICK_AND_MORTY(R.id.rick_and_morty_logo),
        MODERN_FAMILY(R.id.modern_family_logo);

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

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SeriesHolder.init(this);

        Mood mood = Vars.choice.y;
        Tuple<MoodsSeries, Episode> gen = Generator.getInstance().genEpisode(Vars.choice.x, mood);
        MoodsSeries series = gen.x;
        Episode episode = gen.y;
        int seasonNum = series.getSeason(episode).getNumber();

        showLogo(Objects.requireNonNull(SeriesHolder.SeriesKind.getByValue(series.getName())));

        TextView genInfo = findViewById(R.id.gen_info);
        genInfo.setText(String.format("Season %d Episode %d", seasonNum, episode.getNumber()));

        TextView epName = findViewById(R.id.episode_name);
        epName.setText(episode.getName());

        preformVibration(7, VibrationEffect.DEFAULT_AMPLITUDE);
    }

    private void showLogo(SeriesHolder.SeriesKind dispKind) {
        switch (dispKind) {
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
            case RICK_AND_MORTY:
                LogoSeries.RICK_AND_MORTY.display(this);
                break;
            case MODERN_FAMILY:
                LogoSeries.MODERN_FAMILY.display(this);
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