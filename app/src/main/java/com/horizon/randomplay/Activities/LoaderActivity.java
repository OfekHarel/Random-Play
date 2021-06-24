package com.horizon.randomplay.Activities;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import com.horizon.randomplay.R;
import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.util.SharedData;

public class LoaderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        LoaderActivity inst = this;

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SharedData.getInstance(inst);
                SeriesHolder.init(inst);
                redirectActivity(inst, MainActivity.class);
                return null;
            }
        };
        task.execute();
    }
}