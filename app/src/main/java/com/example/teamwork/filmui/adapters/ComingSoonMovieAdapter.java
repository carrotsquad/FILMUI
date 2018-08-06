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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamwork.filmui.MainActivity;
import com.example.teamwork.filmui.MovieDetailActivity;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.beans.SingleComingSoonMovie;
import com.example.teamwork.filmui.beans.SingleHotMovie;
import com.example.teamwork.filmui.utils.GetImageFromWeb;

import java.util.List;

import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_ID;
import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_POSTER;
import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_RATING;
import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_TAGS;
import static com.example.teamwork.filmui.MovieDetailActivity.MOVIE_TITLE;

public class ComingSoonMovieAdapter extends RecyclerView.Adapter<ComingSoonMovieAdapter.ViewHolder>{

    private Context mContext;

    private List<SingleComingSoonMovie> comingSoonMovieList;

    private StringBuilder directors, actors;

    private SingleComingSoonMovie singleComingSoonMovie;

    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView filmImage;
        TextView filmname;
        TextView collectcount;
        TextView actors;
        TextView directors;
        Button wonderbuy;
        LinearLayout linearLayout;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            linearLayout = (LinearLayout) view.findViewById(R.id.hotmovieitem_linearlayout);
            filmImage = (ImageView) view.findViewById(R.id.film_image);
            filmname = (TextView) view.findViewById(R.id.filmname);
            collectcount = (TextView) view.findViewById(R.id.filmcommit);
            actors = (TextView) view.findViewById(R.id.filmactors);
            directors = (TextView) view.findViewById(R.id.filmdirectors);
            wonderbuy = (Button) view.findViewById(R.id.wonderbuy);
        }
    }

    public ComingSoonMovieAdapter(Activity activity, List<SingleComingSoonMovie> comingSoonMovieList){
        this.comingSoonMovieList = comingSoonMovieList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ComingSoonMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.film_item, parent,false);
        final ComingSoonMovieAdapter.ViewHolder holder = new ComingSoonMovieAdapter.ViewHolder(view);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SingleComingSoonMovie singleComingSoonMovie1 = comingSoonMovieList.get(position);
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MOVIE_RATING, 0.0);
                intent.putExtra(MOVIE_ID, singleComingSoonMovie1.getId());
                intent.putExtra(MOVIE_TITLE, singleComingSoonMovie1.getTitle());
                intent.putExtra(MOVIE_TAGS, singleComingSoonMovie1.getTags());
                intent.putExtra(MOVIE_POSTER, singleComingSoonMovie1.getImageId());
                mContext.startActivity(intent);
            }
        });

        holder.filmImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SingleComingSoonMovie singleComingSoonMovie1 = comingSoonMovieList.get(position);
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MOVIE_RATING, 0.0);
                intent.putExtra(MOVIE_ID, singleComingSoonMovie1.getId());
                intent.putExtra(MOVIE_TITLE, singleComingSoonMovie1.getTitle());
                intent.putExtra(MOVIE_TAGS, singleComingSoonMovie1.getTags());
                intent.putExtra(MOVIE_POSTER, singleComingSoonMovie1.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonMovieAdapter.ViewHolder holder, int position) {
        singleComingSoonMovie = comingSoonMovieList.get(position);
        holder.filmname.setText(singleComingSoonMovie.getTitle());
        holder.collectcount.setText(Integer.toString(singleComingSoonMovie.getWishcount())+"人收藏");
        holder.directors.setText("导演："+singleComingSoonMovie.getDirectors());
        holder.actors.setText("演员："+singleComingSoonMovie.getActors());
        holder.wonderbuy.setBackgroundResource(R.drawable.book);
        GetImageFromWeb.setImageView(singleComingSoonMovie.getImageId(), holder.filmImage, activity);
    }

    @Override
    public int getItemCount() {
        return comingSoonMovieList.size();
    }
}
