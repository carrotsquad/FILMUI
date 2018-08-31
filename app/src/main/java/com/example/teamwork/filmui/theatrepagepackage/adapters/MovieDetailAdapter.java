package com.example.teamwork.filmui.theatrepagepackage.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleCast;
import com.example.teamwork.filmui.theatrepagepackage.utils.GetImageFromWeb;

import java.util.List;

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.ViewHolder> {

    private Context mContext;

    private List<SingleCast> singleCastList;

    private StringBuilder directors, actors;

    private SingleCast singleCast;

    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView cast_name;
        ImageView cast_image;

        public ViewHolder(View view){
            super(view);

            cast_image = (ImageView) view.findViewById(R.id.cast_avator);
            cast_name = (TextView)view.findViewById(R.id.cast_name);
        }
    }

    public MovieDetailAdapter(Activity activity, List<SingleCast> singleCastList){
        this.singleCastList = singleCastList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.cast_item, parent,false);
        final MovieDetailAdapter.ViewHolder holder = new MovieDetailAdapter.ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieDetailAdapter.ViewHolder holder, int position) {
        singleCast = singleCastList.get(position);
        holder.cast_name.setText(singleCast.getName());
        GetImageFromWeb.setImageView(singleCast.getAvatar(), holder.cast_image, activity);
    }

    @Override
    public int getItemCount() {
        return singleCastList.size();
    }
}
