package com.jmslam.customview_1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jmslam.customview_1.widget.AvatarView;
import com.jmslam.customview_1.widget.CameraView;
import com.jmslam.customview_1.widget.DashBoard;
import com.jmslam.customview_1.widget.ImageTextView;
import com.jmslam.customview_1.widget.PieChart;
import com.jmslam.customview_1.widget.SportView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    TabLayout tab;
    ViewPager vp;

    String[] titles = {"DashBoard", "PieChart", "AvatarView","SportView","ImageTextView","CameraView"};
    List<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);

        for (int i = 0; i < titles.length; i++) {
            tab.addTab(tab.newTab().setText(titles[i]));
        }

        fragments.add(new ViewFragment(new DashBoard(this)));
        fragments.add(new ViewFragment(new PieChart(this)));
        fragments.add(new ViewFragment(new AvatarView(this)));

        fragments.add(new ViewFragment(new SportView(this)));
        fragments.add(new ViewFragment(new ImageTextView(this)));
        fragments.add(new ViewFragment(new CameraView(this)));

        tab.addOnTabSelectedListener(this);
        adapter = new MyPagerAdapter(getSupportFragmentManager(), titles, fragments);

        vp.setAdapter(adapter);

        tab.setupWithViewPager(vp);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        vp.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
