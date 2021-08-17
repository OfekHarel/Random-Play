package com.horizon.randomplay.Activities.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.horizon.randomplay.util.FragmentAdapter;
import com.horizon.randomplay.util.Vars;

public class BaseFragment extends Fragment {

    protected FragmentAdapter.Tabs tab = null;

    protected BaseFragment(FragmentAdapter.Tabs tab) {
        this.tab = tab;
    }
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
     *
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

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vars.currentTab = tab;
    }

    @Override
    public void onResume() {
        super.onResume();
        Vars.currentTab = tab;
    }
}
