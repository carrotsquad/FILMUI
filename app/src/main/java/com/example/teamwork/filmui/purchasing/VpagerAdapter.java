package com.example.teamwork.filmui.purchasing;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class VpagerAdapter extends FragmentStatePagerAdapter {
    public Context mContext ;
    public Fragment[] fragmentsList;
    public List<String> tabTitle;
    public VpagerAdapter(FragmentManager fm,Context context,Fragment[] fragments,List<String> tabTitle){
        super(fm);
        this.mContext = context;
        this.fragmentsList = fragments;
        this.tabTitle = tabTitle;
    }
    @Override
    public Fragment getItem(int position){
        return fragmentsList[position];
    }

    @Override
    public  int getCount(){
        return tabTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitle.get(position);
    }
}
