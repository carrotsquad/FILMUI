package com.example.teamwork.filmui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.teamwork.filmui.MainActivity;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.adapters.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilmPageFragment extends Fragment{
    private View view;
    private Context mContext;

    /** 一级滑动标签页页的ViewPager和TabLayout **/
    private ViewPager mViewPager_1;
    private TabLayout mTabLayout_1;

    /** 创建随碎片列表 **/
    List<Fragment> fragments_1;

    /**第一级滑动页标题**/
    public static final String []sTitle_1 = new String[]{"电影","影院"};
    /**第一级滑动页的图标**/
    private int[] tabIcons_1 = {
            R.drawable.icfilm,
            R.drawable.icthe,
    };

    public static Fragment newInstance(){
        FilmPageFragment fragment = new FilmPageFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.film_layout,null);
        mContext = getActivity();

        initPageView();
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


    private void initPageView(){

        /* 实例化二维码按钮 */
        Button QRbutton = (Button)view.findViewById(R.id.button_QR);
        /* 实例化定位按钮 */
        Button locationbutton = (Button) view.findViewById(R.id.button_location);
        /* 实例化搜索按钮 */
        Button searchbutton = (Button)view.findViewById(R.id.button_sur);

        /* 设置搜索按钮监听器 */
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"You clicked search.",Toast.LENGTH_SHORT).show();
            }
        });

        /* 设置二维码按钮监听器 */
        QRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"You clicked QR.",Toast.LENGTH_SHORT).show();
            }
        });

        /* 设置定位按钮监听器 */
        locationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGPSPermission();
                initLocation();
                Toast.makeText(mContext,"重新定位完成",Toast.LENGTH_SHORT).show();
            }
        });

        /* 初始化页面 */
        initView();

        /* 检查定位权限状态 */
        getGPSPermission();

        /* 初始化定位 */
        initLocation();
    }


    /**
     * 初始化页面，创建碎片
     */
    private void initView() {

        /* 初始化第一级滑动页 */
        mViewPager_1 = (ViewPager) view.findViewById(R.id.vp_content_1);
        mTabLayout_1 = (TabLayout) view.findViewById(R.id.tabLayout_1);
        mTabLayout_1.setupWithViewPager(mViewPager_1);

        /*
         * 设置第一级TabLayout设置监听器
         */
        mTabLayout_1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            } });

        /* 实例化 */
        fragments_1 = new ArrayList<>();

        /* 加入第一级的两个碎片 */
        fragments_1.add(MovieFragment.newInstance());
        fragments_1.add(TheatreFragment.newInstance());

        /* 设置一级碎片适配器 */
        MyFragmentAdapter adapter_1 = new MyFragmentAdapter(getChildFragmentManager(),fragments_1, Arrays.asList(sTitle_1));
        mViewPager_1.setAdapter(adapter_1);

        /* 设置初始化页面 */
        mViewPager_1.setCurrentItem(0);

        /* 自定义tab布局 */
        mTabLayout_1.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout_1.getTabAt(1).setCustomView(getTabView(1));



        /* 设置监听器 */
        mViewPager_1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 滑动标签页响应
             * @param position
             */
            @Override public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            } });

    }


    /**
     * 动态获取定位权限
     */
    private void getGPSPermission(){
        if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }


    /**
     * 初始化各种定位,返回AMapLocation对象
     */
    private AMapLocation initLocation(){

        final AMapLocation[] MapLocation = new AMapLocation[1];

        //声明AMapLocationClient类对象
        AMapLocationClient mLocationClient = null;

        //声明定位回调监听器
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    TextView citylocation = (TextView) view.findViewById(R.id.text_location);
                    citylocation.setText(aMapLocation.getDistrict());
                    Log.d("定位",aMapLocation.getAddress());

                    MapLocation[0] = aMapLocation;
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                    String errinfo = "location Error,ErrorCode:" + String.valueOf(aMapLocation.getErrorCode()) + "ErrorInfo:" + String.valueOf(aMapLocation.getErrorInfo());
                    Toast.makeText(mContext, errinfo, Toast.LENGTH_SHORT).show();
                }
            }
        };
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //声明AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = null;
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //获取最近3s内精度最高的一次定位结果：
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);

        if (mLocationClient != null) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }

        return MapLocation[0];

    }


    /**
     * 返回权限获取结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initLocation();
                } else {
                    Toast.makeText(mContext,"You denied the permissions", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 自定义tabView
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.layout_tab, null);
        TextView txt_title = (TextView) mView.findViewById(R.id.txt_title);
        txt_title.setText(sTitle_1[position]);
        ImageView img_title = (ImageView) mView.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons_1[position]);
        return mView;
    }
}
