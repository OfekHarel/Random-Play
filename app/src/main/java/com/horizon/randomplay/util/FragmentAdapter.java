package com.horizon.randomplay.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.horizon.randomplay.Activities.history.HistoryFragment;
import com.horizon.randomplay.Activities.movies.MovieFragment;
import com.horizon.randomplay.Activities.series.SeriesFragment;
import com.horizon.randomplay.Activities.preff.PrefFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public enum Tabs {
        HISTORY(0),
        SERIES(1),
        MOVIE(2),
        SETTINGS(3);

        private final int tabNum;

        Tabs(final int tabNum) {
            this.tabNum = tabNum;
        }

        public int getTabNum() {
            return tabNum;
        }
    }

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HistoryFragment();
            case 1:
                return new SeriesFragment();
            case 2:
                return new MovieFragment();
            case 3:
                return new PrefFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return Tabs.values().length;
    }

    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "History";
            case 1:
                return "Series";
            case 2:
                return "Movies";
            case 3:
                return "Settings";
        }
        return null;
    }
}

