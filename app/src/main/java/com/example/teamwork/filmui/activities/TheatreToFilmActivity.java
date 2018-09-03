package com.example.teamwork.filmui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.fragments.InTheatreFragment;
import com.example.teamwork.filmui.theatrepagepackage.fragments.MovieFragment;

public class TheatreToFilmActivity extends AppCompatActivity {

    private Button button_back;
    private TextView theatre_name;
    private String theatrename;
    private String theatrelocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre_to_film);

        button_back = (Button)findViewById(R.id.theatre_to_film_back);
        theatre_name = (TextView)findViewById(R.id.theatre_to_film_theatrename);

        Intent intent = getIntent();
        theatrename=intent.getStringExtra("cinemaTitle");
        theatrelocation=intent.getStringExtra("cinemaLocation");
        theatre_name.setText(theatrename);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //传送数据
        Bundle bundle = new Bundle();
        bundle.putString("theatrename",theatrename);
        bundle.putString("theatrelocation",theatrelocation);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        MovieFragment movieFragment = new MovieFragment();
        movieFragment.setArguments(bundle);
        transaction.replace(R.id.theatre_to_film_linearlayout,movieFragment);
        transaction.commit();

    }

}
