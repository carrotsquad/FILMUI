package com.example.teamwork.filmui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamwork.filmui.adapters.ComingSoonMovieAdapter;
import com.example.teamwork.filmui.adapters.FilmConAdapter;
import com.example.teamwork.filmui.adapters.HotMovieAdapter;
import com.example.teamwork.filmui.adapters.MyFragmentAdapter;
import com.example.teamwork.filmui.beans.SingleComingSoonMovie;
import com.example.teamwork.filmui.beans.SingleHotMovie;
import com.example.teamwork.filmui.beans.FilmCon;
import com.example.teamwork.filmui.beans.HotMovieBean;
import com.example.teamwork.filmui.fragments.FirstFragment;
import com.example.teamwork.filmui.fragments.SecondFragment;
import com.example.teamwork.filmui.fragments.Subone;
import com.example.teamwork.filmui.fragments.Subtwo;
import com.example.teamwork.filmui.utils.GetImageFromWeb;
import com.example.teamwork.filmui.utils.HttpGetFilmData;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.teamwork.filmui.utils.ComingSoonMovieParse.getComingSoonMovieBean;
import static com.example.teamwork.filmui.utils.HotMovieParse.getHotMoiveBean;

public class MainActivity extends  AppCompatActivity {

    /** ”正在热映适配器“ **/
    private HotMovieAdapter hotMovieAdapter;

    /** ”即将上映“适配器 **/
    private ComingSoonMovieAdapter comingSoonMovieAdapter;

    /** 两个刷新布局 **/
    private SwipeRefreshLayout swipeRefreshLayout_1;
    private SwipeRefreshLayout swipeRefreshLayout_2;

    /** 上次点击返回键的时间 */
    private long lastBackPressed;

    /** 两次点击的间隔时间 */
    private static final int QUIT_INTERVAL = 2000;

    /** 一级滑动标签页页的ViewPager和TabLayout **/
    private ViewPager mViewPager_1;
    private TabLayout mTabLayout_1;

    /** 二级滑动标签页页的ViewPager和TabLayout **/
    private ViewPager mViewPager_2;
    private TabLayout mTabLayout_2;

    /** 水平瀑布流 **/
    private LinearLayout mHorizontalLinear;


    @BindView(R.id.dropDownMenu)
    DropDownMenu theatreDropDownMenu;


    private String headers[] = {"附近区域", "离我最近", "特色品牌"};
    private List<View> popupViews = new ArrayList<>();

    private String nearmenu[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String distancemunu[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String brandmenu[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;

    private GirdDropDownAdapter NearAdapter;
    private ListDropDownAdapter DistanceAdapter;
    private ConstellationAdapter BrandAdapter;
    private ListView nearView;
    private ListView distanceView;
    private View constellationView;
    private GridView constellation;

    /** 创建随碎片列表 **/
    List<Fragment> fragments_1;

    /**第一级滑动页标题**/
    public static final String []sTitle_1 = new String[]{"电影","影院"};
    /**第一级滑动页的图标**/
    private int[] tabIcons_1 = {
            R.drawable.icfilm,
            R.drawable.icthe,
    };
    /**第二级滑动页标题**/
    public static final String []sTitle_2 = new String[]{"正在热映","即将上映"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_layout);

        /* 实例化二维码按钮 */
        Button QRbutton = (Button)findViewById(R.id.button_QR);
        /* 实例化二维码按钮 */
        Button locationbutton = (Button)findViewById(R.id.button_location);
        /* 实例化搜索按钮 */
        Button searchbutton = (Button)findViewById(R.id.button_sur);

        /* 设置搜索按钮监听器 */
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You clicked search.",Toast.LENGTH_SHORT).show();
            }
        });

