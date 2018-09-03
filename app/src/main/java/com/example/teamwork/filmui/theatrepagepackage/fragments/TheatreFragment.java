package com.example.teamwork.filmui.theatrepagepackage.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.adapters.ConstellationAdapter;
import com.example.teamwork.filmui.theatrepagepackage.adapters.GirdDropDownAdapter;
import com.example.teamwork.filmui.theatrepagepackage.adapters.ListDropDownAdapter;
import com.example.teamwork.filmui.theatrepagepackage.adapters.TheatreConAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;


/**
 * “影院”页面
 */

public class TheatreFragment extends Fragment{

    private Context mContext;

    private String headers[] = {"附近区域", "离我最近", "特色品牌"};
    private List<View> popupViews = new ArrayList<>();

    private String distancemunu[] = {"1000m", "2000m", "3000m", "4000m", "5000m"};
    private int[] distances =  {1000, 2000, 3000, 4000, 5000};
    private String brandmenu[] = {"不限", "UME","太平洋","金逸","万达","星美","时代","中影","联和","联合","保利","横店","大地"};

    private int constellationPosition = 0;

    private GirdDropDownAdapter NearAdapter;
    private ListDropDownAdapter DistanceAdapter;
    private ConstellationAdapter BrandAdapter;
    private ListView nearView;
    private ListView distanceView;
    private View constellationView;
    private GridView constellation;

    private View view;

    private DropDownMenu theatreDropDownMenu;

    public static Fragment newInstance(){
        TheatreFragment fragment = new TheatreFragment();
        return fragment;
    }

    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private String Keywords = "电影院";
    private int Radius = 1000;
    private int posi = 0;
    private StringBuffer brand  = new StringBuffer();

    private TheatreConAdapter theatreConAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private int jug=0;
    private List<String> nearbys = new ArrayList<>();
    private StringBuffer Adcode = new StringBuffer();
    private double latitude;
    private double longitude;
    private DistrictSearch districtSearch;
    private DistrictSearchQuery districtSearchQuery;
    private int num=0;

    private DistrictItem districtItem;

    private List<String> info = new ArrayList<>();
    private String name;
    private String genre;
    private String poster;
    private Bundle bundle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_theatre,null);
        mContext = getActivity();

        initgetbundle();
        initTheatreMenu();
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
     * 获取电影信息
     */
    private void initgetbundle(){
        bundle=getArguments();
        if(bundle!=null){
            name=bundle.getString("name");
            genre=bundle.getString("genre");
            poster=bundle.getString("poster");
            info.add(name);
            info.add(genre);
            info.add(poster);
            Log.e("filmlllll", name+"       "+genre+"        "+poster);
        }
    }


    /**
     * 初始化各种定位及列表
     */
    private void initTheatre(){

        final AMapLocation[] MapLocation = new AMapLocation[1];

        //声明AMapLocationClient类对象
        AMapLocationClient mLocationClient = null;

        //声明定位回调监听器
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (jug == 0) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        Log.d("定位", aMapLocation.getAddress());
                         /* 没有选择附近区域 */
                          Adcode.setLength(0);
                          Adcode.append(aMapLocation.getAdCode());
                          latitude = aMapLocation.getLatitude();
                          longitude = aMapLocation.getLongitude();
                        initTheatreRecycleView(Adcode, latitude, longitude, Radius, posi, brand);
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        String errinfo = "location Error,ErrorCode:" + String.valueOf(aMapLocation.getErrorCode()) + "ErrorInfo:" + String.valueOf(aMapLocation.getErrorInfo());
                        Toast.makeText(mContext, errinfo, Toast.LENGTH_SHORT).show();
                    }
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

