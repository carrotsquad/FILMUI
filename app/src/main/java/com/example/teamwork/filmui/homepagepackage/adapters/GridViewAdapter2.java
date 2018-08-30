package com.example.teamwork.filmui.homepagepackage.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.teamwork.filmui.R;

/**
 * Created by dongtianming on 2018/8/18.
 */

public class GridViewAdapter2 extends BaseAdapter {
    private String hotCity[]={"上海","北京","广州","杭州","深圳","武汉","成都","重庆","南京","西安"};
    @Override
    public int getCount() {
        return hotCity.length;
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
        if (convertView==null){
            view=View.inflate(parent.getContext(), R.layout.grid_item,null);
        }
        else {
            view=convertView;
        }
        TextView grid_item_textview=view.findViewById(R.id.grid_item_textview);
        grid_item_textview.setText(hotCity[position]);
        return view;
    }
}
