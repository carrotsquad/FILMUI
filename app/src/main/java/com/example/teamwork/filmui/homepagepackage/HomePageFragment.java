package com.example.teamwork.filmui.homepagepackage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.homepagepackage.CitySearchActivity;
import com.example.teamwork.filmui.homepagepackage.SearchViewActivity;
import com.example.teamwork.filmui.homepagepackage.adapters.ComingSoonRecyclerviewAdapter;
import com.example.teamwork.filmui.homepagepackage.adapters.HotRecyclerviewAdapter;
import com.example.teamwork.filmui.homepagepackage.adapters.InfoRecyclerViewAdapter;
import com.example.teamwork.filmui.homepagepackage.adapters.MyViewPagerAdapter;
import com.example.teamwork.filmui.homepagepackage.beans.ComingSoonMovieBean;
import com.example.teamwork.filmui.homepagepackage.beans.FilmMakerBean;
import com.example.teamwork.filmui.homepagepackage.beans.HotMoiveBean;
import com.example.teamwork.filmui.homepagepackage.beans.ViewPagerAltBean;
import com.example.teamwork.filmui.homepagepackage.utils.ComingSoonMovieParse;
import com.example.teamwork.filmui.homepagepackage.utils.HotMovieParse;
import com.example.teamwork.filmui.homepagepackage.utils.HttpGetJsonData;
import com.example.teamwork.filmui.homepagepackage.utils.HttpGetJsonData2;
import com.example.teamwork.filmui.homepagepackage.utils.SmapleTitleBehavior2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongtianming on 2018/8/17.
 */

