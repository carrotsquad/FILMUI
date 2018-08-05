package com.example.teamwork.filmui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamwork.filmui.MainActivity;
import com.example.teamwork.filmui.MovieDetailActivity;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.beans.SingleHotMovie;
import com.example.teamwork.filmui.utils.GetImageFromWeb;

import java.util.List;

import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_ID;
import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_RATING;
import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_TAGS;
import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_TITLE;

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
        LinearLayout linearLayout;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            filmImage = (ImageView) view.findViewById(R.id.film_image);
            filmname = (TextView) view.findViewById(R.id.filmname);
            commit = (TextView) view.findViewById(R.id.filmcommit);
            actors = (TextView) view.findViewById(R.id.filmactors);
            directors = (TextView) view.findViewById(R.id.filmdirectors);
            linearLayout = (LinearLayout) view.findViewById(R.id.hotmovieitem_linearlayout);
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
        final ViewHolder holder = new ViewHolder(view);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SingleHotMovie singleHotMovie1 = hotMovieBeanList.get(position);
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MOVIE_RATING, singleHotMovie1.getCommit());
                intent.putExtra(MOVIE_ID, singleHotMovie1.getId());
                intent.putExtra(MOVIE_TITLE, singleHotMovie1.getTitle());
                intent.putExtra(MOVIE_TAGS, singleHotMovie1.getTags());
                mContext.startActivity(intent);
            }
        });

        holder.filmImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SingleHotMovie singleHotMovie1 = hotMovieBeanList.get(position);
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MOVIE_RATING, singleHotMovie1.getCommit());
                intent.putExtra(MOVIE_ID, singleHotMovie1.getId());
                intent.putExtra(MOVIE_TITLE, singleHotMovie1.getTitle());
                intent.putExtra(MOVIE_TAGS, singleHotMovie1.getTags());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotMovieAdapter.ViewHolder holder, int position) {
        singleHotMovie = hotMovieBeanList.get(position);
        holder.filmname.setText(singleHotMovie.getTitle());
        holder.commit.setText("评分："+Double.toString(singleHotMovie.getCommit()));
        holder.directors.setText("导演："+singleHotMovie.getDirectors());
        holder.actors.setText("演员："+singleHotMovie.getActors());
        GetImageFromWeb.setImageView(singleHotMovie.getImageId(), holder.filmImage, activity);
    }

    @Override
    public int getItemCount() {
            return hotMovieBeanList.size();
    }

}
