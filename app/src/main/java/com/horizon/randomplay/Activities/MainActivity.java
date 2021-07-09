package com.horizon.randomplay.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.horizon.randomplay.R;
import com.horizon.randomplay.SeriesHolder;
import com.horizon.randomplay.util.FragmentAdapter;
import com.horizon.randomplay.util.SharedData;


public class MainActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager2 pager2;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedData.getInstance(this);
        SeriesHolder.init(this);

        MainActivity cont = this;

        this.tabLayout = findViewById(R.id.tab_layout);
        this.pager2 = findViewById(R.id.view_pager);

        FragmentManager fm = getSupportFragmentManager();
        this.fragmentAdapter = new FragmentAdapter(fm, getLifecycle());
        this.pager2.setAdapter(this.fragmentAdapter);

        this.tabLayout.addTab(this.tabLayout.newTab().setText(this.fragmentAdapter.getPageTitle(FragmentAdapter.Tabs.HISTORY.getTabNum())));
        this.tabLayout.addTab(this.tabLayout.newTab().setText(this.fragmentAdapter.getPageTitle(FragmentAdapter.Tabs.HOME.getTabNum())));
        this.tabLayout.addTab(this.tabLayout.newTab().setText(this.fragmentAdapter.getPageTitle(FragmentAdapter.Tabs.PREFF.getTabNum())));
        this.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        this.pager2.setCurrentItem(FragmentAdapter.Tabs.HOME.getTabNum());
        this.tabLayout.getTabAt(FragmentAdapter.Tabs.HOME.getTabNum()).select();

        this.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (SharedData.getInstance().getChosen().size() <= 0 && tab.getText() != fragmentAdapter.getPageTitle(FragmentAdapter.Tabs.PREFF.getTabNum())) {
                    pager2.setCurrentItem(FragmentAdapter.Tabs.PREFF.getTabNum());
                    tabLayout.selectTab(tabLayout.getTabAt(FragmentAdapter.Tabs.PREFF.getTabNum()));
                    setPopWin(cont, "Note", "You must choose at list one series!", "Okay", (dialog, which) ->{
                    }).show();
                } else {
                    pager2.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        this.pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public void onBackPressed() {
        switch (this.pager2.getCurrentItem()) {
            case 0:
            case 2:
                pager2.setCurrentItem(FragmentAdapter.Tabs.HOME.getTabNum());
                tabLayout.selectTab(tabLayout.getTabAt(FragmentAdapter.Tabs.HOME.getTabNum()));
                break;
            case 1:
                exit();
        }
    }

    public void clickInfo(View view) {
    }
}