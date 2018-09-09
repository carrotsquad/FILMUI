package com.example.teamwork.filmui.minepagepackage.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.minepagepackage.Fragment.TopFragment;
import com.example.teamwork.filmui.minepagepackage.Util.RegisterParse;

import java.lang.ref.WeakReference;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "RegisterActivity";
    private Button register_button;
    private EditText register_username;
    private EditText register_password;
    private Myhandler myhandler = new Myhandler(this);
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        InitView();
    }
    public class Myhandler extends Handler{
        private final WeakReference<RegisterActivity> rActivity;

        public Myhandler(RegisterActivity activity){
            rActivity = new WeakReference<RegisterActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg);
            if (rActivity.get()==null){
                return;
            }
            rActivity.get().updateUIThread(msg);
        }

    }
    private void updateUIThread(Message msg){
        Bundle bundle = new Bundle();
        bundle = msg.getData();
        String result = bundle.getString("result");
        if(result.equals("success")){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"注册成功，请登录！",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"该用户名已被注册，请重新输入！"+result,Toast.LENGTH_SHORT).show();
            register_username.setText("");
            register_password.setText("");
        }

    }
    void InitView(){
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.register_top,new TopFragment());
        transaction.commit();
        register_button = findViewById(R.id.register_button);
        register_username = findViewById(R.id.register_username);
        register_password = findViewById(R.id.register_password);
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.equals(" ")||source.toString().contentEquals("\n")) {
                    return "";
                } else {return null;}
            }
        };
        register_username.setFilters(new InputFilter[]{filter});
        register_password.setFilters(new InputFilter[]{filter});
        register_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(register_username.getText().toString().isEmpty() && register_password.getText().toString().isEmpty()){
            Toast.makeText(this,"请输入信息",Toast.LENGTH_SHORT).show();
        }else {
            sendRegister();
        }
    }
    private void sendRegister(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = RegisterParse.RegisterByPost(register_username.getText().toString(),register_password.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("result",str);
                Message msg = new Message();
                msg.setData(bundle);
                myhandler.sendMessage(msg);
            }
        }).start();
    }

}
