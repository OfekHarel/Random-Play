package com.horizon.randomplay.Activities.movies;

import android.view.View;
import android.widget.ImageView;

import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.movies.MoviesHolder;

public class MovieLogosManager extends BaseActivity{

    public enum LogoSeries {
        MARVEL(R.id.marvel_logo),
        DISNEY_ANIM(R.id.disney_logo),
        LORD_OF_THE_RINGS(R.id.lotr_logo),
        HARRY_POTTER(R.id.hp_logo),
        DREAM_WORKS(R.id.dreamworks_logo),
        MY_GOLDENS(R.id.my_goldens_logo);

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
            case LORD_OF_THE_RINGS:
                LogoSeries.LORD_OF_THE_RINGS.display(activity);
                break;
            case HARRY_POTTER:
                LogoSeries.HARRY_POTTER.display(activity);
                break;
            case DREAM_WORKS:
                LogoSeries.DREAM_WORKS.display(activity);
                break;
            case MY_GOLDENS:
                LogoSeries.MY_GOLDENS.display(activity);
                break;
        }
    }
}
