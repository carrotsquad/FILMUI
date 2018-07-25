package com.example.teamwork.filmui;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mtoolbar;

    private ActionMenuView actionMenuView;

    private FilmCon[] filmCons = {new FilmCon("我不是药神",R.drawable.yaoshen,"徐峥 王传君 周一围 谭卓","文牧野",8.9,20180705,608687),
    new FilmCon("超人总动员2",R.drawable.superman,"格雷格·T·尼尔森","布拉德·伯德",8.1,20180622,80000),
    new FilmCon("肆式青春",R.drawable.sishi,"坂泰斗 寿美菜子","易小星 竹内良贵 李豪凌",0,20180804,4600),
    new FilmCon("邪不压正",R.drawable.fare,"彭于晏 姜文 廖凡","姜文",7.1,20180713,186700)};

    private List<FilmCon> filmConList = new ArrayList<>();

    private FilmConAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_layout);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dingwei);
        }

        actionMenuView = (ActionMenuView)findViewById(R.id.actionbar);
        initFilms();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FilmConAdapter(filmConList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.film_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.film_chosen:
                Toast.makeText(this, "You clicked film_chosen", Toast.LENGTH_SHORT).show();
                break;
            case R.id.the_chosen:
                Toast.makeText(this, "You clicked the_chosen",Toast.LENGTH_SHORT).show();;
                break;
            case R.id.QR:
                Toast.makeText(this, "You clicked QR",Toast.LENGTH_SHORT).show();;
                break;
            case R.id.sur:
                Toast.makeText(this, "You clicked sur",Toast.LENGTH_SHORT).show();;
                break;
            case android.R.id.home:
                Toast.makeText(this,"You clicked location",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    //显示菜单栏图标
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if(menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void initFilms(){
        filmConList.clear();
        for(int i=0;i<filmCons.length;i++){
            filmConList.add(filmCons[i]);
        }
    }

    private void refreshFruits() {
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
                        initFilms();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

}
