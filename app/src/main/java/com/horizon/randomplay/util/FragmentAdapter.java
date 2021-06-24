package com.horizon.randomplay.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.horizon.randomplay.Activities.HistoryFragment;
import com.horizon.randomplay.Activities.HomeFragment;
import com.horizon.randomplay.Activities.PrefFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public enum Tabs {
        HISTORY(0),
        HOME(1),
        PREFF(2);

        private final int tabNum;
        private Tabs(final int tabNum) {
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
                return new HomeFragment();
            case 2:
                return new PrefFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "History";
            case 1:
                return "Home";
            case 2:
                return "Preferences";
        }
        return null;
    }
}

