package com.example.teamwork.filmui.purchasing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.activities.MainActivity;

public class BuySucess extends AppCompatActivity {
ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sucess);
        back =findViewById(R.id.sucsess_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(BuySucess.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
