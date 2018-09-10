package com.example.teamwork.filmui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.adapters.HotMovieAdapter;
import com.example.teamwork.filmui.theatrepagepackage.adapters.TicketAdapter;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleTicket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.teamwork.filmui.theatrepagepackage.utils.GetUserTickets.getUserTickets;

// TODO: 2018/9/9 继续完成，以及做工具类

public class MyTickets extends AppCompatActivity {


    private RecyclerView recyclerView;

    private TicketAdapter ticketAdapter;

    private Button button;

    private TextView textView;
    private GridLayoutManager layoutManager;

    private List<String> info;

    private HotMovieAdapter hotMovieAdapter;
    private SharedPreferences sharedPreferences;

    private String url = "";
    private String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);

        initView();

        try {
            initRecycler();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 初始化页面
     */
    private void initView(){
        sharedPreferences = getSharedPreferences("users",Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name","");
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
    private void initRecycler() throws IOException {
        recyclerView = (RecyclerView) findViewById(R.id.mytickets_recycler);
        layoutManager = new GridLayoutManager(MyTickets.this,1);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<SingleTicket> singleTicketArrayList = new ArrayList<>();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    singleTicketArrayList.addAll(getUserTickets(name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        while (thread.isAlive()){

        }

        Log.e("kkkkkkkk",Integer.toString(singleTicketArrayList.size()));

        ticketAdapter = new TicketAdapter(MyTickets.this, singleTicketArrayList);

        recyclerView.setAdapter(ticketAdapter);

    }
}
