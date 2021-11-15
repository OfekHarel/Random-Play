package com.horizon.randomplay.Activities.movies;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.horizon.randomplay.Activities.StreamingServicesLogoManager;
import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.Activities.MainActivity;
import com.horizon.randomplay.Activities.RandomActivity;
import com.horizon.randomplay.Generator;
import com.horizon.randomplay.R;
import com.horizon.randomplay.components.HistoryComp;
import com.horizon.randomplay.components.movies.MoodMovieCollection;
import com.horizon.randomplay.components.movies.Movie;
import com.horizon.randomplay.components.stream.StreamHelper;
import com.horizon.randomplay.components.stream.StreamingServices;
import com.horizon.randomplay.movies.MovieLogosManager;
import com.horizon.randomplay.movies.MoviesHolder;
import com.horizon.randomplay.components.Mood;
import com.horizon.randomplay.util.SharedData;
import com.horizon.randomplay.util.Tuple;
import com.horizon.randomplay.util.Vars;

import java.util.ArrayList;
import java.util.Objects;

public class MovieResultActivity extends BaseActivity {

    private Movie movie;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_result);

        MoviesHolder.init(this);

        Mood mood = Vars.movie_choice.y;
        Tuple<MoodMovieCollection, Movie> gen = Generator.getInstance(this).getMovieHandler().generate(Vars.movie_choice.x, mood);
        MoodMovieCollection movieCollection = gen.x;
        this.movie = gen.y;

        MovieLogosManager.showLogo(this, Objects.requireNonNull(MoviesHolder.MovieKind.getByValue(movieCollection.getName())));

        TextView genInfo = findViewById(R.id.movie_gen_info);
        genInfo.setText(movie.getName());

        TextView moodViewer = findViewById(R.id.movie_moods);
        if (mood.equals(Mood.ANYTHING)) {
            ArrayList<String> moodsViewArr = MoviesHolder.getModesByMovie(movieCollection, movie);
            String moodsText = String.join(", ", moodsViewArr);
            if (moodsViewArr.size() >= 1) {
                moodViewer.setText(String.format("Movie Moods: %s.", moodsText));
                moodViewer.setVisibility(View.VISIBLE);
            }
        } else {
            moodViewer.setVisibility(View.INVISIBLE);
        }

        Button watchNowBtn = findViewById(R.id.watch_now);
        if (movie.getId() != null) {
            watchNowBtn.setVisibility(View.VISIBLE);
            StreamingServicesLogoManager.showLogo(this, StreamingServices.NETFLIX);
        } else {
            watchNowBtn.setVisibility(View.INVISIBLE);
            StreamingServicesLogoManager.goBlank(this);
        }

        preformVibration(7);

        SharedData.getInstance().getMovieHandler().addHistory(new HistoryComp(movieCollection.getName(), movie.getName()).toString());
    }

    public void clickRegenerate(View view) {
        Vars.isSeries = false;
        redirectActivity(this, RandomActivity.class);
        preformVibration(view);
    }

    @Override
    public void onBackPressed() {
        redirectActivity(this, MainActivity.class);
    }

    public void clickWatchNow(View view) {
        StreamHelper.open(StreamingServices.NETFLIX, this.movie.getId(), this);
    }
}