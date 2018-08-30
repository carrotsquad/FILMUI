package com.example.teamwork.filmui.homepagepackage.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.teamwork.filmui.R;


/**
 * Created by dongtianming on 2018/8/18.
 */

public class GridViewAdpater1 extends BaseAdapter {
    String string = "重庆";

    public GridViewAdpater1(Activity activity) {
        this.activity = activity;
    }

    private Activity activity;

    @Override
    public int getCount() {
        return 1;
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
        View view = null;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.grid_item, null);
        } else {
            view = convertView;
        }
        Intent getIntent = activity.getIntent();
        String locationCity = getIntent.getStringExtra("定位城市");
        TextView grid_item_textview = view.findViewById(R.id.grid_item_textview);
        if (locationCity == null) {
            grid_item_textview.setText(string);
        } else {
            grid_item_textview.setText(locationCity);
        }
        return view;
    }
}
