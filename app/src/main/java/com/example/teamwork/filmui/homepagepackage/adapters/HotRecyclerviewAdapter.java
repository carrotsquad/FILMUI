package com.example.teamwork.filmui.homepagepackage.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.activities.MovieDetailActivity;
import com.example.teamwork.filmui.homepagepackage.beans.HotMoiveBean;
import com.example.teamwork.filmui.homepagepackage.utils.GetImageFromCache;

import java.util.List;

import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_ID;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_RATING;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_TAGS;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_TITLE;


/**
 * Created by dongtianming on 2018/7/29.
 */

public class HotRecyclerviewAdapter extends RecyclerView.Adapter<HotRecyclerviewAdapter.ViewHolder> {
    private Activity activity;
    private Context mContext;
    private HotMoiveBean hotMoiveBean;

    public HotRecyclerviewAdapter(Activity activity, HotMoiveBean hotMoiveBean) {
        this.activity = activity;
        this.hotMoiveBean = hotMoiveBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }

        View view=View.inflate(parent.getContext(), R.layout.coming_movie_item,null);
        final ViewHolder viewHolder=new ViewHolder(view);

        /* 转到详细介绍页面 */
        ((ViewHolder) viewHolder).movie_item_hotimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MOVIE_RATING, 0.0);
                intent.putExtra(MOVIE_ID, hotMoiveBean.getSubjects().get(position).getId());
                intent.putExtra(MOVIE_TITLE, hotMoiveBean.getSubjects().get(position).getTitle());
                List<String> Tags = hotMoiveBean.getSubjects().get(position).getGenres();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=0;i<Tags.size();i++){
                    stringBuilder.append(Tags.get(i)+" ");
                }
                intent.putExtra(MOVIE_TAGS,stringBuilder.toString() );
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (hotMoiveBean.getSubjects()==null&&hotMoiveBean.getCount()==0){

        }
        else {
            holder.movie_item_hotnametextview.setText(hotMoiveBean.getSubjects().get(position).getTitle());
            holder.movie_item_hotgradetextview.setText(hotMoiveBean.getSubjects().get(position).getCollect_count() + "人收藏");
            GetImageFromCache.setImageView(hotMoiveBean.getSubjects().get(position).getImages().getSmall(),holder.movie_item_hotimageview,activity);
        }
    }

    @Override
    public int getItemCount() {
        if (hotMoiveBean.getCount()==0){
            return 20;
        }
        return hotMoiveBean.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_item_hotimageview;
        TextView movie_item_hotgradetextview;
        TextView movie_item_hotnametextview;
        public ViewHolder(View itemView) {
            super(itemView);
            movie_item_hotimageview=(ImageView) itemView.findViewById(R.id.movie_item_hotimageview);
            movie_item_hotgradetextview=(TextView)itemView.findViewById(R.id.movie_item_hotgradetextview);
            movie_item_hotnametextview=(TextView)itemView.findViewById(R.id.movie_item_hotnametextview);
        }
    }
}
