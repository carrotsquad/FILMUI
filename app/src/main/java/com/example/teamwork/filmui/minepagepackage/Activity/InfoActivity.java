package com.example.teamwork.filmui.minepagepackage.Activity;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.minepagepackage.Fragment.TopFragment;

public class InfoActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        context = getApplicationContext();
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.info_top,new TopFragment());
        transaction.commit();
    }

    private void initView(){

    }
}
