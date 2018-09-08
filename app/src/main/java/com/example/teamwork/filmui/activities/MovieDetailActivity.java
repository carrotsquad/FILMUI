package com.example.teamwork.filmui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.adapters.MovieDetailAdapter;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleCast;
import com.example.teamwork.filmui.theatrepagepackage.utils.GetImageFromWeb;
import com.example.teamwork.filmui.theatrepagepackage.utils.HttpGetDetailData;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.example.teamwork.filmui.purchasing.MatchSelectActivity.actionStart;
import static com.example.teamwork.filmui.theatrepagepackage.utils.MovieDetailParse.getMovieDetail;
import static com.example.teamwork.filmui.theatrepagepackage.utils.PushMovieData.submitPostData;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String MOVIE_ID = "movie_id";

    public static final String MOVIE_RATING = "movie_rating";

    public static final String MOVIE_TITLE = "movie_title";

    public static final String MOVIE_TAGS = "movie_tags";

    // 默认展示最大行数3行
    private static final int VIDEO_CONTENT_DESC_MAX_LINE = 5;
    // 扩充
    private static final int SHOW_CONTENT_NONE_STATE = 0;
    // 收起状态
    private static final int SHRINK_UP_STATE = 1;
    // 展开状态
    private static final int SPREAD_STATE = 2;
    //默认收起状态
    private static int mState = SHRINK_UP_STATE;

    /**
     * 想看的电影,参数username，want（电影ID）返回参数success或者false
     */
    public static final String wannapush_uri="http://47.106.95.140:8080/tpp/want";

    /**
     * 看过的电影,参数username，watched（电影ID）返回参数success或者false
     */
    public static final String alreadypush_uri="http://47.106.95.140:8080/tpp/watched";

    /**
     * 删除想看的电影,参数username,want 成功返回success失败返回false want即想看电影id
     */
    public static final String wannadelete_uri="http://47.106.95.140:8080/tpp/deletewant";

    /**
     * 删除看过电影,参数username,watched成功返回success失败返回false want即想看电影id
     */
    public static final String alreadydelete_uri="http://47.106.95.140:8080/tpp/deletewatched";

    // 展示更多
    private RelativeLayout mShowMore;
    // 展开
    private ImageView mImageSpread;
    // 收起
    private ImageView mImageShrinkUp;

    private CheckBox notwatch_checkBox;
    private CheckBox alreadywatched_checkbox;
    private Button xuanzuogoupiao;
    private RatingBar ratingBar;


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MovieDetailAdapter movieDetailAdapter;

    private Double movieRating;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView MovieImageView;
    private TextView MovieTitle;

    private TextView MovieScore;
    private TextView MovieTags;
    private TextView MovieCountries;
    private TextView MovieAka;
    private TextView Movie_Wish_Count;
    private TextView Movie_Collect_Count;
    private TextView MovieSummary;

    private String movieID;
    private String movieTitle;
    private String movieTags;
    private String moviePoster;

    /* 随机颜色 */
    private String[] bg = new String[]{
            "#d1b163",
            "#7095c2",
            "#de6f6c",
            "#7a9c84"
    };

    private int[] bgcolor = {
            R.color.special_yellow,
            R.color.special_blue,
            R.color.special_red,
            R.color.special_green
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetail_layout);


        /* 初始化界面 */
        initVIew();
        /* 初始化分数 */
        initScore();
        /* 初始化细节 */
        initCasts();
        /* 初始化简介 */
        initIntro();
    }


    /**
     * 初始化界面
     */
    private void initVIew(){

        Intent intent = getIntent();
        int min=0;
        int max=4;
        Random random = new Random();
        int num = random.nextInt(max)%(max-min+1) + min;

        Log.e("6666666666666",Integer.toString(num)+" " +bg[num]);


        movieRating = intent.getDoubleExtra(MOVIE_RATING, 0.0);
        movieID = intent.getStringExtra(MOVIE_ID);
        movieTags = intent.getStringExtra(MOVIE_TAGS);
        movieTitle = intent.getStringExtra(MOVIE_TITLE);

        toolbar = (Toolbar)findViewById(R.id.moviedetail_toolbar);
        toolbar.setTitle(" ");
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.moviedetail_collapsing_toolbar);
        collapsingToolbarLayout.setBackgroundColor(android.graphics.Color.parseColor(bg[num]));
        setWindowStatusBarColor(MovieDetailActivity.this, bgcolor[num]);

        MovieTitle = (TextView) findViewById(R.id.moviedetail_title);
        MovieTags = (TextView)findViewById(R.id.moviedetail_tags);
        MovieScore = (TextView)findViewById(R.id.moviedetail_score);


        MovieTitle.setText(movieTitle);
        MovieTags.setText(movieTags);
        MovieScore.setText("评分："+new DecimalFormat("0.0").format(movieRating));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        xuanzuogoupiao = (Button)findViewById(R.id.moviedetail_xuanzuogoupiao);
        alreadywatched_checkbox = (CheckBox) findViewById(R.id.moviedetail_alreadywatched_checkbox);
        notwatch_checkBox = (CheckBox)findViewById(R.id.moviedetail_wannawatch_checkbox);
        xuanzuogoupiao.setOnClickListener(this);
        notwatch_checkBox.setOnCheckedChangeListener(this);
        alreadywatched_checkbox.setOnCheckedChangeListener(this);

    }


    /**
     * 初始化简介的伸缩特性的控件
     */
    private void initIntro(){
        mShowMore = (RelativeLayout) findViewById(R.id.show_more);
        mImageSpread = (ImageView) findViewById(R.id.spread);
        mImageShrinkUp = (ImageView) findViewById(R.id.shrink_up);
        mShowMore.setOnClickListener(this);
    }


    /**
     * 重写按钮的onClick方法
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /* 选中“更多” */
            case R.id.show_more: {
                if (mState == SPREAD_STATE) {
                    MovieSummary.setMaxLines(VIDEO_CONTENT_DESC_MAX_LINE);
                    MovieSummary.requestLayout();
                    mImageShrinkUp.setVisibility(View.GONE);
                    mImageSpread.setVisibility(View.VISIBLE);
                    mState = SHRINK_UP_STATE;
                } else if (mState == SHRINK_UP_STATE) {
                    MovieSummary.setMaxLines(Integer.MAX_VALUE);
                    MovieSummary.requestLayout();
                    mImageShrinkUp.setVisibility(View.VISIBLE);
                    mImageSpread.setVisibility(View.GONE);
                    mState = SPREAD_STATE;
                }
                break;
            }
            case R.id.moviedetail_xuanzuogoupiao:{
                Intent intent1=new Intent(MovieDetailActivity.this, FilmToTheatreActivity.class);
                intent1.putExtra("filmname", movieTitle);
                intent1.putExtra("filmgenre", movieTags);
                intent1.putExtra("filmposter", moviePoster);
                Log.e("film", moviePoster+"       "+movieTags+"        "+movieTitle);
                startActivity(intent1);
                break;
            }
            default: {
                break;
            }
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.moviedetail_wannawatch_checkbox:{
                if (isChecked){
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                submitPostData(wannapush_uri,"admin", movieID);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    alreadywatched_checkbox.setVisibility(View.INVISIBLE);
                }
                else {
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                submitPostData(wannadelete_uri,"admin", movieID);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    alreadywatched_checkbox.setVisibility(View.VISIBLE);
                }
                break;
            }
            case R.id.moviedetail_alreadywatched_checkbox:{
                if(isChecked){
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                submitPostData(alreadypush_uri,"admin", movieID);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    notwatch_checkBox.setVisibility(View.INVISIBLE);

                }else {
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                submitPostData(alreadydelete_uri,"admin", movieID);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    notwatch_checkBox.setVisibility(View.VISIBLE);
                }
                break;
            }
            default:{
                break;
            }
        }
    }


    /**
     * 初始化细节
     */
    private void initCasts(){
        Handler castshandler;
        castshandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder =(StringBuilder) msg.obj;
                if (stringBuilder!=null && (getMovieDetail(stringBuilder)).size()!=0){
                    Log.d("现在的list大小：",String.valueOf((getMovieDetail(stringBuilder)).size()));
                    initCastAdapter(getMovieDetail(stringBuilder));
                } else {

                }
            }
        };
        HttpGetDetailData.getPath(castshandler, "http://api.douban.com/v2/movie/subject/"+movieID);
    }


    /**
     * 初始化细节控件
     * @param singleCastList
     */
    private void initCastAdapter(List<SingleCast> singleCastList){
        SingleCast singleCast = singleCastList.get(0);

        recyclerView = (RecyclerView)findViewById(R.id.moviedetail_castsrecycleview);
        Movie_Collect_Count = (TextView)findViewById(R.id.moviedetail_yikanrenshu);
        Movie_Wish_Count = (TextView)findViewById(R.id.moviedetail_xiangkanrenshu);
        MovieAka = (TextView)findViewById(R.id.moviedetail_original_title);
        MovieCountries = (TextView)findViewById(R.id.moviedetail_period);
        MovieSummary = (TextView)findViewById(R.id.moviedetail_intro);
        MovieImageView = (ImageView) findViewById(R.id.moviedetail_poster);

        moviePoster=singleCast.getPoster();

        GetImageFromWeb.setImageView(moviePoster, MovieImageView, MovieDetailActivity.this);
        Movie_Wish_Count.setText(Integer.toString(singleCast.getWish_count()));
        Movie_Collect_Count.setText(Integer.toString(singleCast.getCollect_count()));
        MovieCountries.setText(singleCast.getCountries());
        MovieAka.setText(singleCast.getAka());
        MovieSummary.setText("  "+singleCast.getSummary());


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        movieDetailAdapter = new MovieDetailAdapter(this,singleCastList);
        recyclerView.setAdapter(movieDetailAdapter);
    }


    /**
     * 初始化分数
     */
    private void initScore(){
        ratingBar = (RatingBar)findViewById(R.id.moviedetail_ratingbar);
        ratingBar.setStepSize(0.1f);
        ratingBar.setRating((float)(movieRating*0.5));
    }


    /**
     * 设置menu布局
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar_menu, menu);
        return true;
    }


    /**
     * 设置响应
     * @param item
     * @return
     */
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


    /**
     * 设置状态栏颜色
     * @param activity
     * @param colorResId
     */
     private void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
