package com.example.teamwork.filmui.minepagepackage.Activity;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.minepagepackage.Fragment.InfoFragment;
import com.example.teamwork.filmui.minepagepackage.Fragment.SignoutFragment;
import com.example.teamwork.filmui.minepagepackage.Fragment.TopFragment;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;
    private LinearLayout sign_out;
    private SharedPreferences preferences;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        initView();
    }
    private void initView(){
        sign_out=(LinearLayout)findViewById(R.id.sign_out);
        sign_out.setOnClickListener(this);
        context = getApplicationContext();
        preferences = context.getSharedPreferences("users", Context.MODE_PRIVATE);
        String name = preferences.getString("name","");
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.top,new TopFragment());
        if (name.isEmpty()){
            android.support.v4.app.Fragment fragment = manager.findFragmentById(R.id.sign_out);
            android.support.v4.app.Fragment fragment2 = manager.findFragmentById(R.id.user_info);
            if (fragment==null&&fragment2==null){

            }else {
                transaction.remove(fragment2);
                transaction.remove(fragment);
            }
        }else{
            transaction.replace(R.id.sign_out,new SignoutFragment());
            transaction.replace(R.id.user_info,new InfoFragment());
        }


        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_out:
                preferences = getSharedPreferences("users", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("name");
                editor.commit();
                finish();
                break;
        }
    }
}
