package com.example.teamwork.filmui.fragments;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamwork.filmui.MainActivity;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.adapters.ComingSoonMovieAdapter;
import com.example.teamwork.filmui.beans.SingleComingSoonMovie;
import com.example.teamwork.filmui.utils.GetImageFromWeb;
import com.example.teamwork.filmui.utils.HttpGetFilmData;

import java.util.List;

import static com.example.teamwork.filmui.utils.ComingSoonMovieParse.getComingSoonMovieBean;

public class Subtwo extends Fragment {


    /** 水平瀑布流 **/
    private LinearLayout mHorizontalLinear;

    /** ”即将上映“适配器 **/
    private ComingSoonMovieAdapter comingSoonMovieAdapter;

    private Context mContext;

    private View view;

    public static Fragment newInstance(){
        Subtwo fragment = new Subtwo();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.future_layout,null);
        mContext = getActivity();
        initComingSoonMovie();
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
     * 初始化“即将上映”
     */
    private void initComingSoonMovie(){
        Handler comingsoonmoviehandler;
        comingsoonmoviehandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder = (StringBuilder) msg.obj;
                if(stringBuilder!=null&&(getComingSoonMovieBean(stringBuilder)).size()!=0){
                    Log.d("现在的list大小：",String.valueOf((getComingSoonMovieBean(stringBuilder)).size()));
                    initHorizontal(getComingSoonMovieBean(stringBuilder));
                    initComingSoonMovieAdapter(getComingSoonMovieBean(stringBuilder));
                } else {

                }
            }
        };
        HttpGetFilmData.getStringBuilder(comingsoonmoviehandler, "http://api.douban.com/v2/movie/coming_soon?");

    }


    /**
     * 初始化“即将上映”的RecycleView和水平瀑布流
     * @param singleComingSoonMovieList
     */
    private void initComingSoonMovieAdapter(List<SingleComingSoonMovie> singleComingSoonMovieList){
        RecyclerView comingsoonmovie_recycleview = (RecyclerView) view.findViewById(R.id.recycler_view_2);
        GridLayoutManager layoutManager2 = new GridLayoutManager(mContext,1);
        comingsoonmovie_recycleview.setLayoutManager(layoutManager2);
        comingSoonMovieAdapter = new ComingSoonMovieAdapter(getActivity(),singleComingSoonMovieList);
        comingsoonmovie_recycleview.setAdapter(comingSoonMovieAdapter);

        /*  解决数据加载不完的问题  */
        comingsoonmovie_recycleview.setNestedScrollingEnabled(false);
        comingsoonmovie_recycleview.setHasFixedSize(true);
        /* 解决数据加载完成后, 没有停留在顶部的问题  */
        comingsoonmovie_recycleview.setFocusable(false);
    }


    /**
     *初始化横向的瀑布流
     */
    private void initHorizontal(List<SingleComingSoonMovie> singleComingSoonMovies){
        mHorizontalLinear = (LinearLayout)view.findViewById(R.id.id_horizontallinear);
        //开始添加数据
        for(int i = 0;i<singleComingSoonMovies.size();i++){
            //寻找行布局，第一个参数为行布局ID，第二个参数为这个行布局需要放到那个容器上
            View view1 = LayoutInflater.from(mContext).inflate(R.layout.horizontal_item,mHorizontalLinear,false);
            //通过View寻找ID实例化控件
            ImageView img = (ImageView) view1.findViewById(R.id.movie_item_hotimageview);
            TextView title = (TextView) view1.findViewById(R.id.movie_item_hotnametextview);
            TextView collectcount = (TextView) view1.findViewById(R.id.movie_item_hotgradetextview);
            GetImageFromWeb.setImageView(singleComingSoonMovies.get(i).getImageId(),img, getActivity());
            title.setText(singleComingSoonMovies.get(i).getTitle());
            collectcount.setText(singleComingSoonMovies.get(i).getWishcount()+"人收藏");
            mHorizontalLinear.addView(view1);
        }

    }
}
