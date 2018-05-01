package com.golike.magicgankclient.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    public void serFragments(List<Fragment> list) {
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        if (mList == null)
            return null;
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return this.mList != null ? mList.size() : 0;
    }
}
