package com.jmslam.customview_1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinglx on 2018/7/9.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    String[] list;

    List<Fragment> fragments = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm, String[] list, List<Fragment> fragments) {
        super(fm);

        this.list = list;
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return list[position];
    }

    @Override
    public int getCount() {

        return fragments != null ? fragments.size() : 0;
    }

}