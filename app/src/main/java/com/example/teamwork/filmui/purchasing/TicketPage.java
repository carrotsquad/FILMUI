package com.example.teamwork.filmui.purchasing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.homepagepackage.utils.GetImageFromInternet;

// TODO: 2018/9/10 继续完善

public class TicketPage extends AppCompatActivity {

    private ImageView mImageView;
    private TextView filmname;
    private TextView timeandshuxing;
    private TextView place;
    private TextView seat;
    private ImageView filmimage;
    private Bitmap mBitmap;
    private ImageView back;

    private String filmTitle;
    private String dateandshuxing;
    private String imageUri;
    private String seatlocation;
    private String cinemaplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_page);

        initWidgets();

        setWidgets();
        /*

        这里添加读取服务器数据代码


         */
        //data为 电影名+场次+属性+位置

    }


    /**
     * 初始化控件
     */
    private void initWidgets(){
        mImageView=(ImageView) findViewById(R.id.page_core);
        filmname = (TextView) findViewById(R.id.page_movie_title);
        timeandshuxing = (TextView) findViewById(R.id.page_time_shuxing);
        place = (TextView) findViewById(R.id.page_cinema_place);
        seat = (TextView) findViewById(R.id.page_seat_location);
        filmimage = (ImageView) findViewById(R.id.page_movie_image);
        back = (ImageView) findViewById(R.id.ticket_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 设置控件
     */
    private void setWidgets() {
        Intent intent = getIntent();
        filmTitle = intent.getStringExtra("filmTitle");
        dateandshuxing = intent.getStringExtra("date");
        imageUri = intent.getStringExtra("imageUri");
        seatlocation = intent.getStringExtra("seatlocation");
        cinemaplace = intent.getStringExtra("cinemaplace");

        mBitmap= CoreProducer.createQRCodeBitmap(filmTitle+"\n\n"+dateandshuxing+"\n\n"+cinemaplace+"\n\n"+seatlocation,800,800);
        filmname.setText(filmTitle);
        timeandshuxing.setText(dateandshuxing);
        place.setText(cinemaplace);
        seat.setText(seatlocation);
        GetImageFromInternet.setImageView(imageUri, filmimage, TicketPage.this);
        mImageView.setImageBitmap(mBitmap);
    }
}
