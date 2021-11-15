package com.horizon.randomplay.components.stream;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class StreamHelper {
    private static boolean streamed = true;

    public static void open(StreamingServices s, Long id, AppCompatActivity activity) {
        switch (s) {
            case NETFLIX:
                netflix(activity, id);
        }
        if (streamed) {
            embrace(activity);
        } else {
            disembrace(activity);
        }
    }

    private static void embrace(AppCompatActivity activity) {
        Toast.makeText(activity, "Enjoy!", Toast.LENGTH_SHORT).show();
    }

    private static void disembrace(AppCompatActivity activity) {
        // Netflix app isn't installed, send to website.
        Toast.makeText(activity, "Please install the Netflix app!", Toast.LENGTH_SHORT).show();
    }

    private static void netflix(AppCompatActivity activity, Long id) {
        String netflixID = id.toString();
        String watchUrl = "http://www.netflix.com/watch/" + netflixID;
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClassName("com.netflix.mediaclient", "com.netflix.mediaclient.ui.launch.UIWebViewActivity");
            intent.setData(Uri.parse(watchUrl));
            activity.startActivity(intent);
            streamed = true;
        } catch (Exception e) {
            streamed = false;
        }
    }
}
