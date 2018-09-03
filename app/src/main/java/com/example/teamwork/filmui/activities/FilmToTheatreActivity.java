package com.example.teamwork.filmui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.homepagepackage.HomePageFragment;
import com.example.teamwork.filmui.theatrepagepackage.fragments.TheatreFragment;

import static com.example.teamwork.filmui.purchasing.MatchSelectActivity.actionStart;

public class FilmToTheatreActivity extends AppCompatActivity {

    private Button button_back;
    private TextView film_name;
    private String filmname;
    private String filmgenre;
    private String filmposter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_to_theatre);
        button_back = (Button)findViewById(R.id.film_to_theatre_back);
        film_name = (TextView)findViewById(R.id.film_to_theatre_filmname);

        Intent intent = getIntent();
        filmname=intent.getStringExtra("filmname");
        filmgenre=intent.getStringExtra("filmgenre");
        filmposter=intent.getStringExtra("filmposter");
        film_name.setText(filmname);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //传送数据
        Bundle bundle = new Bundle();
        bundle.putString("name",filmname);
        bundle.putString("genre",filmgenre);
        bundle.putString("poster",filmposter);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        TheatreFragment theatreFragment = new TheatreFragment();
        theatreFragment.setArguments(bundle);
        transaction.replace(R.id.film_to_theatre_linearlayout,theatreFragment);
        transaction.commit();
    }

}
