package com.horizon.randomplay.Activities.series;

import android.view.View;
import android.widget.ImageView;

import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.series.SeriesHolder;

public class SeriesLogosManager {

    public enum LogoSeries {
        THE_BIG_BANG_THEORY(R.id.tbbt_logo_logo),
        HOW_I_MET_YOUR_MOTHER(R.id.himym_logo),
        FRIENDS(R.id.friends_logo),
        BROOKLYN_NINE_NINE(R.id.bnn_logo),
        THE_OFFICE(R.id.the_office_logo),
        RICK_AND_MORTY(R.id.rick_and_morty_logo),
        MODERN_FAMILY(R.id.modern_family_logo),
        GRAYS_ANATOMY(R.id.grays_anatomy_logo),
        ATLA(R.id.atla_logo),
        TLOK(R.id.tlog_logo),
        BLACK_MIRROR(R.id.black_mirror_logo);

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

    public static void showLogo(BaseActivity activity, SeriesHolder.SeriesKind dispKind) {
        switch (dispKind) {
            case FRIENDS:
                LogoSeries.FRIENDS.display(activity);
                break;
            case HOW_I_MET_YOUR_MOTHER:
                LogoSeries.HOW_I_MET_YOUR_MOTHER.display(activity);
                break;
            case THE_BIG_BANG_THEORY:
                LogoSeries.THE_BIG_BANG_THEORY.display(activity);
                break;
            case BROOKLYN_NINE_NINE:
                LogoSeries.BROOKLYN_NINE_NINE.display(activity);
                break;
            case THE_OFFICE:
                LogoSeries.THE_OFFICE.display(activity);
                break;
            case RICK_AND_MORTY:
                LogoSeries.RICK_AND_MORTY.display(activity);
                break;
            case MODERN_FAMILY:
                LogoSeries.MODERN_FAMILY.display(activity);
                break;
            case GRAYS_ANATOMY:
                LogoSeries.GRAYS_ANATOMY.display(activity);
                break;
            case AVATAR_THE_LAST_AIRBENDER:
                LogoSeries.ATLA.display(activity);
                break;
            case THE_LEGEND_OF_KORRA:
                LogoSeries.TLOK.display(activity);
                break;
            case BLACK_MIRROR:
                LogoSeries.BLACK_MIRROR.display(activity);
                break;
        }
    }
}