public class HomePageFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private LocationClient mLocationClient;
    private String locationCity;
    ////////////////////////////////////////////////
    private View view;
    //////////////////////////////////////////////////////////ViewPager的数据
    private List<ViewPagerAltBean> viewPagerAltBeanList;
    private ViewPager activity_main_viewpager;
    private LinearLayout activity_main_llpoints;
    private List<ImageView> imageViewList;;
    private int viewPagerLastIndex;
    private boolean isRunning=false;
    ////////////////////////////////////////////////////////////ComingSoonRecycler的数据
    private RecyclerView activity_main_recycylerview;
    private ComingSoonMovieBean comingSoonMovieBean;
    private Handler recyclerViewHandler;
    private TextView activity_main_totaltextvie;
    //////////////////////////////////////////////////////////////hotRecycler数据
    private RecyclerView activity_main_recycylerview_hot;
    private Handler hotRecyclerViewHandler;
    private HotMoiveBean hotMoiveBean;
    /////////////////////////////////////////////////////////////infoRecycler数据
    private RecyclerView activity_main_inforecycylerview;
    private List<FilmMakerBean> filmMakerBeanList;
    private Handler infoRecyclerViewHandler;
    ////////////////////////////////////////////////////////其他
    private LinearLayout loadll;
    private ImageView search_movie;
    private TextView loadTextview;
    private EditText editTex;
    private RelativeLayout editTex_rl;


    private Context mContext;

    public static Fragment newInstance(){
        HomePageFragment  fragment = new HomePageFragment();
        return fragment;
    }


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_page_fragment,null);

        mContext = getActivity();

        SmapleTitleBehavior2.loadll=view.findViewById(R.id.load_ll);
        SmapleTitleBehavior2.activity=getActivity();
        loadll=view.findViewById(R.id.load_ll);
        loadTextview=view.findViewById(R.id.load_textview);
        search_movie=view.findViewById(R.id.search_movie);
        editTex=view.findViewById(R.id.edittex);
        editTex_rl=view.findViewById(R.id.edittex_rl);
        editTex_rl.setOnClickListener(this);
        editTex.setOnClickListener(this);
        search_movie.setOnClickListener(this);
        loadll.getChildAt(0).setOnClickListener(this);
        ////////////////////////////////////////
        initViewPagerView();
        initViewPagerData();
        initViewPagerAdapter();
        initComingSoonRecyclerView();
        initComingSoonRecyclerData();
        initHotRecyclerView();
        initHotRecyclerData();
        initInfoRecyclerView();
        initInfoRecyclerData();
        ///////////////////////////////定位功能
        getPermission();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        isRunning=false;
    }
    ////////////////////////////////////////////////////////////viewPager
    private void initViewPagerAdapter(){
        activity_main_viewpager.setAdapter(new MyViewPagerAdapter(imageViewList));
        int pos=Integer.MAX_VALUE/2-(Integer.MAX_VALUE/2%imageViewList.size());
        activity_main_viewpager.setCurrentItem(pos);
        new Thread(){
            @Override
            public void run() {
                isRunning=true;
                while (isRunning){
                    try {
                        Thread.sleep(4000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity_main_viewpager.setCurrentItem(activity_main_viewpager.getCurrentItem()+1);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    private void initViewPagerView(){
        activity_main_viewpager= (ViewPager) view.findViewById(R.id.activity_main_viewpager);
        activity_main_llpoints= (LinearLayout)view. findViewById(R.id.activity_main_llpoints);
        activity_main_viewpager.setOnPageChangeListener(this);
    }
    private void initViewPagerData(){
        viewPagerAltBeanList = new ArrayList<>();
        imageViewList = new ArrayList<>();
        ViewPagerAltBean viewPagerAltBean=null;
        ImageView imageView=null;
        View point=null;
        LinearLayout.LayoutParams params=null;
        int []imageResId = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.gpicture,R.drawable.h};
        for (int i=0;i<8;i++){
            viewPagerAltBean=new ViewPagerAltBean();
            viewPagerAltBean.setImageId(imageResId[i]);
            viewPagerAltBeanList.add(viewPagerAltBean);
        }
        for (int j=0;j<viewPagerAltBeanList.size();j++){
            imageView=new ImageView(getActivity());
            imageView.setBackgroundResource(viewPagerAltBeanList.get(j).getImageId());
            imageViewList.add(imageView);
            point=new View(getActivity());
            point.setBackgroundResource(R.drawable.selector_bg_point);
            params = new LinearLayout.LayoutParams(15,15);
            params.setMargins(5,5,5,5);
            point.setEnabled(false);
            activity_main_llpoints.addView(point,params);
        }
    }
    //////////////////////////////////////////////////////////////////////////////comingrecycler
    public void initComingSoonRecyclerView(){
        activity_main_recycylerview= (RecyclerView)view.findViewById(R.id.activity_main_recycylerview);
        activity_main_totaltextvie= (TextView) view.findViewById(R.id.activity_main_totaltextview);
    }
    public void initComingSoonRecyclerData(){
        recyclerViewHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder=(StringBuilder) msg.obj;
                if (stringBuilder==null|| TextUtils.isEmpty(stringBuilder.toString())){
                    initComingSoonRecyclerAdapter();
                }
                else {
                    comingSoonMovieBean = ComingSoonMovieParse.getComingSoonMovieBean(stringBuilder);
                    if (comingSoonMovieBean == null) {
                        activity_main_totaltextvie.setText("全部"+20+"部");
                    }
                    else {
                        activity_main_totaltextvie.setText("全部"+comingSoonMovieBean.getSubjects().size()+"部");
                    }
                    initComingSoonRecyclerAdapter();
                }
            }
        };
        HttpGetJsonData.getStringBuilder(recyclerViewHandler, "http://api.douban.com/v2/movie/in_theaters?");
        initComingSoonRecyclerAdapter();
    }
    public void initComingSoonRecyclerAdapter(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        activity_main_recycylerview.setLayoutManager(linearLayoutManager);
        ComingSoonRecyclerviewAdapter comingSoonRecyclerviewAdapter=null;
        if (comingSoonMovieBean==null){
            comingSoonRecyclerviewAdapter=new ComingSoonRecyclerviewAdapter(new ComingSoonMovieBean(),getActivity());
        }
        else {
            comingSoonRecyclerviewAdapter = new ComingSoonRecyclerviewAdapter(comingSoonMovieBean, getActivity());
        }
        activity_main_recycylerview.setAdapter(comingSoonRecyclerviewAdapter);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////hotrecycler///////////////////////////////////////////////////////
    public void initHotRecyclerView(){
        activity_main_recycylerview_hot= (RecyclerView)view.findViewById(R.id.activity_main_hotrecycylerview);
    }
    public void initHotRecyclerData(){
        hotRecyclerViewHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder= (StringBuilder) msg.obj;
                if (stringBuilder!=null) {
                    hotMoiveBean = HotMovieParse.getHotMoiveBean(stringBuilder);
                    initHotRecyclerAdapter();
                }
                else {
                    initHotRecyclerAdapter();
                }
            }
        };
        HttpGetJsonData.getStringBuilder(hotRecyclerViewHandler,"http://api.douban.com/v2/movie/coming_soon?");
        initHotRecyclerAdapter();
    }
    public void initHotRecyclerAdapter(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        activity_main_recycylerview_hot.setLayoutManager(linearLayoutManager);
        HotRecyclerviewAdapter hotRecyclerviewAdapter=null;
        if (hotMoiveBean==null){
            hotRecyclerviewAdapter=new HotRecyclerviewAdapter(getActivity(),new HotMoiveBean());
        }
        else {
            hotRecyclerviewAdapter = new HotRecyclerviewAdapter(getActivity(),hotMoiveBean);
        }
        activity_main_recycylerview_hot.setAdapter(hotRecyclerviewAdapter);
    }
    //////////////////////////////////////////////////////////////////////////////////////////infoRecycler
    public void initInfoRecyclerView(){
        activity_main_inforecycylerview= (RecyclerView) view.findViewById(R.id.activity_main_inforecycylerview);
    }
    public void initInfoRecyclerData(){
        infoRecyclerViewHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                filmMakerBeanList= (List<FilmMakerBean>) msg.obj;
                initInfoRecyclerAdapter();
            }
        };
        HttpGetJsonData2.sendMessage(infoRecyclerViewHandler);
        initInfoRecyclerAdapter();
    }
    public void initInfoRecyclerAdapter(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        activity_main_inforecycylerview.setLayoutManager(linearLayoutManager);
        activity_main_inforecycylerview.setAdapter(new InfoRecyclerViewAdapter(filmMakerBeanList,getActivity()));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//写ViewPager的监听事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        activity_main_llpoints.getChildAt(position%imageViewList.size()).setEnabled(true);
        activity_main_llpoints.getChildAt(viewPagerLastIndex).setEnabled(false);
        viewPagerLastIndex = position%imageViewList.size();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load_textview://城市搜索监听
                Intent intent=new Intent(getActivity(),CitySearchActivity.class);
                if (locationCity!=null){
                intent.putExtra("定位城市",locationCity);
                }
                startActivityForResult(intent,1);
                break;
            case R.id.search_movie://电影查找监听
                Intent intent1=new Intent(getActivity(),SearchViewActivity.class);
                startActivity(intent1);
                break;
            case R.id.edittex_rl:
                Intent intent2=new Intent(getActivity(),SearchViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.edittex:
                Intent intent3=new Intent(getActivity(),SearchViewActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String placeName;
        if (resultCode==1){
            placeName=data.getStringExtra("地名");
            loadTextview.setText(placeName);
            mLocationClient.stop();
        }
        else if (resultCode==2){
            placeName=data.getStringExtra("地名");
            loadTextview.setText(placeName);
            mLocationClient.stop();
        }
        else if (resultCode==3){
            placeName=data.getStringExtra("地名");
            loadTextview.setText(placeName);
            mLocationClient.stop();
        }
        else if (resultCode==4){
            placeName=data.getStringExtra("地名");
            loadTextview.setText(placeName);
            mLocationClient.stop();
        }
    }
    ///////////////////////////////////////权限申请
    private void getPermission(){
        mLocationClient=new LocationClient(getContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        List<String> permissionList=new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String []permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(),permissions,1);
        }
        else {
            requestLocation();
        };
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int result:grantResults){
                        if (result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(getActivity().getApplicationContext(),"必须同意权限",Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            return;
                        }
                        requestLocation();
                    }
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(),"未知错误",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                break;
            default:
                break;
        }
    }
    private class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation.getCity()==null){

            }
            else {
                locationCity=bdLocation.getCity();
                loadTextview.setText(locationCity);
            }
        }
    }
}
