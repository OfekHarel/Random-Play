package com.horizon.randomplay.Activities.movies;

import android.view.View;
import android.widget.ImageView;

import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.movies.MoviesHolder;

public class MovieLogosManager {

    public enum LogoSeries {
        MARVEL(R.id.marvel_logo),
        DISNEY_ANIM(R.id.disney_logo);

        private final int logoId;
        LogoSeries(int id) {
            this.logoId = id;
        }

        public void display(BaseActivity activity) {
            for (LogoSeries s: LogoSeries.values()) {
                activity.findViewById(s.getLogoId()).setVisibility(View.INVISIBLE);
            }
            ImageView imageView = activity.findViewById(this.logoId);
            imageView.setVisibility(View.VISIBLE);
        }

        public int getLogoId() {
            return logoId;
        }
    }

    public static void showLogo(BaseActivity activity, MoviesHolder.MovieKind dispKind) {
        switch (dispKind) {
            case MARVEL:
                LogoSeries.MARVEL.display(activity);
                break;
            case DISNEY_ANIM:
                LogoSeries.DISNEY_ANIM.display(activity);
                break;
        }
    }
}
