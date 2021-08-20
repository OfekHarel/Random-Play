package com.horizon.randomplay.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.Activities.movies.MovieResultActivity;
import com.horizon.randomplay.Activities.series.SeriesResultActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.util.Vars;

import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation;

public class RandomActivity extends BaseActivity {

    private RandomNumberAnimation rightNumGen;
    private RandomNumberAnimation leftNumGen;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        final int FPS = 15;

        TextView rightNum = findViewById(R.id.right_gen_num);
        this.rightNumGen = new RandomNumberAnimation(rightNum);
        this.rightNumGen.setFPS(FPS);

        TextView leftNum = findViewById(R.id.left_gen_num);
        this.leftNumGen = new RandomNumberAnimation(leftNum);
        this.leftNumGen.setFPS(FPS);

        handler = new Handler();
        int delay = 750;
        handler.postAtTime(()-> {
            leftNumGen.start();
            rightNumGen.start();
        }, delay);

        handler.postDelayed(()-> {
            leftNumGen.stop(true);
            rightNumGen.stop(true);
            if (Vars.isSeries) {
                redirectActivity(this, SeriesResultActivity.class);
            } else {
                redirectActivity(this, MovieResultActivity.class);
            }

        }, delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.rightNumGen.stop(true);
        this.leftNumGen.stop(true);
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        redirectActivity(this, MainActivity.class);
    }
}