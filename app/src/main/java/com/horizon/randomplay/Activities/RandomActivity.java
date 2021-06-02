package com.horizon.randomplay.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.horizon.randomplay.Genrator;
import com.horizon.randomplay.R;

import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation;

public class RandomActivity extends BaseActivity {

    private TextView rightNum;
    private TextView leftNum;

    private RandomNumberAnimation rightNumGen;
    private RandomNumberAnimation leftNumGen;

    private final int delay = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        this.rightNum = findViewById(R.id.right_gen_num);
        this.rightNumGen = new RandomNumberAnimation(this.rightNum);
        this.rightNumGen.setFPS(15);

        this.leftNum = findViewById(R.id.left_gen_num);
        this.leftNumGen = new RandomNumberAnimation(this.leftNum);
        this.leftNumGen.setFPS(15);

        Handler handler = new Handler();
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
}