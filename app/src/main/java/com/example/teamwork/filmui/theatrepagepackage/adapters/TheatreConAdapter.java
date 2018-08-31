package com.example.teamwork.filmui.theatrepagepackage.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.example.teamwork.filmui.R;

import java.util.List;

public class TheatreConAdapter extends RecyclerView.Adapter<TheatreConAdapter.ViewHolder>{

    private Context mContext;

    private List<PoiItem> poiItemList;

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

    public TheatreConAdapter(List<PoiItem> poiItemList){
        this.poiItemList = poiItemList;
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

            }
        });


        return new TheatreConAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheatreConAdapter.ViewHolder holder, int position) {
        PoiItem poiItem = poiItemList.get(position);
        holder.theatrename.setText(poiItem.getTitle());
        holder.theatreaddress.setText(poiItem.getSnippet());
        holder.theatredistance.setText(Integer.toString(poiItem.getDistance())+"米");
    }

    @Override
    public int getItemCount() {
        return poiItemList.size();
    }
}
