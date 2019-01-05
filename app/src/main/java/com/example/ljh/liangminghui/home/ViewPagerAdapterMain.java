package com.example.ljh.liangminghui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapterMain extends FragmentStatePagerAdapter {
    private List<Fragment> mDataList;

    public ViewPagerAdapterMain(FragmentManager fm,List<Fragment> list) {
        super(fm);
        mDataList = list;
    }

    @Override
    public Fragment getItem(int i) {
        return mDataList.get(i);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }
}
