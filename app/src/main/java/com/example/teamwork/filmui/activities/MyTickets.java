package com.example.teamwork.filmui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.adapters.HotMovieAdapter;

import java.util.List;

// TODO: 2018/9/9 继续完成，以及做工具类

public class MyTickets extends AppCompatActivity {


    private RecyclerView recyclerView;

    private Button button;

    private TextView textView;
    private GridLayoutManager layoutManager;

    private List<String> info;

    private HotMovieAdapter hotMovieAdapter;
    private SharedPreferences sharedPreferences;

    private String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);
    }


    /**
     * 初始化页面
     */
    private void initView(){
        sharedPreferences = getSharedPreferences("users",Context.MODE_PRIVATE);

        url = getIntent().getStringExtra("url");

        button = (Button)findViewById(R.id.mytickets_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecycler(){
        recyclerView = (RecyclerView) findViewById(R.id.mytickets_recycler);
        layoutManager = new GridLayoutManager(MyTickets.this,1);
        recyclerView.setLayoutManager(layoutManager);
    }
}
