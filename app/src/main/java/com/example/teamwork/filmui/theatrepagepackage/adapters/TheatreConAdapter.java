package com.example.teamwork.filmui.theatrepagepackage.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.activities.TheatreToFilmActivity;
import com.example.teamwork.filmui.purchasing.MatchSelectActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.teamwork.filmui.purchasing.MatchSelectActivity.actionStart;

public class TheatreConAdapter extends RecyclerView.Adapter<TheatreConAdapter.ViewHolder>{

    private Context mContext;

    private List<PoiItem> poiItemList;
    private List<String> info;
    private String thename;
    private String theaddr;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView theatrename;
        TextView theatreaddress;
        TextView theatredistance;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            theatrename = (TextView) view.findViewById(R.id.id_theatrename);
            theatreaddress = (TextView) view.findViewById(R.id.id_theatreaddress);
            theatredistance = (TextView) view.findViewById(R.id.id_theatredistance);
        }
    }

    public TheatreConAdapter(List<PoiItem> poiItemList,List<String> filminfo){
        this.poiItemList = poiItemList;
//        this.filminfo=filminfo;
        info=new ArrayList<>();
        for(int i=0;i<filminfo.size();i++){
            info.add(filminfo.get(i));
        }
    }

    @NonNull
    @Override
    public TheatreConAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.theatre_item, parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(info.size()!=0){
                    Log.d("filllll",  info.get(0)+"           "+info.get(1)+"           "+info.get(2));
                    actionStart(mContext,thename,info.get(0),theaddr,info.get(1),info.get(2));
                }else {
                    Log.d("filllll",  "lol");
                    Intent intent = new Intent(mContext, TheatreToFilmActivity.class);
                    intent.putExtra("cinemaTitle", thename);
                    intent.putExtra("cinemaLocation",theaddr);
                    mContext.startActivity(intent);
                }
            }
        });


        return new TheatreConAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheatreConAdapter.ViewHolder holder, int position) {
        PoiItem poiItem = poiItemList.get(position);
        thename=poiItem.getTitle();
        theaddr=poiItem.getSnippet();
        holder.theatrename.setText(poiItem.getTitle());
        holder.theatreaddress.setText(poiItem.getSnippet());
        holder.theatredistance.setText(Integer.toString(poiItem.getDistance())+"米");
    }

    @Override
    public int getItemCount() {
        return poiItemList.size();
    }
}
