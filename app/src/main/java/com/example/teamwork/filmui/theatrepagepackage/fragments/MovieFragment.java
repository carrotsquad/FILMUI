package com.example.teamwork.filmui.theatrepagepackage.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.adapters.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * “电影”页面
 */

public class MovieFragment extends Fragment {

    /**第二级滑动页标题**/
    public static final String []sTitle_2 = new String[]{"正在热映","即将上映"};

    /** 二级滑动标签页页的ViewPager和TabLayout **/
    private ViewPager mViewPager_2;
    private TabLayout mTabLayout_2;

    public static Fragment newInstance(){
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_1,null);
        /* 滑动到第一个页面"电影" */
        /* 初始化第二级滑动页 */
        mViewPager_2 = (ViewPager) view.findViewById(R.id.vp_content_2);
        mTabLayout_2 = (TabLayout) view.findViewById(R.id.tabLayout_2);
        mTabLayout_2.setupWithViewPager(mViewPager_2);

        /*
         * 设置第二级TabLayout监听器
         */
        mTabLayout_2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            } });

        /* 实例化 */
        List<Fragment> fragments_2 = new ArrayList<>();
        /* 加入第二级的两个碎片 */
        fragments_2.add(InTheatreFragment.newInstance());
        fragments_2.add(ComingSoonFragment.newInstance());
        /* 设置一级碎片适配器 */
        MyFragmentAdapter adapter_2 = new MyFragmentAdapter(getChildFragmentManager(),fragments_2, Arrays.asList(sTitle_2));
        mViewPager_2.setAdapter(adapter_2);

        /* 设置初始化页面 */
        mViewPager_2.setCurrentItem(0);

        /* 自定义tab布局 */
        mTabLayout_2.getTabAt(0).setText(sTitle_2[0]);
        mTabLayout_2.getTabAt(1).setText(sTitle_2[1]);

        /*
         * 设置第二级ViewPager监听器
         */
        mViewPager_2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
