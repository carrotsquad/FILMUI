package com.example.teamwork.filmui;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
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
    private FilmCon[] futurefilmCons = {new FilmCon("爱情公寓",R.drawable.ipartment,"陈赫 娄艺潇 邓家佳 孙艺洲 ","韦正",0,20180810,17000),
    new FilmCon("文朝荣",R.drawable.wenchaorong," 王洛勇 王亚军 刘永生","张仲",0,20180728,61),
    new FilmCon("肆式青春",R.drawable.sishi,"坂泰斗 寿美菜子","易小星 竹内良贵 李豪凌",0,20180804,4600),
    new FilmCon("狄仁杰之四大天王",R.drawable.direnjie,"赵又廷 冯绍峰 林更新","徐克",0,20180813,186700),
    new FilmCon("小偷家族",R.drawable.xiaotou,"中川雅也 安藤樱 松冈茉优","是枝裕和", 0,20180803,165000),
    new FilmCon("的士速递5",R.drawable.taxi," 弗兰克·盖思堂彼得 马利克·班泽拉","弗兰克·盖思堂彼得",0,20180803,3400)};

    private List<FilmCon> filmConList_1 = new ArrayList<>();

    private FilmConAdapter filmadapter_1;

    private List<FilmCon> filmConList_2 = new ArrayList<>();

    private FilmConAdapter filmadapter_2;

    private SwipeRefreshLayout swipeRefreshLayout_1;

    private SwipeRefreshLayout swipeRefreshLayout_2;
    /** 上次点击返回键的时间 */
    private long lastBackPressed;
    /** 两次点击的间隔时间 */
    private static final int QUIT_INTERVAL = 2000;

    private ViewPager mViewPager_1;
    private TabLayout mTabLayout_1;

    private ViewPager mViewPager_2;
    private TabLayout mTabLayout_2;

    private LinearLayout mHorizontalLinear;


    public static final String TAG = "TabActivity";
    public static final String []sTitle_1 = new String[]{"电影","影院"};
    private int[] tabIcons_1 = {
            R.drawable.icfilm,
            R.drawable.icthe,
    };
    public static final String []sTitle_2 = new String[]{"正在热映","即将上映"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_layout);
        mViewPager_1 = (ViewPager) findViewById(R.id.vp_content_1);
        mTabLayout_1 = (TabLayout) findViewById(R.id.tabLayout_1);
        Button QRbutton = (Button)findViewById(R.id.button_QR);
        Button locationbutton = (Button)findViewById(R.id.button_location);
        Button searchbutton = (Button)findViewById(R.id.button_sur);

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

        initView();

    }

    private void initView() {

        mViewPager_1 = (ViewPager) findViewById(R.id.vp_content_1);
        mTabLayout_1 = (TabLayout) findViewById(R.id.tabLayout_1);
        mTabLayout_1.setupWithViewPager(mViewPager_1);

        mTabLayout_1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
            Log.i(TAG,"onTabSelected:"+tab.getText());
        }


        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        } });


        List<Fragment> fragments_1 = new ArrayList<>();
        fragments_1.add(FirstFragment.newInstance());
        fragments_1.add(SecondFragment.newInstance());
        MyFragmentAdapter adapter_1 = new MyFragmentAdapter(getSupportFragmentManager(),fragments_1, Arrays.asList(sTitle_1));
        mViewPager_1.setAdapter(adapter_1);
        mTabLayout_1.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout_1.getTabAt(1).setCustomView(getTabView(1));
        mViewPager_1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        mViewPager_2 = (ViewPager) findViewById(R.id.vp_content_2);
                        mTabLayout_2 = (TabLayout) findViewById(R.id.tabLayout_2);
                        mTabLayout_2.setupWithViewPager(mViewPager_2);
                        mTabLayout_2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override public void onTabSelected(TabLayout.Tab tab) {
                                switch (tab.getPosition()){
                                    case 0:
                                        initFilms(filmCons,filmConList_1);
                                        RecyclerView recyclerView_1 = (RecyclerView) findViewById(R.id.recycler_view_1);
                                        GridLayoutManager layoutManager1 = new GridLayoutManager(MainActivity.this,1);
                                        recyclerView_1.setLayoutManager(layoutManager1);
                                        filmadapter_1 = new FilmConAdapter(filmConList_1);
                                        recyclerView_1.setAdapter(filmadapter_1);

                                        swipeRefreshLayout_1 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_1);
                                        swipeRefreshLayout_1.setColorSchemeResources(R.color.colorPrimary);
                                        swipeRefreshLayout_1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                            @Override
                                            public void onRefresh() {
                                                refreshFilms(filmCons,filmConList_1,filmadapter_1,swipeRefreshLayout_1);
                                            }
                                        });

                                        break;
                                    case 1:
                                        initHorizontal();
                                        initFilms(futurefilmCons,filmConList_2);
                                        RecyclerView recyclerView_2 = (RecyclerView) findViewById(R.id.recycler_view_2);
                                        GridLayoutManager layoutManager2 = new GridLayoutManager(MainActivity.this,1);
                                        recyclerView_2.setLayoutManager(layoutManager2);
                                        filmadapter_2 = new FilmConAdapter(filmConList_2);
                                        recyclerView_2.setAdapter(filmadapter_2);

                                        swipeRefreshLayout_2 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_2);
                                        swipeRefreshLayout_2.setColorSchemeResources(R.color.colorPrimary);
                                        swipeRefreshLayout_2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                            @Override
                                            public void onRefresh() {
                                                refreshFilms(futurefilmCons,filmConList_2,filmadapter_2,swipeRefreshLayout_2);
                                            }
                                        });
                                        break;
                                    default:
                                        break;
                                }
                            }


                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            } });
                        List<Fragment> fragments_2 = new ArrayList<>();
                        fragments_2.add(Subone.newInstance());
                        fragments_2.add(Subtwo.newInstance());
                        MyFragmentAdapter adapter_2 = new MyFragmentAdapter(getSupportFragmentManager(),fragments_2, Arrays.asList(sTitle_2));
                        mViewPager_2.setAdapter(adapter_2);
                        mTabLayout_2.getTabAt(0).setText(sTitle_2[0]);
                        mTabLayout_2.getTabAt(1).setText(sTitle_2[1]);
                        mViewPager_2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                switch (position){
                                    case 0:
                                        initFilms(filmCons,filmConList_1);
                                        RecyclerView recyclerView_1 = (RecyclerView) findViewById(R.id.recycler_view_1);
                                        GridLayoutManager layoutManager1 = new GridLayoutManager(MainActivity.this,1);
                                        recyclerView_1.setLayoutManager(layoutManager1);
                                        filmadapter_1 = new FilmConAdapter(filmConList_1);
                                        recyclerView_1.setAdapter(filmadapter_1);

                                        swipeRefreshLayout_1 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_1);
                                        swipeRefreshLayout_1.setColorSchemeResources(R.color.colorPrimary);
                                        swipeRefreshLayout_1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                            @Override
                                            public void onRefresh() {
                                                refreshFilms(filmCons,filmConList_1,filmadapter_1,swipeRefreshLayout_1);
                                            }
                                        });

                                        break;
                                    case 1:
                                        initHorizontal();
                                        initFilms(futurefilmCons,filmConList_2);
                                        RecyclerView recyclerView_2 = (RecyclerView) findViewById(R.id.recycler_view_2);
                                        GridLayoutManager layoutManager2 = new GridLayoutManager(MainActivity.this,1);
                                        recyclerView_2.setLayoutManager(layoutManager2);
                                        filmadapter_2 = new FilmConAdapter(filmConList_2);
                                        recyclerView_2.setAdapter(filmadapter_2);

                                        swipeRefreshLayout_2 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_2);
                                        swipeRefreshLayout_2.setColorSchemeResources(R.color.colorPrimary);
                                        swipeRefreshLayout_2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                            @Override
                                            public void onRefresh() {
                                                refreshFilms(futurefilmCons,filmConList_2,filmadapter_2,swipeRefreshLayout_2);
                                            }
                                        });
                                        break;
                                        default:
                                            break;
                                }
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        break;
                    case 1:

                        break;
                        default:
                            break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            } });

    }

    private void initFilms(FilmCon[] filmlist,List<FilmCon> filmConList){
        filmConList.clear();
        for(int i=0;i<filmlist.length;i++){
            filmConList.add(filmlist[i]);
        }
    }

    private void refreshFilms(final FilmCon[] filmlist, final List<FilmCon> filmConList, final FilmConAdapter filmadapter, final SwipeRefreshLayout swipeRefreshLayout) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFilms(filmlist,filmConList);
                        filmadapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

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

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab, null);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(sTitle_1[position]);
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons_1[position]);
        return view;
    }

    private void initHorizontal(){
        mHorizontalLinear = (LinearLayout)findViewById(R.id.id_horizontallinear);
        //开始添加数据
        for(int i = 0;i<futurefilmCons.length;i++){
            //寻找行布局，第一个参数为行布局ID，第二个参数为这个行布局需要放到那个容器上
            View view = LayoutInflater.from(this).inflate(R.layout.horizontal_item,mHorizontalLinear,false);
            //通过View寻找ID实例化控件
            ImageView img = (ImageView) view.findViewById(R.id.id_horizontal_imageView);
            TextView name = (TextView) view.findViewById(R.id.id_horizontal_futurefilmname);
            TextView date = (TextView) view.findViewById(R.id.id_horizontal_futurefilmdate);
            img.setImageResource(futurefilmCons[i].getImageId());
            name.setText(futurefilmCons[i].getName());
            date.setText(Long.toString(futurefilmCons[i].getDate()).charAt(5)+"月"+Long.toString(futurefilmCons[i].getDate()).charAt(6)+Long.toString(futurefilmCons[i].getDate()).charAt(7)+"日");
            mHorizontalLinear.addView(view);
        }

    }

}
