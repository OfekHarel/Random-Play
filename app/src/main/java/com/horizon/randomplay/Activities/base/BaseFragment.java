package com.horizon.randomplay.Activities.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
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
        activity.finish();
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

    protected void preformVibration(Context context, int milli) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(VibrationEffect.createOneShot(milli, VibrationEffect.DEFAULT_AMPLITUDE));
    }
}
