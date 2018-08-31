package com.example.teamwork.filmui.homepagepackage.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.activities.MovieDetailActivity;
import com.example.teamwork.filmui.homepagepackage.beans.ComingSoonMovieBean;
import com.example.teamwork.filmui.homepagepackage.utils.GetImageFromCache;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleComingSoonMovie;

import java.util.List;

import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_ID;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_RATING;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_TAGS;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_TITLE;


/**
 * Created by dongtianming on 2018/7/29.
 */

public class ComingSoonRecyclerviewAdapter extends RecyclerView.Adapter <ComingSoonRecyclerviewAdapter.ViewHolder>{
    private Activity activity;
    private Context mContext;
    private ComingSoonMovieBean comingsoonmoivebean;
    public ComingSoonRecyclerviewAdapter(ComingSoonMovieBean comingsoonmoivebean,Activity activity) {
        this.comingsoonmoivebean=comingsoonmoivebean;
        this.activity=activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,null);
        final RecyclerView.ViewHolder viewHolder=new ViewHolder(view);

        /* 转到详细介绍页面 */
        ((ViewHolder) viewHolder).movie_item_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MOVIE_RATING, comingsoonmoivebean.getSubjects().get(position).getRating().getAverage());
                intent.putExtra(MOVIE_ID, comingsoonmoivebean.getSubjects().get(position).getId());
                intent.putExtra(MOVIE_TITLE, comingsoonmoivebean.getSubjects().get(position).getTitle());
                List<String> Tags = comingsoonmoivebean.getSubjects().get(position).getGenres();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=0;i<Tags.size();i++){
                    stringBuilder.append(Tags.get(i)+" ");
                }
                intent.putExtra(MOVIE_TAGS,stringBuilder.toString() );
                mContext.startActivity(intent);
            }
        });

        return (ViewHolder) viewHolder;
    }
 //向对应的控件中设置内容
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //设置缓存数据
        if (comingsoonmoivebean.getSubjects()==null&&comingsoonmoivebean.getCount()==0) {

        }
        else {
            if (comingsoonmoivebean.getSubjects().get(position).getRating().getAverage()==(double) 0.0){
                holder.movie_item_gradetextview.setText("暂无评价");
            }
            else {
                holder.movie_item_gradetextview.setText(String.valueOf((double) comingsoonmoivebean.getSubjects().get(position).getRating().getAverage()));
            }
            holder.movie_item_nametextview.setText(comingsoonmoivebean.getSubjects().get(position).getTitle());
            GetImageFromCache.setImageView(comingsoonmoivebean.getSubjects().get(position).getImages().getSmall(),holder.movie_item_imageview,activity);
        }
    }

    @Override
    public int getItemCount() {
        if (comingsoonmoivebean.getCount()==0){
            return 20;
        }
        else {
            return comingsoonmoivebean.getSubjects().size();
        }
    }
//找到item对应的子控件
    public static class ViewHolder extends RecyclerView.ViewHolder {
    ImageView movie_item_imageview;
    TextView movie_item_gradetextview;
    TextView movie_item_nametextview;
        public ViewHolder(View itemView) {
            super(itemView);
            movie_item_imageview= (ImageView) itemView.findViewById(R.id.movie_item_imageview);
            movie_item_gradetextview = (TextView) itemView.findViewById(R.id.movie_item_gradetextview);
            movie_item_nametextview = (TextView)itemView.findViewById(R.id.movie_item_nametextview);
        }
    }
}
