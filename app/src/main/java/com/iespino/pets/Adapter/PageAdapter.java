package com.iespino.pets.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class PageAdapter extends FragmentPagerAdapter
{
    ArrayList<Fragment> fragments;

    public PageAdapter(FragmentManager fm, ArrayList<Fragment>fragments)
    {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}
