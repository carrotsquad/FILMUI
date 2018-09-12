package com.example.teamwork.filmui.theatrepagepackage.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamwork.filmui.activities.FilmToTheatreActivity;
import com.example.teamwork.filmui.activities.MovieDetailActivity;
import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleComingSoonMovie;
import com.example.teamwork.filmui.theatrepagepackage.utils.GetImageFromWeb;

import java.util.ArrayList;
import java.util.List;

import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_ID;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_RATING;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_TAGS;
import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_TITLE;
import static com.example.teamwork.filmui.purchasing.MatchSelectActivity.actionStart;

public class ComingSoonMovieAdapter extends RecyclerView.Adapter<ComingSoonMovieAdapter.ViewHolder>{

    private Context mContext;

    private List<SingleComingSoonMovie> comingSoonMovieList;

    private StringBuilder directors, actors;

    private SingleComingSoonMovie singleComingSoonMovie;

    private Activity activity;

    private List<String> info;

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

    public ComingSoonMovieAdapter(Activity activity, List<SingleComingSoonMovie> comingSoonMovieList,List<String> theatreinfo){
        this.comingSoonMovieList = comingSoonMovieList;
        this.activity = activity;

        info=new ArrayList<>();
        for(int i=0;i<theatreinfo.size();i++){
            info.add(theatreinfo.get(i));
        }
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
                    if (info.size() == 0) {
                        Intent intent = new Intent(mContext, MovieDetailActivity.class);
                        intent.putExtra(MOVIE_RATING, "0.0");
                        intent.putExtra(MOVIE_ID, singleComingSoonMovie1.getId());
                        intent.putExtra(MOVIE_TITLE, singleComingSoonMovie1.getTitle());
                        intent.putExtra(MOVIE_TAGS, singleComingSoonMovie1.getTags());
                        intent.putExtra("actors",singleComingSoonMovie1.getActors());
                        intent.putExtra("directors",singleComingSoonMovie1.getDirectors());
                        intent.putExtra("commit","0.0");
                        mContext.startActivity(intent);
                    } else {
                        Log.d("filllll", info.get(0) + "           " + info.get(1) + "           ");
                        actionStart(mContext, info.get(0), singleComingSoonMovie1.getTitle(), info.get(1), singleComingSoonMovie1.getTags(), singleComingSoonMovie1.getImageId());
                    }

            }
        });

        holder.filmImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SingleComingSoonMovie singleComingSoonMovie1 = comingSoonMovieList.get(position);
                if(info.size()==0) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra(MOVIE_RATING, 0.0);
                    intent.putExtra(MOVIE_ID, singleComingSoonMovie1.getId());
                    intent.putExtra(MOVIE_TITLE, singleComingSoonMovie1.getTitle());
                    intent.putExtra(MOVIE_TAGS, singleComingSoonMovie1.getTags());
                    intent.putExtra("actors",singleComingSoonMovie1.getActors());
                    intent.putExtra("directors",singleComingSoonMovie1.getDirectors());
                    intent.putExtra("commit","0.0");
                    mContext.startActivity(intent);
                }else {
                    Log.d("filllll",  info.get(0)+"           "+info.get(1)+"           ");
                    actionStart(mContext,info.get(0),singleComingSoonMovie1.getTitle(),info.get(1),singleComingSoonMovie1.getTags(),singleComingSoonMovie1.getImageId());
                }
            }
        });

        holder.wonderbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SingleComingSoonMovie singleComingSoonMovie1 = comingSoonMovieList.get(position);
                if(info.size()==0) {
                    Intent intent1=new Intent(mContext, FilmToTheatreActivity.class);
                    intent1.putExtra("filmname", singleComingSoonMovie1.getTitle());
                    intent1.putExtra("filmgenre", singleComingSoonMovie1.getTags());
                    intent1.putExtra("filmposter",singleComingSoonMovie1.getImageId());
                    mContext.startActivity(intent1);
                }else {
                    Log.d("filllll",  info.get(0)+"           "+info.get(1)+"           ");
                    actionStart(mContext,info.get(0),singleComingSoonMovie1.getTitle(),info.get(1),singleComingSoonMovie1.getTags(),singleComingSoonMovie1.getImageId());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonMovieAdapter.ViewHolder holder, int position) {
        singleComingSoonMovie = comingSoonMovieList.get(position);
        holder.filmname.setText(singleComingSoonMovie.getTitle());
        holder.collectcount.setText(singleComingSoonMovie.getWishcount()+"人收藏");
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
