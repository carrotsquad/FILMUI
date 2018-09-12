//package com.example.teamwork.filmui.activities;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.teamwork.filmui.R;
//import com.example.teamwork.filmui.theatrepagepackage.adapters.HotMovieAdapter;
//import com.example.teamwork.filmui.theatrepagepackage.beans.SingleHotMovie;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.teamwork.filmui.theatrepagepackage.utils.GetUserInfo.getUserInfo;
//import static com.example.teamwork.filmui.theatrepagepackage.utils.GetUserMovie.initUserMovie;
//
//
//// TODO: 2018/9/9
//// FIXME: 2018/9/9 豆瓣api数据获取问题，基本看不到电影列表，能力有限，也不知道怎么修复 by zhangqianyuan
//
//public class UserMovieActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//
//    private Button button;
//
//    private TextView textView;
//    private GridLayoutManager layoutManager;
//
//    private List<String> info;
//
//    private HotMovieAdapter hotMovieAdapter;
//    private SharedPreferences sharedPreferences;
//
//    String op="";
//    String url = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_usermovie);
//
//        sharedPreferences = getSharedPreferences("users",Context.MODE_PRIVATE);
//
//        url = getIntent().getStringExtra("url");
//
//
//
//
//        textView = (TextView)findViewById(R.id.usermovie_class);
//        op = getIntent().getStringExtra("class");
//        if(op.equals("wanna")){
//            textView.setText("我想看的电影");
//        }
//
//        button = (Button)findViewById(R.id.usermovie_back);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//
//        try {
//            initList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 初始化列表信息
//     * @throws IOException
//     */
//    private void initList() throws IOException {
//        String name = sharedPreferences.getString("name","");
//
//        List<String> stringList = new ArrayList<>();
//
//        Thread thread=new Thread(){
//            @Override
//            public void run() {
//                try {
//                    stringList.addAll(getUserInfo(url,name));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        thread.start();
//        while (thread.isAlive()){
//
//        }
//
////        List<String> strings = new ArrayList<>();
////        strings.add("26336252");
////        strings.add("26810318");
//
//        initRecycler(stringList);
//    }
//
//
//    /**
//     * 初始化RecyclerView
//     */
//    private void initRecycler(List<String> stringList) throws IOException {
//
//        info = new ArrayList<>();
//        recyclerView = (RecyclerView) findViewById(R.id.usermovie_recycler);
//        layoutManager = new GridLayoutManager(UserMovieActivity.this,1);
//        recyclerView.setLayoutManager(layoutManager);
//
//        List<SingleHotMovie> singleHotMovieList = initUserMovie(stringList);
//        Log.e("用户电影列表大小",Integer.toString(singleHotMovieList.size()));
//        hotMovieAdapter = new HotMovieAdapter(UserMovieActivity.this , singleHotMovieList, info);
//
//        recyclerView.setAdapter(hotMovieAdapter);
//    }
//}
