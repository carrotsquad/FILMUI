package com.example.teamwork.filmui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teamwork.filmui.R;

public class LuanchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luanch);

        Button button = (Button)findViewById(R.id.launch_button);

        Integer time = 3500;    //设置等待时间，单位为毫秒
        Handler handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LuanchActivity.this, MainActivity.class));
                finish();
            }
        }, time);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LuanchActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
