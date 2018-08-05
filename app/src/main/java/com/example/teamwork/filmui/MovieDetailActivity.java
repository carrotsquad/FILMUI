package com.example.teamwork.filmui;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "movie_id";

    public static final String MOVIE_RATING = "movie_rating";

    public static final String MOVIE_TITLE = "movie_title";

    public static final String MOVIE_TAGS = "movie_rating";

    private CheckBox notwatch_checkBox;
    private Button alreadywatched_button;
    private Button xuanzuogoupiao;
    private RatingBar ratingBar;
    private String movieID;
    private String movieTitle;
    private String movieTags;
    private Double movieRating;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView MovieImageView;
    private TextView MovieTitle;

    private TextView MovieTags;
    private TextView Movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail_layout);


        initVIew();
        initScore();
    }


    private void initScore(){
        ratingBar = (RatingBar)findViewById(R.id.moviedetail_ratingbar);
        ratingBar.setStepSize(0.1f);
        ratingBar.setRating((float)(movieRating*0.5));
    }

    private void initVIew(){

        Intent intent = getIntent();

        movieID = intent.getStringExtra(MOVIE_ID);
        movieRating = intent.getDoubleExtra(MOVIE_RATING,0.0);
        movieTags = intent.getStringExtra(MOVIE_TAGS);
        movieTitle = intent.getStringExtra(MOVIE_TITLE);
        toolbar = (Toolbar)findViewById(R.id.moviedetail_toolbar);
        toolbar.setTitle(" ");
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.moviedetail_collapsing_toolbar);
        MovieImageView = (ImageView) findViewById(R.id.moviedetail_poster);
        MovieTitle = (TextView) findViewById(R.id.moviedetail_title);
        MovieTags = (TextView)findViewById(R.id.moviedetail_tags);
        MovieTitle.setText(movieTitle);
        MovieTags.setText(movieTags);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        xuanzuogoupiao = (Button)findViewById(R.id.moviedetail_xuanzuogoupiao);
        xuanzuogoupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Toast.makeText(this, "You clicked share.", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
                default:
                    break;
        }
        return true;
    }
}
