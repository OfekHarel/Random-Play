package com.horizon.randomplay.Activities.history;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.horizon.randomplay.Activities.MainActivity;
import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.Activities.movies.MovieLogosManager;
import com.horizon.randomplay.Activities.series.SeriesLogosManager;
import com.horizon.randomplay.R;
import com.horizon.randomplay.components.movies.MoodMovieCollection;
import com.horizon.randomplay.components.movies.Movie;
import com.horizon.randomplay.components.series.Episode;
import com.horizon.randomplay.components.series.MoodsSeries;
import com.horizon.randomplay.components.stream.StreamHelper;
import com.horizon.randomplay.components.stream.StreamingServices;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.series.SeriesHolder;
import com.horizon.randomplay.util.Vars;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryMovieComponActivity extends BaseActivity {

    private Movie movie;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_history_compon);
        MoviesHolder.init(this);

        MoodMovieCollection movieCollection = Vars.m_historyCompon.x;
        this.movie = Vars.m_historyCompon.y;

        TextView moodViewer = findViewById(R.id.h_e_moods);
        ArrayList<String> moodsViewArr = MoviesHolder.getModesByMovie(movieCollection, this.movie);
        String moodsText = String.join(", ", moodsViewArr);
        if (moodsViewArr.size() > 0) {
            moodViewer.setText(String.format("Movie Mood: %s.", moodsText));
            moodViewer.setVisibility(View.VISIBLE);
        } else {
            moodViewer.setVisibility(View.INVISIBLE);
        }

        MovieLogosManager.showLogo(this, Objects.requireNonNull(MoviesHolder.MovieKind.getByValue(movieCollection.getName())));

        TextView genInfo = findViewById(R.id.h_info);
        genInfo.setText(this.movie.getName());

        Button watchNow = findViewById(R.id.watch_now);
        if (movie.getMovieID() == null) {
            watchNow.setVisibility(View.INVISIBLE);
        } else {
            watchNow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }

    public void clickWatchNow(View view) {
        StreamHelper.open(StreamingServices.NETFLIX, this.movie.getMovieID(), this);
    }
}