package com.example.teamwork.filmui.purchasing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.LogUtil;
import com.avos.avoscloud.SaveCallback;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleTicket;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.teamwork.filmui.theatrepagepackage.utils.GetUserTickets.getUserTickets;
import static com.example.teamwork.filmui.theatrepagepackage.utils.PushUserTickets.pushUserTickets;

public class OutTicketActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    //总价格
    TextView allPrice;

    //开长时间-散场时间
    TextView time;

    //电影名
    TextView movieTitle;

    //影院名
    TextView cinemaTitle;


    //几号厅  座位排列数
    TextView match_place;

    //底部确认购买
    TextView sure_buy;

    SoldAndCheck mSoldAndCheck;

    //电影类
    AVObject mMatch;

    //电影票类
    TicketInformation mTicketInformation;

    String date;



    String cinema_title;
    String[] seats = new String[3];
    Intent mIntent;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_ticket);
        initView();
    }

    public void initView() {
        back=findViewById(R.id.back_out_ticket);
        sure_buy = findViewById(R.id.sure_buy);
        time = findViewById(R.id.out_ticket_time);
        match_place = findViewById(R.id.out_ticket_seat);
        allPrice = findViewById(R.id.all_price);
        movieTitle =findViewById(R.id.out_ticket_title);
        cinemaTitle=findViewById(R.id.out_ticket_cinema);
        mIntent = getIntent();
        date = mIntent.getStringExtra("date");
        mSoldAndCheck = (SoldAndCheck) mIntent.getSerializableExtra("mSoldAndCheck");
        mMatch = mIntent.getParcelableExtra("match");
        movieTitle.setText(mMatch.getString("movieTitle")+"   "+getIntent().getStringExtra("count")+"张");
        cinemaTitle.setText(mMatch.getString("cinemaTitle"));
        seats[0] = mIntent.getStringExtra("first_seat");
        Log.d("seatlocation", "0" + seats[0]);
        seats[1] = mIntent.getStringExtra("second_seat");
        Log.d("seatlocation", "0" + seats[1]);
        seats[2] = mIntent.getStringExtra("third_seat");
        Log.d("seatlocation", "0" + seats[2]);
        cinema_title = mIntent.getStringExtra("cinema_title");
        match_place.setText(mMatch.getString("place") + " " + mIntent.getStringExtra("position"));
        time.setText(mMatch.getString("start_time") + "-" + mMatch.getString("end_time"));
        allPrice.setText(String.valueOf(mIntent.getFloatExtra("all_price", 1) - 0.1) + "元");
        sure_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initClondData();

                sharedPreferences = getSharedPreferences("users",Context.MODE_PRIVATE);

                String name = sharedPreferences.getString("name","");

                String filmtitle = mMatch.getString("movieTitle");
                String cinematitle = mIntent.getStringExtra("cinema_title");

                String time = date;

                String place = mMatch.getString("place");

                String shuxing = mIntent.getStringExtra("shuxing");

                String imageUrl = mIntent.getStringExtra("imageUrl");

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

                int len = singleTicketArrayList.size();

                final String[] res = {""};
                Thread thread_1 = new Thread(){
                    @Override
                    public void run() {
                        try {
                            res[0] =pushUserTickets(filmtitle,cinema_title,date,shuxing,place,seats[0],imageUrl,name,Integer.toString(len+1));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread_1.start();
                while (thread.isAlive()){

                }

                if(res[0] =="false"){
                    Toast.makeText(OutTicketActivity.this,"购票失败",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(OutTicketActivity.this,"购票成功",Toast.LENGTH_SHORT).show();
                }

                if(seats[1]!=""){
                    Thread thread_2 = new Thread(){
                        @Override
                        public void run() {
                            try {
                                res[0] =pushUserTickets(filmtitle,cinema_title,date,shuxing,place,seats[1],imageUrl,name,Integer.toString(len+2));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    if(res[0]=="false"){
                        Toast.makeText(OutTicketActivity.this,"购票失败",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(OutTicketActivity.this,"购票成功",Toast.LENGTH_SHORT).show();
                    }

                }

                if(seats[2]!=""){

                    Thread thread_2 = new Thread(){
                        @Override
                        public void run() {
                            try {
                                res[0] =pushUserTickets(filmtitle,cinema_title,date,shuxing,place,seats[1],imageUrl,name,Integer.toString(len+3));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    if(res[0]=="false"){
                        Toast.makeText(OutTicketActivity.this,"购票失败",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(OutTicketActivity.this,"购票成功",Toast.LENGTH_SHORT).show();
                    }

                }



                Intent intent = new Intent(OutTicketActivity.this, BuySucess.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //给每个座位设置属性
    public void initClondData() {
        // mSoldAndCheck.isSold = mSoldAndCheck.isCheck;
        StringBuilder builder = new StringBuilder();
        int xys[][] = new int[3][2];
        int c = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                if (mSoldAndCheck.isCheck[i][j]) {
                    xys[c][0] = i;
                    xys[c][1] = j;
                    c++;
                }
            }
        }
        for (int i = 0; i < c; i++) {
            mSoldAndCheck.isSold[xys[i][0]][xys[i][1]] = true;
            builder.append(String.valueOf(xys[i][0])+"排"+String.valueOf(xys[i][1])+"座"+" ");
        }
        //传出 这个东西 给李大桥的服务器
        mTicketInformation = new TicketInformation(mMatch.getString("movieTitle"),mMatch.getString("cinemaTitle"),
                mMatch.getString("date")+mMatch.getString("start_time")+"~"+mMatch.getString("end_time"),
                mMatch.getString("shuxing"),mMatch.getString("place"),builder.toString(),mMatch.getString("poster"));
        mSoldAndCheck.cleanCheck();
        mMatch.put("mSoldAndCheck", new Gson().toJson(mSoldAndCheck));
        mMatch.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Log.d("avplayer2", mMatch.getObjectId());
                }
            }
        });
    }


}