package com.appmk.book.AOVDDFHFPJTMQIDS;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by BEK on 18.02.2017.
 */

public class SonsAdapter extends FragmentStatePagerAdapter {

   // private ArrayList<Fragment> fragments;
    private String toFindText="";
    protected static final String[] CONTENT = new String[] { "А", "Ә", "Б", "В", "Г", "Ғ", "Д", "Е", "Ж","З", "И",
            "К", "Қ", "Л", "М", "Н", "О", "Ө", "П", "Р", "С", "Т", "У", "Ұ", "Ү", "Ф", "Х", "Ц", "Ш", "Ы", "І", "Э", "Я"};


    public SonsAdapter(FragmentManager fm) {
        super(fm);
        //this.fragments = fragments;
    }



    @Override
    public Fragment getItem(int position) {
        return FirstFragment.newInstance(position);

    }


    @Override
    public int getCount() {
        return CONTENT.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return SonsAdapter.CONTENT[position % CONTENT.length];
    }
}
