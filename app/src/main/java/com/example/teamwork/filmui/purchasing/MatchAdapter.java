package com.example.teamwork.filmui.purchasing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;
import com.example.teamwork.filmui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */


public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    List<AVObject> mMatches =new ArrayList<>();

    public class ViewHolder extends  RecyclerView.ViewHolder{
         TextView start;  //开场时间
         TextView end;    //散场时间
         TextView shuxing; //荧幕属性
        TextView place;    //场次地点
         TextView price;   //票价
         TextView buy;     //购票按钮


        public ViewHolder(View view){
            super(view);
            buy = view.findViewById(R.id.buy);
           start = view.findViewById(R.id.start_time);
           end =view.findViewById(R.id.end_time);
           shuxing = view.findViewById(R.id.shuxing);
           place = view.findViewById(R.id.place);
           price = view.findViewById(R.id.ticket_price);

        }
    }
    public void setData(List<AVObject> list){
        mMatches.clear();
        mMatches.addAll(list);
        // 提醒系统数据变化  更新 数据
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int p){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item,null);
        final ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder ,int position){
        final AVObject object = mMatches.get(position);
        final String isSold = object.getString("mSoldAndCheck");
        viewHolder.start.setText(object.getString("start_time"));
        viewHolder.shuxing.setText(object.getString("shuxing"));
        viewHolder.end.setText(object.getString("end_time")+" "+"散场");
        viewHolder.price.setText(object.getString("price")+"元");
        viewHolder.place.setText(object.getString("place"));
        if (!viewHolder.itemView.hasOnClickListeners()){
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(),SeatSelectActivity.class);
                intent.putExtra("match",object);
                intent.putExtra("mSoldAndCheck",isSold);
                v.getContext().startActivity(intent);
    }});}}
    @Override
    public int getItemCount(){
        return mMatches.size();
    }
}
