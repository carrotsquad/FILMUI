package com.example.teamwork.filmui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamwork.filmui.adapters.MyFragmentAdapter;
import com.example.teamwork.filmui.fragments.FilmPageFragment;
import com.example.teamwork.filmui.fragments.HomePageFragment;
import com.example.teamwork.filmui.fragments.MinePageFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends  AppCompatActivity {


    /** 上次点击返回键的时间 */
    private long lastBackPressed;

    /** 两次点击的间隔时间 */
    private static final int QUIT_INTERVAL = 2000;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    List<Fragment> fragments;

    public static final String []sTitle = new String[]{"首页","电影","我的"};

    private int[] tabIcons = {
            R.drawable.myhome,
            R.drawable.myfilm,
            R.drawable.mine
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);

        /* 初始化页面 */
        initView();


    }


    /**
     * 初始化页面，创建碎片
     */
    private void initView() {

        /* 初始化第一级滑动页 */
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);

        /*
         * 设置第一级TabLayout设置监听器
         */
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            } });

        /* 实例化 */
        fragments = new ArrayList<>();

        /* 加入第一级的两个碎片 */
        fragments.add(HomePageFragment.newInstance());
        fragments.add(FilmPageFragment.newInstance());
        fragments.add(MinePageFragment.newInstance());


        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments, Arrays.asList(sTitle));
        mViewPager.setAdapter(adapter);

        /* 设置初始化页面 */
        mViewPager.setCurrentItem(1);

        /* 自定义tab布局 */
        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        mTabLayout.getTabAt(2).setCustomView(getTabView(2));


        /* 设置监听器 */
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 滑动标签页响应
             * @param position
             */
            @Override public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            } });
    }




    /**
     * 重写返回键响应函数，按两次才退出
     */
    @Override
    public void onBackPressed() {
        long backPressed = System.currentTimeMillis();
        if (backPressed - lastBackPressed > QUIT_INTERVAL) {
            lastBackPressed = backPressed;
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
        } else {
            finish();
            System.exit(0);
        }
    }


    /**
     * 自定义tabView
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab, null);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(sTitle[position]);
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons[position]);
        return view;
    }

}

