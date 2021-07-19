package com.horizon.randomplay.Activities.series;

import android.os.Bundle;
import android.widget.TextView;

import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.Activities.MainActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.components.series.MoodsSeries;
import com.horizon.randomplay.util.Vars;

import java.util.Locale;
import java.util.Objects;

public class SeriesInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_info);

        SeriesHolder.init(this);

        TextView info = findViewById(R.id.ep_se_info);
        TextView moods = findViewById(R.id.series_moods);

        MoodsSeries s = SeriesHolder.getAllSeries().get(Vars.prefInfoChoice.getName());
        assert s != null;

        SeriesLogosManager.showLogo(this, Objects.requireNonNull(SeriesHolder.SeriesKind.getByValue(s.getName())));

        String text_i = String.format(Locale.US, "Has:\n %d Seasons - %d Episodes", s.getSeasons().size(), s.countEpisodes());
        info.setText(text_i);

        String text_m = String.format(Locale.US, "Has %d Moods", s.getAvailableMoods().size());
        moods.setText(text_m);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }
}