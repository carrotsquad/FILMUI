package com.example.teamwork.filmui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class TheatreFilterPop extends PopupWindow {

    /**布局填充器**/
    private LayoutInflater mInflater;
    /**上下文**/
    private Context mContext;
    /**只显示String类型的数据**/
    private List<String> mDatas = new ArrayList<>();
    /**pop整体View**/
    private View popupView;
    /**选择条件的list**/
    private ListView contentLv;
    /**筛选条件选择后的回调**/
    AdapterView.OnItemClickListener itemClickListener;
    /**适配器*/
    CommPopAdapter adapter;

    /**
     * 构造函数
     *
     * @param context
     * @param mDatas
     */
    public TheatreFilterPop(Context context, List<String> mDatas) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = (mDatas);
        popupView = mInflater.inflate(
                R.layout.common_popup_list_dialog, null);
        //设置View
        this.setContentView(popupView);
        //设置弹出窗体的宽高
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //初始化控件
        initPopView();
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        //需要动画效果的话可以设置
        //this.setAnimationStyle(R.style.PopupWindowAnimation);
        this.update();
    }


    private void initPopView() {
        contentLv = (ListView) popupView.findViewById(R.id.lv_pop);
        adapter = new CommPopAdapter(mContext, mDatas);
        contentLv.setAdapter(adapter);
    }


    /**
     * listview点击事件
     *
     * @param itemClickListener
     */
    public void setOnItemSelectedListener(AdapterView.OnItemClickListener itemClickListener) {
        if (null != itemClickListener && null != contentLv) {
            contentLv.setOnItemClickListener(itemClickListener);
        }
    }
}


