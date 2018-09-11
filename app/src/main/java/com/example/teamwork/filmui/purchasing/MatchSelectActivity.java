package com.example.teamwork.filmui.purchasing;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.bumptech.glide.Glide;
import com.example.teamwork.filmui.R;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MatchSelectActivity extends AppCompatActivity {
    TabLayout mTableLayout;
    ViewPager mViewPager;
    List<String> tabTitle;
    ImageView back;
    ImageView AmapLocation;
    VpagerAdapter mVpagerAdapter;
    public static final int REQUESTCODE = 1210;
    MatchFragment1[] fragments = new MatchFragment1[3];
    TextView title;   //电影名字
    TextView movie_shuxing;  //电影属性 类型和演员排列
    TextView cinema_title_above;  //头部标题影院名字
    TextView cinema_title_bottom; //较下方的影院名字
    TextView cinema_location;     //cinema_title_bottom 下方的影院位置
    ImageView movie_poster;       //电影海报
    String movieTitle;
    String cinemaTitle;
    String movieShuxing;
    String cinemaLocation;
    String posterURL;

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
        isAllGranted(permissions);
        Intent intent=getIntent();
        movieTitle =intent.getStringExtra("movieTitle");
        cinemaTitle=intent.getStringExtra("cinemaTitle");
        movieShuxing=intent.getStringExtra("movieShuxing");
        cinemaLocation=intent.getStringExtra("cinemaLocation");
        posterURL=intent.getStringExtra("posterURL");
        initView();
        initData();
    }

    public void initView() {
        movie_shuxing = findViewById(R.id.moview_shuxing);
        title = findViewById(R.id.pass_title);
        movie_poster =findViewById(R.id.movie_poster);
        cinema_location=findViewById(R.id.location);
        cinema_title_bottom=findViewById(R.id.cinima_title);
        cinema_title_above=findViewById(R.id.title);
        movie_shuxing.setText(movieShuxing);
        title.setText(movieTitle);
        cinema_title_above.setText(cinemaTitle);
        cinema_title_bottom.setText(cinemaTitle);
        cinema_location.setText(cinemaLocation);
        Glide.with(this).load(posterURL).into(movie_poster);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        AmapLocation = findViewById(R.id.find_location);
        AmapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MatchSelectActivity.this, "导航功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });

        sharedPreferences = getSharedPreferences("infos", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("date_1",getOldDate(0));
        editor.putString("date_2",getOldDate(1));
        editor.putString("date_3",getOldDate(2));
        editor.putString("shuxing",movieShuxing);
        editor.putString("imageUrl",posterURL);
        editor.commit();

        editor.commit();
        mTableLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
        tabTitle = new ArrayList<>();
        MatchFragment1 fragment1= new MatchFragment1();
        MatchFragment1 fragment2= new MatchFragment1();
        MatchFragment1 fragment3= new MatchFragment1();
        tabTitle.add(getOldDate(0));
        tabTitle.add(getOldDate(1));
        tabTitle.add(getOldDate(2));


//        Log.e("是否为空",movieShuxing);
//        Log.e("是否为空",posterURL);

        Bundle bundle = new Bundle();
        bundle.putString("date",getOldDate(0));
        bundle.putString("shuxing",movieShuxing);
        bundle.putString("imageUrl",posterURL);
        fragment1.setArguments(bundle);
        bundle.clear();
        bundle.putString("date",getOldDate(1));
        bundle.putString("shuxing",movieShuxing);
        bundle.putString("imageUrl",posterURL);
        fragment2.setArguments(bundle);
        bundle.clear();
        bundle.putString("date",getOldDate(2));
        bundle.putString("shuxing",movieShuxing);
        bundle.putString("imageUrl",posterURL);
        fragment3.setArguments(bundle);
        bundle.clear();

        fragments[0] = fragment1;
        fragments[1] = fragment2;
        fragments[2] = fragment3;

        //设置预加载最大页数为2
        mViewPager.setOffscreenPageLimit(2);
        mVpagerAdapter = new VpagerAdapter(getSupportFragmentManager(), this, fragments, tabTitle);
        mViewPager.setAdapter(mVpagerAdapter);
        mTableLayout.setupWithViewPager(mViewPager);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("index",Integer.toString(position));
                editor.commit();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        }

    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }


    public static void actionStart(Context context,String cinemaTitle,String movieTitle,String cinemaLocation,String movieShuxing, String posterURL) {
        Intent intent = new Intent(context, MatchSelectActivity.class);
        intent.putExtra("movieTitle",movieTitle);
        intent.putExtra("cinemaTitle", cinemaTitle);
        intent.putExtra("cinemaLocation",cinemaLocation);
        intent.putExtra("movieShuxing",movieShuxing);
        intent.putExtra("posterURL",posterURL);
        context.startActivity(intent);
    }

    public void initData() {
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            DataProducer.downLoadData(movieTitle, cinemaTitle, i, list -> {
                        if (list.size()> 0) {
                            fragments[finalI].setDataFragment(list);
                        } else {
                           DataProducer.putDataInbackground(cinemaTitle, movieTitle, finalI, posterURL,new MyCallBack<Integer>() {
                               @Override
                               public void onSuccess(Integer data) {
                                   if (data==2){
                                       initData();
                                   }
                               }
                           });
                        }
                    }
            );
        }

    }

    //检查应用是否具有权限
    public boolean checkPermissionAllGranted(@NonNull String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    //检查是否拥有所有权限如果有，执行定位方法toStartLocation 返回  ，如果没有 向用户申请
    public void isAllGranted(@NonNull String[] permissions) {
        boolean isAllGranted = checkPermissionAllGranted(permissions);
        Log.d("fuck if", "isAllGranted" + isAllGranted);
        if (isAllGranted) {
            Log.d("fuck", "isAllGranted: true");
            return;
        }
        ActivityCompat.requestPermissions(this, permissions, REQUESTCODE);//此方法会自动调出一个提醒用户允许授权的弹框
    }

    //对requestPermission方法（即弹出弹框建议用户允许权限）后 进行是否允许了权限结果进行处理 如果requestCode相同  并且处理结果全为GRANTED那么执行定位方法，否则执行提醒用户手动打开权限方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantedResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantedResults);

        if (requestCode == REQUESTCODE) {
            boolean isAllGranted = true;
            for (int grant : grantedResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                Log.d("fuck 123", "isAllGranted true");
            } else {
                openAppDetial();
                Log.d("fuck", "openapp ");
            }

        }

    }

    private void openAppDetial() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("请求");
        dialog.setMessage("备份通讯录需要访问 “通讯录” 和 “外部存储器”，请到 “应用信息 -> 权限” 中授予！");
        dialog.setCancelable(true);
        dialog.setPositiveButton("去授予", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton("取消", null);
        dialog.show();
    }
}

