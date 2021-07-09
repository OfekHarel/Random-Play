package com.horizon.randomplay.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * This function allows a simple transition between activities.
     *
     * @param activity The current activity (this)
     * @param aClass   The dest activity .class
     */
    protected void redirectActivity(AppCompatActivity activity, Class aClass) {
        Intent launchNextActivity = new Intent(activity, aClass);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(launchNextActivity);
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
     *
     */
    protected void preformVibration(View view) {
        view.setHapticFeedbackEnabled(true);
        view.performHapticFeedback(android.view.HapticFeedbackConstants.LONG_PRESS);
    }

    protected void preformVibration(int milli) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(VibrationEffect.createOneShot(milli, VibrationEffect.DEFAULT_AMPLITUDE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    /**
     * builds a pop up dialog window.
     * sets a default cancel btn.
     * @param context - the context where it will show up.
     * @param title - the title of the window.
     * @param msg - the pop up window description
     * @param positiveBtn - positive option string
     * @param positiveListener - what will happen when pressing positive.
     * @return the popup win object
     */
    protected AlertDialog.Builder setPopWin(Context context, String title, String msg, String positiveBtn,
                                            DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setNegativeButton("Okay but cooler", (dialog, which) -> dialog.cancel());
        alert.setPositiveButton(positiveBtn, positiveListener);
        return alert;
    }
}