        /* 设置二维码按钮监听器 */
        QRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You clicked QR.",Toast.LENGTH_SHORT).show();
            }
        });

        /* 设置定位按钮监听器 */
        locationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You clicked location.",Toast.LENGTH_SHORT).show();
            }
        });

        /* 初始化页面 */
        initView();

    }

    /**
     * 初始化页面，创建碎片
     */
    private void initView() {

        /* 初始化第一级滑动页 */
        mViewPager_1 = (ViewPager) findViewById(R.id.vp_content_1);
        mTabLayout_1 = (TabLayout) findViewById(R.id.tabLayout_1);
        mTabLayout_1.setupWithViewPager(mViewPager_1);

        /*
         * 设置第一级TabLayout设置监听器
         */
        mTabLayout_1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
        }


        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        } });

        /* 实例化 */
        fragments_1 = new ArrayList<>();

        /* 加入第一级的两个碎片 */
        fragments_1.add(FirstFragment.newInstance());
        fragments_1.add(SecondFragment.newInstance());

        /* 设置一级碎片适配器 */
        MyFragmentAdapter adapter_1 = new MyFragmentAdapter(getSupportFragmentManager(),fragments_1, Arrays.asList(sTitle_1));
        mViewPager_1.setAdapter(adapter_1);

        /* 自定义tab布局 */
        mTabLayout_1.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout_1.getTabAt(1).setCustomView(getTabView(1));

        /* 设置监听器 */
        mViewPager_1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 滑动标签页响应
             * @param position
             */
            @Override public void onPageSelected(int position) {

                switch (position){
                    /* 滑动到第一个页面"电影" */
                    case 0:
                        /* 初始化第二级滑动页 */
                        mViewPager_2 = (ViewPager) findViewById(R.id.vp_content_2);
                        mTabLayout_2 = (TabLayout) findViewById(R.id.tabLayout_2);
                        mTabLayout_2.setupWithViewPager(mViewPager_2);

                        /*
                         * 设置第二级TabLayout监听器
                         */
                        mTabLayout_2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override public void onTabSelected(TabLayout.Tab tab) {
                                switch (tab.getPosition()){
                                    /** 滑动到“正在上映”页面 **/
                                    case 0:
                                        initHotMovie();
                                        break;
                                    /** 滑动到“即将上映”页面 **/
                                    case 1:
                                        initComingSoonMovie();
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

                        /* 实例化 */
                        List<Fragment> fragments_2 = new ArrayList<>();
                        /* 加入第二级的两个碎片 */
                        fragments_2.add(Subone.newInstance());
                        fragments_2.add(Subtwo.newInstance());
                        /* 设置一级碎片适配器 */
                        MyFragmentAdapter adapter_2 = new MyFragmentAdapter(getSupportFragmentManager(),fragments_2, Arrays.asList(sTitle_2));
                        mViewPager_2.setAdapter(adapter_2);
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
                                switch (position){
                                    case 0:
                                        initHotMovie();
                                        break;
                                    case 1:
                                        initComingSoonMovie();
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
                    /* 滑动到第二个页面"影院" */
                    case 1:
                        initTheatreMenu();

                        break;
                        default:
                            break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            } });

    }


    /**
     * 刷新电影
     * @param swipeRefreshLayout
     */
    private void refreshHotMovies(final SwipeRefreshLayout swipeRefreshLayout) {
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
                        hotMovieAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }


    /**
     * 设置刷新响应
     * @param swipeRefreshLayout
     */
    private void refreshComingSoonMovies(final SwipeRefreshLayout swipeRefreshLayout) {
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
                        comingSoonMovieAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
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
        txt_title.setText(sTitle_1[position]);
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons_1[position]);
        return view;
    }


    /**
     *初始化横向的瀑布流
     */
    private void initHorizontal(List<SingleComingSoonMovie> singleComingSoonMovies){
        mHorizontalLinear = (LinearLayout)findViewById(R.id.id_horizontallinear);
        //开始添加数据
        for(int i = 0;i<singleComingSoonMovies.size();i++){
            //寻找行布局，第一个参数为行布局ID，第二个参数为这个行布局需要放到那个容器上
            View view = LayoutInflater.from(this).inflate(R.layout.horizontal_item,mHorizontalLinear,false);
            //通过View寻找ID实例化控件
            ImageView img = (ImageView) view.findViewById(R.id.movie_item_hotimageview);
            TextView title = (TextView) view.findViewById(R.id.movie_item_hotnametextview);
            TextView collectcount = (TextView) view.findViewById(R.id.movie_item_hotgradetextview);
            GetImageFromWeb.setImageView(singleComingSoonMovies.get(i).getImageId(),img,MainActivity.this);
            title.setText(singleComingSoonMovies.get(i).getTitle());
            collectcount.setText(singleComingSoonMovies.get(i).getCollectcount()+"人收藏");
            mHorizontalLinear.addView(view);
        }

    }


    /**
     * 初始化过滤条,有bug
     */
    private void initTheatreMenu(){
        ButterKnife.bind(this);
        //init city menu
        nearView = new ListView(this); //gird:准备
        NearAdapter = new GirdDropDownAdapter(this, Arrays.asList(nearmenu));
        nearView.setDividerHeight(0);
        nearView.setAdapter(NearAdapter);

        //init age menu
        distanceView = new ListView(this);
        distanceView.setDividerHeight(0);
        DistanceAdapter = new ListDropDownAdapter(this, Arrays.asList(distancemunu));
        distanceView.setAdapter(DistanceAdapter);

        //init constellation
        constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        constellation = ButterKnife.findById(constellationView, R.id.constellation);
        BrandAdapter = new ConstellationAdapter(this, Arrays.asList(brandmenu));
        constellation.setAdapter(BrandAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //其内部已经设置了记录当前tab位置的参数，该参数会随tab被点击而改变，所以这里直接设置tab值即可
                //此处若想获得constellations第一个值“不限”，可修改constellationPosition初始值为-1，且这里代码改为constellationPosition == -1)
                theatreDropDownMenu.setTabText((constellationPosition == 0) ? headers[2] : brandmenu[constellationPosition]);
                theatreDropDownMenu.closeMenu();
//                changeContentView();   //在这里可以请求获得经筛选后要显示的内容
            }
        });

        //add item click event
        nearView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NearAdapter.setCheckItem(position);
                theatreDropDownMenu.setTabText(position == 0 ? headers[0] : nearmenu[position]);
                theatreDropDownMenu.closeMenu();
            }
        });

        distanceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DistanceAdapter.setCheckItem(position);
                theatreDropDownMenu.setTabText(position == 0 ? headers[1] : distancemunu[position]);
                theatreDropDownMenu.closeMenu();
            }
        });
        

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BrandAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init popupViews
        popupViews.add(nearView);
        popupViews.add(distanceView);
        popupViews.add(constellationView);

        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        Log.e("err",Integer.toString(Arrays.asList(headers).size())+" 另一个 "+Integer.toString(popupViews.size()));

        //init dropdownview
        theatreDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        popupViews.clear();

    }


    /**
     * 初始化“正在热映”
     */
    private void initHotMovie(){
        Handler hotmoviehandler;
        hotmoviehandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder = (StringBuilder) msg.obj;
                if(stringBuilder!=null&&(getHotMoiveBean(stringBuilder)).size()!=0){
                    Log.d("现在的list大小：",String.valueOf((getHotMoiveBean(stringBuilder)).size()));
                    initHotMovieAdapter(getHotMoiveBean(stringBuilder));
                } else {

                }
            }
        };
        HttpGetFilmData.getStringBuilder(hotmoviehandler, "http://api.douban.com/v2/movie/in_theaters?");
    }


    /**
     * 初始化“正在热映”的RecycleView
     * @param hotmovielist
     */
    private void initHotMovieAdapter(List<SingleHotMovie> hotmovielist){
        RecyclerView hotmovie_recycleview = (RecyclerView) findViewById(R.id.recycler_view_1);
        GridLayoutManager layoutManager1 = new GridLayoutManager(MainActivity.this,1);
        hotmovie_recycleview.setLayoutManager(layoutManager1);
        hotMovieAdapter = new HotMovieAdapter(this, hotmovielist);
        hotmovie_recycleview.setAdapter(hotMovieAdapter);

        swipeRefreshLayout_1 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_1);
        swipeRefreshLayout_1.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout_1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshHotMovies(swipeRefreshLayout_1);
            }
        });
    }


    /**
     * 初始化“即将上映”
     */
    private void initComingSoonMovie(){
        Handler comingsoonmoviehandler;
        comingsoonmoviehandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder = (StringBuilder) msg.obj;
                if(stringBuilder!=null&&(getComingSoonMovieBean(stringBuilder)).size()!=0){
                    Log.d("现在的list大小：",String.valueOf((getComingSoonMovieBean(stringBuilder)).size()));
                    initHorizontal(getComingSoonMovieBean(stringBuilder));
                    initComingSoonMovieAdapter(getComingSoonMovieBean(stringBuilder));
                } else {

                }
            }
        };
        HttpGetFilmData.getStringBuilder(comingsoonmoviehandler, "http://api.douban.com/v2/movie/coming_soon?");

    }


    /**
     * 初始化“即将上映”的RecycleView和水平瀑布流
     * @param singleComingSoonMovieList
     */
    private void initComingSoonMovieAdapter(List<SingleComingSoonMovie> singleComingSoonMovieList){
        RecyclerView comingsoonmovie_recycleview = (RecyclerView) findViewById(R.id.recycler_view_2);
        GridLayoutManager layoutManager2 = new GridLayoutManager(MainActivity.this,1);
        comingsoonmovie_recycleview.setLayoutManager(layoutManager2);
        comingSoonMovieAdapter = new ComingSoonMovieAdapter(this,singleComingSoonMovieList);
        comingsoonmovie_recycleview.setAdapter(comingSoonMovieAdapter);

        swipeRefreshLayout_2 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_2);
        swipeRefreshLayout_2.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout_2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshComingSoonMovies(swipeRefreshLayout_2);
            }
        });
    }
}