//    public void toStartLocation() {
//        AMapLocationListener listener = new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//                if (true) {
//                    if (aMapLocation.getErrorCode() == 0) {
//                        Log.d("fuck it", "city is " + aMapLocation.getCity());
//                        Log.d("fuck it", "country is  " + aMapLocation.getCountry());
//                        adress = "你的位置是：" + aMapLocation.getCountry() + "  " + aMapLocation.getCity();
//                        Log.d("fuck it", adress);
//                        mTextView.setText(adress);
//                    } else {
//                        Log.d("fuck it", "errorCode is " + aMapLocation.getErrorCode() + "errorInfo is " + aMapLocation.getErrorInfo());
//                    }
//                }
//            }
//        };
//        client = new AMapLocationClient(this);
//        client.setLocationListener(listener);
//        //设置定位场景（签到，出行，运动）
//        AMapLocationClientOption option = new AMapLocationClientOption();
//        //设置定位模式（高精度，低耗，仅用设备定位）
//        AMapLocationClientOption locationOption = new AMapLocationClientOption();
//        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
//        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        if (null != client) {
//            client.setLocationOption(option);
//            client.stopLocation();
//            client.startLocation();
//        }
//        locationOption.setOnceLocation(true);
//        client.setLocationOption(locationOption);
//        client.startLocation();
//    }
//
//    //    public void initGeoFence() {
//    //        final GeoFenceClient geoFenceClient = new GeoFenceClient(this);
//    //        geoFenceClient.setActivateAction(GeoFenceClient.GEOFENCE_IN | GeoFenceClient.GEOFENCE_STAYED | GeoFenceClient.GEOFENCE_OUT);
//    //        geoFenceClient.addGeoFence(cqupt, 10000, "电影院");
//    //        GeoFenceListener geoFenceListener = new GeoFenceListener() {
//    //            @Override
//    //            public void onGeoFenceCreateFinished(List<GeoFence> list, int errorCode, String s) {
//    //                if (errorCode == GeoFence.ADDGEOFENCE_SUCCESS) {
//    //                    Log.d("geofen", "CreateGeoFen = true");
//    //
//    //                } else
//    //                    Log.d("geofen", "CreateGeoFen = false");
//    //            }
//    //        };
//    //        geoFenceClient.setGeoFenceListener(geoFenceListener);
//    //
//    //    }
//
//    public void initPoiPointer() {
//        PoiSearch.Query query = new PoiSearch.Query(null, "080601", "023");
//        query.setPageSize(10);
//        query.setPageNum(2);
//        PoiSearch poiSearch = new PoiSearch(this, query);
//        PoiSearch.OnPoiSearchListener listener = new PoiSearch.OnPoiSearchListener() {
//            @Override
//            public void onPoiSearched(PoiResult poiResult, int i) {
//                if (i == 1000) {
//                    List<PoiItem> poiItems = poiResult.getPois();
//                    for (PoiItem poiItem : poiItems) {
//                        CinemaMsg msg = new CinemaMsg(poiItem.getTitle(), poiItem.getAdName() + "   " + poiItem.getSnippet(), "32元");
//                        mCinemaMsgs.add(msg);
//                        Log.d("poi", "title is " + msg.getTitle());
//                        Log.d("poi", "location is " + msg.getLocation());
//                    }
//                    Log.d("poi", "success");
//                    initCinemaAdapter();
//                } else
//                    Log.d("poi", "errorCode" + i);
//            }
//
//            @Override
//            public void onPoiItemSearched(PoiItem poiItem, int i) {
//
//            }
//        };
//
//        poiSearch.searchPOIAsyn();
//        poiSearch.setOnPoiSearchListener(listener);
//        LatLonPoint cqupt1 = new LatLonPoint(29.53832, 106.613922);
//        poiSearch.setBound(new PoiSearch.SearchBound(cqupt1, 50000, true));
//    }

//    public void initCinemaAdapter() {
//        CinemaAdapter adapter = new CinemaAdapter(mCinemaMsgs);
//        Log.d("adapter", "" + mCinemaMsgs.size());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
//    }
//
//}
//
