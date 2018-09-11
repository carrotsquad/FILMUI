package com.example.teamwork.filmui.minepagepackage.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.minepagepackage.Fragment.TopFragment;

public class InfoActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;
    private Context context;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initView();
        context = getApplicationContext();
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.info_top,new TopFragment());
        transaction.commit();
    }

    private void initView(){
        SharedPreferences sharedPreferences = getSharedPreferences("users",Context.MODE_PRIVATE);

        textView = (TextView) findViewById(R.id.info_user);
        textView.setText(sharedPreferences.getString("name","username"));

    }
}
