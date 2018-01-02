package com.dazhentech.faithchallengea.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dazhentech.faithchallengea.momentsfragment.listfragment.MomentsListFragment;

import java.util.List;

/**
 * Created by 51499 on 2017/3/28.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> titles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments,List<String> list) {
        super(fm);
        this.titles = list;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
