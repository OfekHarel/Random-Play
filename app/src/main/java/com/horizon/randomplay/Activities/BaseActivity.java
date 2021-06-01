package com.horizon.randomplay.Activities;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    /**
     * This function allows a simple transition between activities.
     *
     * @param activity The current activity (this)
     * @param aClass   The dest activity .class
     */
    protected void redirectActivity(AppCompatActivity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     * Exits the app to the android main screen (home page)
     */
    protected void exit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * preforms a vibration
     * @param view - the context
     * @param type - which vibration type
     */
    protected void preformVibration(View view, int type) {
        view.setHapticFeedbackEnabled(true);
        view.performHapticFeedback(type);
    }

}
