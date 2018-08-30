package com.example.teamwork.filmui.homepagepackage.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.homepagepackage.beans.City;

import java.util.List;

/**
 * Created by dongtianming on 2018/8/18.
 */

public class ListViewAdapter extends BaseAdapter {
    List<City> cities;
    Context mContext;
    Activity activity;

    public ListViewAdapter(List<City> cities, Context mContext,Activity activity) {
        this.cities = cities;
        this.mContext = mContext;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return cities.size()+2;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        ViewHolder viewHolder=null;
        if (getItemViewType(position)==0){
            view=View.inflate(parent.getContext(),R.layout.gridview1,null);
            GridView gridView=view.findViewById(R.id.gridview1);
            gridView.setAdapter(new GridViewAdpater1(activity));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                TextView lastTextview;
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView=view.findViewById(R.id.grid_item_textview);
                    if (lastTextview==null){
                        lastTextview=textView;
                        textView.setTextColor(mContext.getColor(R.color.红));
                        textView.setBackground(mContext.getDrawable(R.drawable.bar0));
                    }
                    else {
                        lastTextview.setTextColor(mContext.getColor(R.color.黑));
                        lastTextview.setBackground(mContext.getDrawable(R.drawable.bar1));
                        textView.setTextColor(mContext.getColor(R.color.红));
                        textView.setBackground(mContext.getDrawable(R.drawable.bar0));
                        lastTextview=textView;
                    }
                    Intent intent=new Intent();
                    String placeName=textView.getText().toString().trim();
                    intent.putExtra("地名",placeName);
                    activity.setResult(2,intent);
                    activity.finish();
                }
            });
            return view;
        }
        else if (getItemViewType(position)==1){
            view=View.inflate(parent.getContext(),R.layout.gridview2,null);
            GridView gridView=view.findViewById(R.id.gridview2);
            gridView.setAdapter(new GridViewAdapter2());
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                TextView lastTextview;
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView=view.findViewById(R.id.grid_item_textview);
                    if (lastTextview==null){
                        lastTextview=textView;
                        textView.setTextColor(mContext.getColor(R.color.红));
                        textView.setBackground(mContext.getDrawable(R.drawable.bar0));
                    }
                    else {
                        lastTextview.setTextColor(mContext.getColor(R.color.黑));
                        lastTextview.setBackground(mContext.getDrawable(R.drawable.bar1));
                        textView.setTextColor(mContext.getColor(R.color.红));
                        textView.setBackground(mContext.getDrawable(R.drawable.bar0));
                        lastTextview=textView;
                    }
                    Intent intent=new Intent();
                    String placeName=textView.getText().toString().trim();
                    intent.putExtra("地名",placeName);
                    activity.setResult(2,intent);
                    activity.finish();
                }
            });
            return view;
        }
        else {
            if (convertView==null){
                convertView=View.inflate(parent.getContext(),R.layout.item3,null);
                viewHolder=new ViewHolder();
                viewHolder.textview1=convertView.findViewById(R.id.textview1);
                viewHolder.item_textview=convertView.findViewById(R.id.item_textview);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder= (ViewHolder) convertView.getTag();
            }

           String name=cities.get(position-2).getName();
            String pinyin=cities.get(position-2).getPinyin().substring(0,1);
            viewHolder.textview1.setText(pinyin);
            viewHolder.item_textview.setText(name);
            if (position==2){
                viewHolder.textview1.setVisibility(View.VISIBLE);
            }
            else {
                String preWord=cities.get(position-1-2).getPinyin().substring(0,1);
                if (preWord.equals(pinyin)){
                    viewHolder.textview1.setVisibility(View.GONE);
                }else {
                    viewHolder.textview1.setVisibility(View.VISIBLE);
                }
            }
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 2 ? position : 2;
    }
    public class ViewHolder{
        TextView textview1;
        TextView item_textview;
    }
}
