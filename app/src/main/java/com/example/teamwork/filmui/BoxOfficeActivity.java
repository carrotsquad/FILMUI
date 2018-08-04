package com.example.teamwork.filmui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamwork.filmui.adapters.BoxOfficeAdapter;
import com.example.teamwork.filmui.beans.SingleBoxOffice;
import com.example.teamwork.filmui.utils.GetBoxOfficeData;
import com.example.teamwork.filmui.utils.HttpGetFilmData;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.teamwork.filmui.utils.BoxOfficeParse.getSingleBoxOfficeList;
import static com.example.teamwork.filmui.utils.HotMovieParse.getHotMoiveBean;

/**
 * 票房页面
 */

public class BoxOfficeActivity extends AppCompatActivity {

    private Button back;
    private TextView date;
    private TextView sumBoxOffice;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BoxOfficeAdapter boxOfficeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);

        /* 初始化页面 */
        initView();
    }


    /**
     * 初始化页面
     */
    private void initView(){
        Handler boxofficehandler;
        boxofficehandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder = (StringBuilder)msg.obj;
                if(stringBuilder!=null&&(getSingleBoxOfficeList(stringBuilder)).size()!=0){
                    Log.d("现在的list大小：",String.valueOf((getSingleBoxOfficeList(stringBuilder)).size()));
                    initBoxOfficeAdapter(getSingleBoxOfficeList(stringBuilder));
                } else {

                }
            }
        };
        GetBoxOfficeData.getStringBuilder(boxofficehandler, "https://api.shenjian.io/?appid=51abe84d503ac7bf17c322da00d1cb52");
    }


    /**
     * 初始化其他控件
     * @param singleBoxOfficeList
     */
    private void initOtherwidgets(List<SingleBoxOffice> singleBoxOfficeList){

        /* 实例化每日总票房TextView */
        sumBoxOffice = (TextView)findViewById(R.id.boxoffice_sumbox);

        /* 实例化日期TextView */
        date = (TextView)findViewById(R.id.boxoffice_date);

        /* 实例化返回按钮 */
        back = (Button)findViewById(R.id.boxoffice_back);

        /* 返回按钮设置监听器 */
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* 总票房 */
        Double sumboxoffice = 0.0;

        /* 计算总票房 */
        for (int i = 0;i<singleBoxOfficeList.size();i++){
            sumboxoffice+=singleBoxOfficeList.get(i).getBoxOffice();
        }

        /* 给每日总票房TextView设置文本 */
        sumBoxOffice.setText(new DecimalFormat("0.0").format(sumboxoffice)+"万");

        /* 给日期TextView设置文本 */
        date.setText(singleBoxOfficeList.get(0).getTime().substring(0,4)+"年"+singleBoxOfficeList.get(0).getTime().substring(5,7)+"月"+
                singleBoxOfficeList.get(0).getTime().substring(8,10)+"日票房");

    }


    /**
     * 初始化RecycleView
     * @param singleBoxOfficeList
     */
    private void initBoxOfficeAdapter(List<SingleBoxOffice> singleBoxOfficeList){
        /* 初始化其他控件 */
        initOtherwidgets(singleBoxOfficeList);

        /* 实例化RecycleView */
        recyclerView = (RecyclerView)findViewById(R.id.boxoffice_recycleview);

        /* 给RecycleView设置布局管理器 */
        linearLayoutManager = new LinearLayoutManager(BoxOfficeActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        /* 实例化boxOfficeAdapter */
        boxOfficeAdapter = new BoxOfficeAdapter(singleBoxOfficeList);

        /* 给RecycleView设置适配器 */
        recyclerView.setAdapter(boxOfficeAdapter);

    }

}
