package com.example.teamwork.filmui.minepagepackage.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.minepagepackage.Entity.Perferences;
import com.example.teamwork.filmui.minepagepackage.Fragment.TopFragment;
import com.example.teamwork.filmui.minepagepackage.Util.LoginParse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private Myhandler myhandler = new Myhandler(this);
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initview();
    }
    public class Myhandler extends Handler {
        private final WeakReference<LoginActivity> rActivity;

        public Myhandler(LoginActivity activity){
            rActivity = new WeakReference<LoginActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg);
            if (rActivity.get()==null){
                return;
            }rActivity.get().updateUIThread(msg);
        }

    }
    private void updateUIThread(Message msg){
        Bundle bundle = new Bundle();
        bundle = msg.getData();
        String result = bundle.getString("result");
        if(result.equals("success")){
            Perferences per = new Perferences(getApplicationContext());
            per.sava(username.getText().toString());
            Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"用户名或密码错误，请重新输入！"+result,Toast.LENGTH_SHORT).show();
        }

    }

    void initview(){
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.login_top,new TopFragment());
        transaction.commit();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        username.getText();
        password.getText();
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        Log.d("log",username.getText().toString());
        Log.d("log",username.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                if (username.getText().toString().equals("")||
                password.getText().toString().equals("")){
                 Log.d("log",username.getText().toString());
                 Log.d("log",password.getText().toString());
                 Toast.makeText(this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                 }else {
                    Log.d("username:",username.getText().toString());
                    Log.d("password:",password.getText().toString());
                     sendmsg();
                }
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
        }

    }

    private void sendmsg(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = LoginParse.RegisterByPost(username.getText().toString(),password.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("result",str);
                Message msg = new Message();
                msg.setData(bundle);
                myhandler.sendMessage(msg);
                if (str.equals("success")){
                    Intent intent = new Intent();
                    intent.putExtra("username",String.valueOf(username.getText()));
                    System.out.println("username"+username.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        }).start();
    }





}