//        return MapLocation[0];

    }


    /**
     * 初始化列表
     * @param Adcode
     * @param latitude
     * @param longitude
     * @param Radius
     * @param posi
     * @param brand
     */
    private void initTheatreRecycleView(StringBuffer Adcode,double latitude, double longitude, int Radius, final int posi, final StringBuffer brand){
        query = new PoiSearch.Query(Keywords,"", Adcode.toString());
        query.setPageSize(20);
        poiSearch = new PoiSearch(mContext, query);
        //设置周边搜索的中心点以及半径
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), Radius));
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {

                List<PoiItem> poiItemList = poiResult.getPois();
                int lenth = poiItemList.size();
                String str_brand = ".*"+brand.toString()+".*";
                Log.e("666666666666666666",str_brand);
                Log.e("6666666666666666666666",Integer.toString(str_brand.length()));
                Log.e("666666666666666666 列表长度",Integer.toString(poiItemList.size()));
                Log.e("666666666666666666",Integer.toString(posi));

                if(posi>0){
                    for (int j=0;j<lenth;j++) {
                        PoiItem poiItem = poiItemList.get(j);
                        Log.e("66666666666666666", poiItem.getTitle());
                        if(poiItem.getTitle().matches(str_brand)){
                            Log.e("66666666666666666",poiItem.getTitle()+"包含"+str_brand);
                        } else {
                            poiItemList.remove(poiItem);

                            /* 关键一步 */
                            j=-1;
                            lenth = poiItemList.size();
                            Log.e("66666666666666666666666",poiItem.getTitle()+"不包含"+str_brand);
                        }

                    }
                }

                Log.e("666666666666666666 列表长度",Integer.toString(poiItemList.size()));
                theatreConAdapter = new TheatreConAdapter(poiItemList,info);
//                recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_3);
                gridLayoutManager = new GridLayoutManager(mContext,1);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(theatreConAdapter);
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();

    }


    /**
     * 初始化区域及列表
     * @return
     */
    private DistrictItem initDistricts(){

        final AMapLocation[] MapLocation = new AMapLocation[1];

        //声明AMapLocationClient类对象
        AMapLocationClient mLocationClient = null;

        //声明定位回调监听器
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation.getErrorCode() == 0) {
                        districtItem = new DistrictItem();
                        districtSearch = new DistrictSearch(mContext);
                        districtSearchQuery = new DistrictSearchQuery();
                        //传入关键字
                        districtSearchQuery.setKeywords(aMapLocation.getCity());

                        Log.e("888888888888888", aMapLocation.getCity());
                        //是否返回边界值
                        districtSearchQuery.setShowBoundary(false);
                        districtSearch.setQuery(districtSearchQuery);

                        //绑定监听器
                        districtSearch.setOnDistrictSearchListener(new DistrictSearch.OnDistrictSearchListener() {
                            @Override
                            public void onDistrictSearched(DistrictResult districtResult) {
                                nearbys.clear();
                                DistrictItem districtItem = districtResult.getDistrict().get(0);
                                List<DistrictItem> districtItemArrayList = districtItem.getSubDistrict();
                                for (int j=0;j<districtItemArrayList.size();j++){
                                    nearbys.add(districtItemArrayList.get(j).getName());
                                }
                                districtItem = districtItemArrayList.get(num);
                                if(jug==1){
                                    /* 选择附近区域 */
                                    Adcode.setLength(0);
                                    Adcode.append(districtItem.getCitycode());
                                    latitude = districtItem.getCenter().getLatitude();
                                    longitude = districtItem.getCenter().getLongitude();

                                    Log.e("99999999999999999",Adcode.toString());
                                    initTheatreRecycleView(Adcode,latitude,longitude,Radius,posi,brand);
                                }
                            }
                        });
                        //开始搜索
                        districtSearch.searchDistrictAsyn();
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
        return districtItem;
    }


    /**
     * 初始化过滤条
     */
    private void initTheatreMenu(){
        ButterKnife.bind(view);

        theatreDropDownMenu = view.findViewById(R.id.dropDownMenu);

        initDistricts();

        //init city menu
        nearView = new ListView(mContext); //gird:准备
        NearAdapter = new GirdDropDownAdapter(mContext, nearbys);
        nearView.setDividerHeight(0);
        nearView.setAdapter(NearAdapter);

        //init age menu
        distanceView = new ListView(mContext);
        distanceView.setDividerHeight(0);
        DistanceAdapter = new ListDropDownAdapter(mContext, Arrays.asList(distancemunu));
        distanceView.setAdapter(DistanceAdapter);

        //init constellation
        constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        constellation = ButterKnife.findById(constellationView, R.id.constellation);
        BrandAdapter = new ConstellationAdapter(mContext, Arrays.asList(brandmenu));
        constellation.setAdapter(BrandAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //其内部已经设置了记录当前tab位置的参数，该参数会随tab被点击而改变，所以这里直接设置tab值即可
                //此处若想获得constellations第一个值“不限”，可修改constellationPosition初始值为-1，且这里代码改为constellationPosition == -1)
                theatreDropDownMenu.setTabText((constellationPosition == 0) ? headers[2] : brandmenu[constellationPosition]);
                theatreDropDownMenu.closeMenu();
//                changeContentView();   //在这里可以请求获得经筛选后要显示的内容
            }
        });


        //add item click event
        /* 设置完附近区域后 */
        nearView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NearAdapter.setCheckItem(position);
                theatreDropDownMenu.setTabText(position == 0 ? headers[0] : nearbys.get(position));
                num = position;
                jug = 1;
                initDistricts();
                theatreDropDownMenu.closeMenu();

            }
        });


        /* 重新设置距离后 */
        distanceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DistanceAdapter.setCheckItem(position);
                theatreDropDownMenu.setTabText(position == 0 ? headers[1] : distancemunu[position]);
                Radius = distances[position];
                if(jug==0){
                    initTheatre();
                }else {
                    initDistricts();
                }
                theatreDropDownMenu.closeMenu();
            }
        });


        /* 重新设置品牌后 */
        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BrandAdapter.setCheckItem(position);
                constellationPosition = position;
                posi = constellationPosition;
                if(position>0){
                    /* 清空内容 */
                    brand.setLength(0);
                    /* 追加内容 */
                    brand.append(brandmenu[constellationPosition]);
                    Log.e("666666666666666666",brand.toString());
                    /* 重新初始化 */
                }
                if(jug==0){
                    initTheatre();
                }else {
                    initDistricts();
                }
            }
        });

        //init popupViews
        popupViews.add(nearView);
        popupViews.add(distanceView);
        popupViews.add(constellationView);

        //init context view
        recyclerView = new RecyclerView(mContext);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        initTheatre();


        Log.e("err",Integer.toString(Arrays.asList(headers).size())+" 另一个 "+Integer.toString(popupViews.size()));

        //init dropdownview
        theatreDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, recyclerView);

//        popupViews.clear();

    }

}
