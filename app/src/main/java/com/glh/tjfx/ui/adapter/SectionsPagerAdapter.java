package com.glh.tjfx.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.glh.tjfx.base.BaseFragment;
import com.glh.tjfx.ui.fragment.DataFragment;
import com.glh.tjfx.ui.fragment.StationFragment;
import com.glh.tjfx.ui.fragment.WellFragment;
import com.glh.tjfx.ui.interfaces.IDataView;
import com.glh.tjfx.ui.interfaces.IMainView;
import com.glh.tjfx.ui.interfaces.IStationView;
import com.glh.tjfx.ui.interfaces.IWellView;

import java.util.List;
import java.util.Map;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> list;

    public SectionsPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
