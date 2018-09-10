package com.example.teamwork.filmui.minepagepackage.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.activities.MyTickets;
import com.example.teamwork.filmui.activities.UserMovieActivity;
import com.example.teamwork.filmui.minepagepackage.Activity.InfoActivity;
import com.example.teamwork.filmui.minepagepackage.Activity.LoginActivity;
import com.example.teamwork.filmui.minepagepackage.Activity.SettingActivity;
import com.example.teamwork.filmui.minepagepackage.Activity.WebActivity;

import static android.app.Activity.RESULT_OK;
import static com.example.teamwork.filmui.UriStorage.alreadyget_uri;
import static com.example.teamwork.filmui.UriStorage.wannaget_uri;

public class MinePageFragment extends Fragment implements View.OnClickListener{

    private View view;
    private Context mContext;

    private LinearLayout to_login;
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;
    private String returnData;
    private SharedPreferences preferences;
    private ImageButton setting;
    private LinearLayout mine_movie;
    private LinearLayout mine_card;
    private LinearLayout mine_coupon;
    private LinearLayout mine_show;

    private LinearLayout want_movie;
    private LinearLayout watched_movie;
    private LinearLayout mine_comm;
    private LinearLayout mine_little;
    private LinearLayout mine_discussion;
    private LinearLayout mine_redpack;
    private LinearLayout mine_bankcard;
    private LinearLayout mine_mall;
    private LinearLayout mine_entertainment;
    private LinearLayout mine_help;


    public static Fragment newInstance(){
        MinePageFragment fragment = new MinePageFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mine,null);
        mContext = getActivity();


