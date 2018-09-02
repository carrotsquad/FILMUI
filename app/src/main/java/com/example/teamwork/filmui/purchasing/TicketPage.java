package com.example.teamwork.filmui.purchasing;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.teamwork.filmui.R;

public class TicketPage extends AppCompatActivity {
ImageView mImageView;
Bitmap mBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_page);
        mImageView=findViewById(R.id.page_core);
        /*

        这里添加读取服务器数据代码


         */
        //data为 电影名+场次+属性+位置
       mBitmap= CoreProducer.createQRCodeBitmap("data",800,800);
       mImageView.setImageBitmap(mBitmap);
    }

}
