package com.horizon.randomplay.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.horizon.randomplay.R;

import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation;

public class RandomActivity extends BaseActivity {

    private RandomNumberAnimation rightNumGen;
    private RandomNumberAnimation leftNumGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        TextView rightNum = findViewById(R.id.right_gen_num);
        this.rightNumGen = new RandomNumberAnimation(rightNum);
        this.rightNumGen.setFPS(15);

        TextView leftNum = findViewById(R.id.left_gen_num);
        this.leftNumGen = new RandomNumberAnimation(leftNum);
        this.leftNumGen.setFPS(15);

        Handler handler = new Handler();
        int delay = 1000;
        handler.postAtTime(()-> {
            leftNumGen.start();
            rightNumGen.start();
        }, delay);

        handler.postDelayed(()-> {
            redirectActivity(this, ResultActivity.class);
            leftNumGen.stop(true);
            rightNumGen.stop(true);
        }, delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.rightNumGen.stop(true);
        this.leftNumGen.stop(true);
        redirectActivity(this, ResultActivity.class);
    }
}