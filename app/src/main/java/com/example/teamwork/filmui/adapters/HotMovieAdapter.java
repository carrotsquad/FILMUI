package com.example.teamwork.filmui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.beans.SingleHotMovie;
import com.example.teamwork.filmui.utils.GetImageFromWeb;

import java.util.List;

public class HotMovieAdapter extends RecyclerView.Adapter<HotMovieAdapter.ViewHolder> {

    private Context mContext;

    private List<SingleHotMovie> hotMovieBeanList;

    private StringBuilder directors, actors;

    private SingleHotMovie singleHotMovie;

    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView filmImage;
        TextView filmname;
        TextView commit;
        TextView actors;
        TextView directors;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            filmImage = (ImageView) view.findViewById(R.id.film_image);
            filmname = (TextView) view.findViewById(R.id.filmname);
            commit = (TextView) view.findViewById(R.id.filmcommit);
            actors = (TextView) view.findViewById(R.id.filmactors);
            directors = (TextView) view.findViewById(R.id.filmdirectors);
        }
    }

    public HotMovieAdapter(Activity activity, List<SingleHotMovie> hotMovieBeanList){
        this.hotMovieBeanList = hotMovieBeanList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HotMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.film_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotMovieAdapter.ViewHolder holder, int position) {
        singleHotMovie = hotMovieBeanList.get(position);
        holder.filmname.setText(singleHotMovie.getTitle());
        holder.commit.setText("评分："+Double.toString(singleHotMovie.getCommit()));
//        for(int i=0;i<SingleHotMovie.getSubjects().get(position).getDirectors().size();i++){
//            directors.append(SingleHotMovie.getSubjects().get(position).getDirectors().get(i).getName());
//        }
//        for(int i=0;i<SingleHotMovie.getSubjects().get(position).getCasts().size();i++){
//            actors.append(SingleHotMovie.getSubjects().get(position).getDirectors().get(i).getName());
//        }
        holder.directors.setText("导演："+singleHotMovie.getDirectors());
        holder.actors.setText("演员："+singleHotMovie.getActors());
        GetImageFromWeb.setImageView(singleHotMovie.getImageId(), holder.filmImage, activity);
    }

    @Override
    public int getItemCount() {
            return hotMovieBeanList.size();
    }

}
