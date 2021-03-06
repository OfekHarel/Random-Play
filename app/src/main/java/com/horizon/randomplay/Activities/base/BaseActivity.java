package com.horizon.randomplay.Activities.base;

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

import com.horizon.randomplay.R;

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
     *
     * @param view - the context
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
     *
     * @param context          - the context where it will show up.
     * @param title            - the title of the window.
     * @param msg              - the pop up window description
     * @param positiveBtn      - positive option string
     * @param positiveListener - what will happen when pressing positive.
     */
    protected void setPopWin(Context context, String title, String msg, String positiveBtn, String falseBtn,
                             DialogInterface.OnClickListener positiveListener) {

        AlertDialog dialog = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert).create();
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, positiveBtn, positiveListener);
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, falseBtn, (d, which)-> d.cancel());
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.white));
        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.white));

    }
}
