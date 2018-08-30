package com.example.teamwork.filmui.homepagepackage.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;


import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.homepagepackage.beans.City;
import com.example.teamwork.filmui.homepagepackage.utils.PinyinUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongtianming on 2018/8/18.
 */

public class AutoTextViewAdapter extends BaseAdapter implements Filterable{
    private List<City> cities;//数据集合
    private ArrayFilter mFilter;//全局变量Filter
    private Context context;    //上下文
    private ArrayList<City> mUnfilteredData;//未进行过滤的数据集合
    private ArrayList<City> mfilteredData;//已经进行过滤了的数据集合

    public AutoTextViewAdapter(List<City> cities, Context context) {
        this.cities = cities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(parent.getContext(), R.layout.auto_textview_item,null);
        TextView textView=view.findViewById(R.id.test555);
        textView.setText(cities.get(position).getName());
        return view;
    }


    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }
    private class ArrayFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //初始化一个FilterResult
            FilterResults results = new FilterResults();
            //初始化未过滤集合,如果该集合未初始化,则进行初始化
            if (mUnfilteredData==null){
                mUnfilteredData=new ArrayList<>(cities);
            }
            //对用户输入进行判断
            if (constraint == null || constraint.length() == 0) {
                //用户没有输入,数据不会产生变化
                ArrayList<City> list = mUnfilteredData;
                results.values = list;
                results.count = list.size();
            }
            else {
                String prefixString = PinyinUtils.getPinyin(constraint.toString().trim()).toLowerCase().substring(0,1);
                ArrayList<City> unfilteredValues = mUnfilteredData;
                int count=mUnfilteredData.size();
                ArrayList<City> newValues = new ArrayList<City>(count);
                for (int i = 0; i < count; i++) {
                    City city = unfilteredValues.get(i);
                    if (city != null) {
                        if(city.getPinyin()!=null && city.getPinyin().toLowerCase().startsWith(prefixString)) {//比较得出需要的数据
                            newValues.add(city);
                        }
                    }
                }
                mfilteredData=newValues;
                results.count=newValues.size();
                results.values=newValues;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cities = (List<City>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                //如果为0 则还原初始状态并且不会显示出来
                notifyDataSetInvalidated();
            }
        }
    }
    private class ViewHolder{
        TextView textView;
    }
}
