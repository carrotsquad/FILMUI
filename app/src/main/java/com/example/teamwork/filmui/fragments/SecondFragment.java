package com.example.teamwork.filmui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teamwork.filmui.MainActivity;
import com.example.teamwork.filmui.R;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondFragment extends Fragment {

    private Context mContext;

    private String headers[] = {"附近区域", "离我最近", "特色品牌"};
    private List<View> popupViews = new ArrayList<>();

    private String nearmenu[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String distancemunu[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String brandmenu[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;

    private com.example.teamwork.filmui.GirdDropDownAdapter NearAdapter;
    private com.example.teamwork.filmui.ListDropDownAdapter DistanceAdapter;
    private com.example.teamwork.filmui.ConstellationAdapter BrandAdapter;
    private ListView nearView;
    private ListView distanceView;
    private View constellationView;
    private GridView constellation;

    @BindView(R.id.dropDownMenu)
    DropDownMenu theatreDropDownMenu;

    public static Fragment newInstance(){
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_theatre,null);
        mContext = getActivity();
//        initTheatreMenu();
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
     * 初始化过滤条,有bug
     */
    private void initTheatreMenu(){
        ButterKnife.bind(getActivity());
        //init city menu
        nearView = new ListView(mContext); //gird:准备
        NearAdapter = new com.example.teamwork.filmui.GirdDropDownAdapter(mContext, Arrays.asList(nearmenu));
        nearView.setDividerHeight(0);
        nearView.setAdapter(NearAdapter);

        //init age menu
        distanceView = new ListView(mContext);
        distanceView.setDividerHeight(0);
        DistanceAdapter = new com.example.teamwork.filmui.ListDropDownAdapter(mContext, Arrays.asList(distancemunu));
        distanceView.setAdapter(DistanceAdapter);

        //init constellation
        constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        constellation = ButterKnife.findById(constellationView, R.id.constellation);
        BrandAdapter = new com.example.teamwork.filmui.ConstellationAdapter(mContext, Arrays.asList(brandmenu));
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
        nearView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NearAdapter.setCheckItem(position);
                theatreDropDownMenu.setTabText(position == 0 ? headers[0] : nearmenu[position]);
                theatreDropDownMenu.closeMenu();
            }
        });

        distanceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DistanceAdapter.setCheckItem(position);
                theatreDropDownMenu.setTabText(position == 0 ? headers[1] : distancemunu[position]);
                theatreDropDownMenu.closeMenu();
            }
        });


        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BrandAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init popupViews
        popupViews.add(nearView);
        popupViews.add(distanceView);
        popupViews.add(constellationView);

        //init context view
        TextView contentView = new TextView(mContext);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        Log.e("err",Integer.toString(Arrays.asList(headers).size())+" 另一个 "+Integer.toString(popupViews.size()));

        //init dropdownview
        theatreDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        popupViews.clear();

    }
}
