package com.example.com.project;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by USER on 2016-11-19.
 */

public class CPagerAdapter extends FragmentStatePagerAdapter{

    public CPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
