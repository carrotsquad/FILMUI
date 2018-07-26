package com.example.teamwork.filmui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TheatreActivity extends AppCompatActivity {

    private List<FilmCon> filmConList = new ArrayList<>();

    private FilmConAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout2;

    private FilmCon[] filmCons = {new FilmCon("爱情公寓",R.drawable.ipartment,"陈赫 娄艺潇 邓家佳 孙艺洲 ","韦正",0,20180810,17000),
            new FilmCon("文朝荣",R.drawable.wenchaorong," 王洛勇 王亚军 刘永生","张仲",0,20180728,61),
            new FilmCon("肆式青春",R.drawable.sishi,"坂泰斗 寿美菜子","易小星 竹内良贵 李豪凌",0,20180804,4600),
            new FilmCon("狄仁杰之四大天王",R.drawable.direnjie,"赵又廷 冯绍峰 林更新","徐克",0,20180813,186700),
            new FilmCon("小偷家族",R.drawable.xiaotou,"中川雅也 安藤樱 松冈茉优","是枝裕和", 0,20180803,165000),
            new FilmCon("的士速递5",R.drawable.taxi," 弗兰克·盖思堂彼得 马利克·班泽拉","弗兰克·盖思堂彼得",0,20180803,3400)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_theatre);

        ImageButton filmbutton = (ImageButton) findViewById(R.id.id_tab_film_img);
        Button locationbutton = (Button)findViewById(R.id.button_location_2);
        Button searchbutton = (Button)findViewById(R.id.button_sur_2);
//        Button quyubutton1 = (Button)findViewById(R.id.quyu_1);
//        Button quyubutton2 = (Button)findViewById(R.id.quyu_2);
//        Button nearbutton1 = (Button)findViewById(R.id.nearest_1);
//        Button nearbutton2 = (Button)findViewById(R.id.nearest_2);
//        Button brandbutton1 = (Button)findViewById(R.id.brand_1);
//        Button brandbutton2 = (Button)findViewById(R.id.brand_2);


        initFilms();
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view_2);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this,1);
        recyclerView2.setLayoutManager(layoutManager2);
        adapter = new FilmConAdapter(filmConList);
        recyclerView2.setAdapter(adapter);

        swipeRefreshLayout2 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_2);
        swipeRefreshLayout2.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFilms();
            }
        });



        filmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheatreActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(TheatreActivity.this,"You clicked film.",Toast.LENGTH_SHORT).show();
            }
        });

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TheatreActivity.this,"You clicked search.",Toast.LENGTH_SHORT).show();
            }
        });

        locationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TheatreActivity.this,"You clicked location.",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void initFilms(){
        filmConList.clear();
        for(int i=0;i<filmCons.length;i++){
            filmConList.add(filmCons[i]);
        }
    }

    private void refreshFilms() {
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
                        swipeRefreshLayout2.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
