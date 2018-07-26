package com.example.teamwork.filmui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private FilmCon[] filmCons = {new FilmCon("我不是药神",R.drawable.yaoshen,"徐峥 王传君 周一围 谭卓","文牧野",8.9,20180705,608687),
    new FilmCon("超人总动员2",R.drawable.superman,"格雷格·T·尼尔森","布拉德·伯德",8.1,20180622,80000),
    new FilmCon("肆式青春",R.drawable.sishi,"坂泰斗 寿美菜子","易小星 竹内良贵 李豪凌",0,20180804,4600),
    new FilmCon("邪不压正",R.drawable.fare,"彭于晏 姜文 廖凡","姜文",7.1,20180713,186700),
    new FilmCon("摩天营救",R.drawable.skyscraper,"道恩·强森 内芙·坎贝尔","罗森·马歇尔·瑟伯", 6.6,20180720,50000),
    new FilmCon("侏罗纪世界2",R.drawable.jus,"克里斯·帕拉特 布莱丝·达拉斯·霍华德","胡安·安东尼奥·巴亚纳",6.9,20180615,40000)};

    private List<FilmCon> filmConList = new ArrayList<>();

    private FilmConAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    /** 上次点击返回键的时间 */
    private long lastBackPressed;
    /** 两次点击的间隔时间 */
    private static final int QUIT_INTERVAL = 2000;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> fragments;
    private List<String> titles;
    public static final String TAG = "TabActivity";
    public static final String []sTitle = new String[]{"电影","影院"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.film_layout);
        mViewPager = (ViewPager) findViewById(R.id.vp_content_1);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_1);
        Button QRbutton = (Button)findViewById(R.id.button_QR);
        Button locationbutton = (Button)findViewById(R.id.button_location);
        Button searchbutton = (Button)findViewById(R.id.button_sur);



//        TabLayout tabLayout_1 = (TabLayout)findViewById(R.id.tabLayout_1);
//        tabLayout_1.addTab(tabLayout_1.newTab().setText("电影"));
//        tabLayout_1.addTab(tabLayout_1.newTab().setText("影院"));
//
//        TabLayout tabLayout_2 = (TabLayout)findViewById(R.id.tabLayout_2);
//        tabLayout_2.addTab(tabLayout_2.newTab().setText("电影"));
//        tabLayout_2.addTab(tabLayout_2.newTab().setText("影院"));


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You clicked search.",Toast.LENGTH_SHORT).show();
            }
        });

        QRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You clicked QR.",Toast.LENGTH_SHORT).show();
            }
        });

        locationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You clicked location.",Toast.LENGTH_SHORT).show();
            }
        });


//        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mtoolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setTitle("");
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_dingwei);
//        }
//
//        actionMenuView = (ActionMenuView)findViewById(R.id.actionbar);


//        initFilms();
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_1);
//        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new FilmConAdapter(filmConList);
//        recyclerView.setAdapter(adapter);
//
//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_1);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshFilms();
//            }
//        });
        initView();
    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_content_1);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_1);
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[1]));
        // mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[3]));
        // mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[4]));
        // mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[5]));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
            Log.i(TAG,"onTabSelected:"+tab.getText());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        } });


        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FirstFragment.newInstance());
        fragments.add(SecondFragment.newInstance());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments, Arrays.asList(sTitle));
        mViewPager.setAdapter(adapter); mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override public void onPageSelected(int position) {
                Log.i(TAG,"select page:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            } });
    }







//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.layout.film_layout, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.film_chosen:
//                Toast.makeText(this, "You clicked film_chosen", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.the_chosen:
//                Toast.makeText(this, "You clicked the_chosen",Toast.LENGTH_SHORT).show();;
//                break;
//            case R.id.QR:
//                Toast.makeText(this, "You clicked QR",Toast.LENGTH_SHORT).show();;
//                break;
//            case R.id.sur:
//                Toast.makeText(this, "You clicked sur",Toast.LENGTH_SHORT).show();;
//                break;
//            case android.R.id.home:
//                Toast.makeText(this,"You clicked location",Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

    //显示菜单栏图标
//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        if (menu != null) {
//            if(menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
//                try {
//                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    method.setAccessible(true);
//                    method.invoke(menu, true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return super.onMenuOpened(featureId, menu);
//    }

//    private void initFilms(){
//        filmConList.clear();
//        for(int i=0;i<filmCons.length;i++){
//            filmConList.add(filmCons[i]);
//        }
//    }
//
//    private void refreshFilms() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        initFilms();
//                        adapter.notifyDataSetChanged();
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                });
//            }
//        }).start();
//    }
//
//    @Override
//    public void onBackPressed() {
//        long backPressed = System.currentTimeMillis();
//        if (backPressed - lastBackPressed > QUIT_INTERVAL) {
//            lastBackPressed = backPressed;
//            Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }

}
