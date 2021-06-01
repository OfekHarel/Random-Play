package com.horizon.randomplay.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.horizon.randomplay.R;

import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation;

public class RandomActivity extends BaseActivity {

    private TextView rightNum;
    private TextView leftNum;

    private RandomNumberAnimation rightNumGen;
    private RandomNumberAnimation leftNumGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        this.rightNum = (TextView) findViewById(R.id.right_gen_num);
        this.rightNumGen = new RandomNumberAnimation(this.rightNum);
        this.rightNumGen.setFPS(25);

        this.leftNum = (TextView) findViewById(R.id.left_gen_num);
        this.leftNumGen = new RandomNumberAnimation(this.leftNum);
        this.leftNumGen.setFPS(25);

        RandomActivity inst = this;

        Handler handler = new Handler();
        handler.postAtTime(()-> leftNumGen.start(), 2500);
        handler.postDelayed(()-> rightNumGen.start(), 250);

        handler.postDelayed(()-> {
            leftNumGen.stop(true);
            rightNumGen.stop(true);
            redirectActivity(this, ResultActivity.class);
        }, 2500);


    }
}