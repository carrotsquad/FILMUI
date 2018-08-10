package com.example.teamwork.filmui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.teamwork.filmui.BoxOfficeActivity;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.adapters.HotMovieAdapter;
import com.example.teamwork.filmui.beans.SingleBoxOffice;
import com.example.teamwork.filmui.beans.SingleHotMovie;
import com.example.teamwork.filmui.utils.GetBoxOfficeData;
import com.example.teamwork.filmui.utils.HttpGetFilmData;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.teamwork.filmui.utils.BoxOfficeParse.getSingleBoxOfficeList;
import static com.example.teamwork.filmui.utils.HotMovieParse.getHotMoiveBean;

public class InTheatreFragment extends Fragment{

    private View view;

    private Context mContext;

    /** ”正在热映适配器“ **/
    private HotMovieAdapter hotMovieAdapter;

    /** 票房按钮设置 **/
    private TextView todaysumboxoffice;
    private RelativeLayout boxoffice;

    public static Fragment newInstance(){
        InTheatreFragment fragment = new InTheatreFragment();
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.now_layout,null);
        mContext = getActivity();
        initHotMovie();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * 初始化“正在热映”
     */
    private void initHotMovie(){
        Handler hotmoviehandler;
        hotmoviehandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder = (StringBuilder) msg.obj;
                if(stringBuilder!=null&&(getHotMoiveBean(stringBuilder)).size()!=0){
                    Log.d("现在的list大小：",String.valueOf((getHotMoiveBean(stringBuilder)).size()));
                    initHotMovieAdapter(getHotMoiveBean(stringBuilder));
                } else {

                }
            }
        };
        HttpGetFilmData.getStringBuilder(hotmoviehandler, "http://api.douban.com/v2/movie/in_theaters?");
    }


    /**
     * 初始化“正在热映”的RecycleView
     * @param hotmovielist
     */
    private void initHotMovieAdapter(List<SingleHotMovie> hotmovielist){
        boxoffice = (RelativeLayout)view.findViewById(R.id.now_layout_boxoffice);
        boxoffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BoxOfficeActivity.class);
                startActivity(intent);
            }
        });
        setsumBoxOffice();


        RecyclerView hotmovie_recycleview = (RecyclerView) view.findViewById(R.id.recycler_view_1);
        GridLayoutManager layoutManager1 = new GridLayoutManager(mContext,1);
        hotmovie_recycleview.setLayoutManager(layoutManager1);
        hotMovieAdapter = new HotMovieAdapter(getActivity(), hotmovielist);
        hotmovie_recycleview.setAdapter(hotMovieAdapter);
        /*  解决数据加载不完的问题  */
        hotmovie_recycleview.setNestedScrollingEnabled(false);
        hotmovie_recycleview.setHasFixedSize(true);
        /* 解决数据加载完成后, 没有停留在顶部的问题  */
        hotmovie_recycleview.setFocusable(false);



    }


    /**
     * 设置每日总票房
     */
    private void setsumBoxOffice(){
        Handler boxofficehandler;
        todaysumboxoffice = (TextView)view.findViewById(R.id.now_layout_todaysumboxoffice);
        boxofficehandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder = (StringBuilder)msg.obj;
                if(stringBuilder!=null&&(getSingleBoxOfficeList(stringBuilder)).size()!=0){
                    Log.d("现在的list大小：",String.valueOf((getSingleBoxOfficeList(stringBuilder)).size()));
                    todaysumboxoffice.setText(new DecimalFormat("0.0").format(getListSum(getSingleBoxOfficeList(stringBuilder)))+"万元");
                } else {

                }
            }
        };
        GetBoxOfficeData.getStringBuilder(boxofficehandler, "https://api.shenjian.io/?appid=51abe84d503ac7bf17c322da00d1cb52");
    }


    /**
     * 计算票房总和
     * @param singleBoxOfficeList
     * @return
     */
    private Double getListSum(List<SingleBoxOffice> singleBoxOfficeList){
        Double sum = 0.0;
        for (int i = 0; i<singleBoxOfficeList.size(); i++){
            sum += singleBoxOfficeList.get(i).getBoxOffice();
        }
        return sum;
    }
}
