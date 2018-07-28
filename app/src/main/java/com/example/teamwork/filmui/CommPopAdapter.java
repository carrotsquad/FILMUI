package com.example.teamwork.filmui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CommPopAdapter extends BaseAdapter {

    /**筛选条件数据**/
    private List<String> mDatas = new ArrayList<>();
    /**布局加载器**/
    private LayoutInflater mInflater;

    public CommPopAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.common_popup_list_item, null);
            viewHolder.mTitleTv = (TextView) convertView.findViewById(R.id.tv_common_listpop_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTitleTv.setText(mDatas.get(position));
        return convertView;

    }

    public class ViewHolder{
        TextView mTitleTv;
    }
}
