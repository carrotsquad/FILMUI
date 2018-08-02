package com.example.teamwork.filmui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.beans.TheatreCon;

import java.util.List;

public class TheatreConAdapter extends RecyclerView.Adapter<TheatreConAdapter.ViewHolder>{

    private Context mContext;

    private List<TheatreCon> mtheatreConList;

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

    public TheatreConAdapter(List<TheatreCon> mtheatreCons){
        mtheatreConList = mtheatreCons;
    }

    @NonNull
    @Override
    public TheatreConAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.theatre_item, parent,false);
        return new TheatreConAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheatreConAdapter.ViewHolder holder, int position) {
        TheatreCon theatreCon = mtheatreConList.get(position);
        holder.theatrename.setText(theatreCon.getTheatreName());
        holder.theatreaddress.setText(theatreCon.getTheatreAddress());
        holder.theatredistance.setText(Double.toString(theatreCon.getTheatreDistance())+"km");
    }

    @Override
    public int getItemCount() {
        return mtheatreConList.size();
    }
}