        initView();
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        manager=getChildFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.to_login_model,new LoginFragment());
        transaction.commit();
        changeFragment();
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


    public void initView(){
        want_movie = view.findViewById(R.id.want_movie);
        watched_movie = view.findViewById(R.id.watched_movie);
        mine_comm = view.findViewById(R.id.mine_comm);
        mine_little = view.findViewById(R.id.mine_little);
        mine_discussion = view.findViewById(R.id.mine_discussion);
        mine_redpack = view.findViewById(R.id.mine_redpack);
        mine_bankcard = view.findViewById(R.id.mine_bankcard);
        mine_mall = view.findViewById(R.id.mine_mall);
        mine_entertainment = view.findViewById(R.id.mine_entertainment);
        mine_help = view.findViewById(R.id.mine_help);
        mine_show = view.findViewById(R.id.mine_show);
        mine_movie = view.findViewById(R.id.mine_movie);
        mine_coupon = view.findViewById(R.id.mine_coupon);
        mine_card = view.findViewById(R.id.mine_card);
        to_login = view.findViewById(R.id.to_login_model);
        setting = view.findViewById(R.id.mine_setting);

        mine_show.setOnClickListener(this);
        mine_card.setOnClickListener(this);
        mine_coupon.setOnClickListener(this);
        mine_movie.setOnClickListener(this);
        setting.setOnClickListener(this);
        to_login.setOnClickListener(this);
        want_movie.setOnClickListener(this);
        watched_movie.setOnClickListener(this);
        mine_comm.setOnClickListener(this);
        mine_little.setOnClickListener(this);
        mine_discussion.setOnClickListener(this);
        mine_redpack.setOnClickListener(this);
        mine_bankcard.setOnClickListener(this);
        mine_mall.setOnClickListener(this);
        mine_entertainment.setOnClickListener(this);
        mine_help.setOnClickListener(this);
    }
    private boolean check(){
        preferences = mContext.getSharedPreferences("users", Context.MODE_PRIVATE);
        String name = preferences.getString("name","");
        boolean exist = name.isEmpty();
        return exist;
    }

    private void no_sign(){
        Intent no_sign = new Intent(getActivity(), LoginActivity.class);
        startActivity(no_sign);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.to_login_model:
                if (check()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                }else{
                    Intent intent = new Intent(getActivity() ,InfoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.mine_setting:
                Intent intent = new Intent(mContext,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_movie:
                if (check()){
                    no_sign();
                }else{
                    Intent intent2 = new Intent(mContext,MyTickets.class);
                    startActivity(intent2);
                }

                break;
            case R.id.mine_coupon:
                if (check()){
                    no_sign();
                }else{

                }
                break;
            case R.id.mine_card:
                if (check()){
                    no_sign();
                }else{

                }
                break;
            case R.id.mine_show:
                if (check()){
                    no_sign();
                }else{

                }
                break;

            case R.id.want_movie:
                if (check()){
                    no_sign();
                }else{
                    Intent intent1 = new Intent(getActivity(), UserMovieActivity.class);
                    intent1.putExtra("class","wanna");
                    intent1.putExtra("url", wannaget_uri);
                    startActivity(intent1);
                }
                break;
            case R.id.watched_movie:
                if (check()){
                    no_sign();
                }else{
                    Intent intent1 = new Intent(getActivity(), UserMovieActivity.class);
                    intent1.putExtra("class","already");
                    intent1.putExtra("url", alreadyget_uri);
                    startActivity(intent1);
                }
                break;
            case R.id.mine_comm:
                if (check()){
                    no_sign();
                }else{

                }
                break;
            case R.id.mine_little:
                if (check()){
                    no_sign();
                }else{

                }
                break;
            case R.id.mine_discussion:
                if (check()){
                    no_sign();
                }else{

                }
                break;
            case  R.id.mine_redpack:
                if (check()){
                    no_sign();
                }else{

                }
                break;
            case R.id.mine_bankcard:
                if (check()){
                    no_sign();
                }else{
                    Intent intent1 = new Intent(getActivity(), WebActivity.class);
                    intent1.putExtra("url","https://h5.m.taobao.com/app/moviediscount/pages/bank-card/index.html?spm=a2115o.8783827.entrance.paypromotions");
                    this.startActivity(intent1);
                }
                break;
            case R.id.mine_mall:
                if (check()){
                    no_sign();
                }else{
                    Intent intent1 = new Intent(getActivity(), WebActivity.class);
                    intent1.putExtra("url","https://ip.m.alibaba.com/");
                    this.startActivity(intent1);
                }
                break;
            case R.id.mine_entertainment:
                if (check()){
                    no_sign();
                }else{
                    Intent intent1 = new Intent(getActivity(), WebActivity.class);
                    intent1.putExtra("url","http://ylb.m.taobao.com:443");
                    this.startActivity(intent1);
                }
                break;
            case R.id.mine_help:
                if (check()){
                    no_sign();
                }else{
                    Intent intent1 = new Intent(getActivity(), WebActivity.class);
                    intent1.putExtra("url","https://login.m.taobao.com/login.htm?tpl_redirect_url=https%3A%2F%2Fh5.m.taobao.com%2Falicare%2Findex.html%3Fspm%3Da2115o.8783827.entrance.help%26sid%3D1c93544115d43e9e30fa4220c10d08cb%26from%3Dtpp_movie_care%26bu%3Dtpp%26mv_h5_needlogin%3D1%26__webview_options__%3DadjustResize%253Dyes");
                    this.startActivity(intent1);
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("被销毁了");
    }


    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onrestart");
        changeFragment();
    }

    private void changeFragment(){
        manager=getChildFragmentManager();
        transaction=manager.beginTransaction();
        preferences = mContext.getSharedPreferences("users", Context.MODE_PRIVATE);
        String name = preferences.getString("name","");
        if (name.isEmpty()){
            transaction.replace(R.id.to_login_model,new LoginFragment());
        }else {
            transaction.replace(R.id.to_login_model,new LoginedFragment());
        }
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if ((resultCode == RESULT_OK)){
                    returnData = data.getStringExtra("username");
                }
                break;
            default:
        }
    }
}
