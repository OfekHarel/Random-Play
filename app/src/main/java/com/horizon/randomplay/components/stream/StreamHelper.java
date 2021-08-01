package com.horizon.randomplay.components.stream;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class StreamHelper {
    public static void open(StreamingServices s, Long id, AppCompatActivity activity) {
        switch (s) {
            case NETFLIX:
                netflix(activity, id);
        }
        embrace(activity);
    }

    private static void embrace(AppCompatActivity activity) {
        Toast.makeText(activity, "Enjoy!", Toast.LENGTH_SHORT).show();
    }

    private static void netflix(AppCompatActivity activity, Long id) {
        String netflixID = id.toString();
        String watchUrl = "http://www.netflix.com/watch/" + netflixID;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("com.netflix.mediaclient", "com.netflix.mediaclient.ui.launch.UIWebViewActivity");
            intent.setData(Uri.parse(watchUrl));
            activity.startActivity(intent);
        }
        catch(Exception e)
        {
            // netflix app isn't installed, send to website.
            Toast.makeText(activity, "Please install the Netflix App!", Toast.LENGTH_SHORT).show();
        }
    }
}
