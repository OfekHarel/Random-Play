package com.horizon.randomplay.Activities;

import android.view.View;
import android.widget.ImageView;

import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.components.stream.StreamingServices;

public class StreamingServicesLogoManager extends BaseActivity {

    public enum StreamingServiceLogo {
        NETFLIX(R.id.netflix_logo);

        private final int logoId;

        StreamingServiceLogo(int id) {
            this.logoId = id;
        }

        public void display(BaseActivity activity, int disp) {
            for (StreamingServiceLogo s : StreamingServiceLogo.values()) {
                activity.findViewById(s.getLogoId()).setVisibility(View.INVISIBLE);
            }
            ImageView imageView = activity.findViewById(this.logoId);
            imageView.setVisibility(disp);
        }

        public int getLogoId() {
            return logoId;
        }
    }

    public static void showLogo(BaseActivity activity, StreamingServices dispKind) {
        switch (dispKind) {
            case NETFLIX:
                StreamingServiceLogo.NETFLIX.display(activity, View.VISIBLE);
                break;
        }
    }

    public static void goBlank(BaseActivity activity) {
        StreamingServiceLogo.values()[0].display(activity, View.INVISIBLE);
    }
}
