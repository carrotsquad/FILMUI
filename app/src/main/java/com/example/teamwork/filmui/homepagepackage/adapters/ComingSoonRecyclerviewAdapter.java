package com.example.teamwork.filmui.homepagepackage.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.homepagepackage.beans.ComingSoonMovieBean;
import com.example.teamwork.filmui.homepagepackage.utils.GetImageFromCache;


/**
 * Created by dongtianming on 2018/7/29.
 */

public class ComingSoonRecyclerviewAdapter extends RecyclerView.Adapter <ComingSoonRecyclerviewAdapter.ViewHolder>{
    private Activity activity;
    private ComingSoonMovieBean comingsoonmoivebean;
    public ComingSoonRecyclerviewAdapter(ComingSoonMovieBean comingsoonmoivebean,Activity activity) {
        this.comingsoonmoivebean=comingsoonmoivebean;
        this.activity=activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,null);
        RecyclerView.ViewHolder viewHolder=new ViewHolder(view);
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
