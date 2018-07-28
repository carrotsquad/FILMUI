package com.example.teamwork.filmui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;


//电影类适配器
public class FilmConAdapter extends RecyclerView.Adapter<FilmConAdapter.ViewHolder> {

    private Context mContext;

    private List<FilmCon> mfilmConList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView filmImage;
        TextView filmname;
        TextView commit;
        TextView actors;
        TextView directors;
        Button wonderbuy;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            filmImage = (ImageView) view.findViewById(R.id.film_image);
            filmname = (TextView) view.findViewById(R.id.filmname);
            commit = (TextView) view.findViewById(R.id.filmcommit);
            actors = (TextView) view.findViewById(R.id.filmactors);
            directors = (TextView) view.findViewById(R.id.filmdirectors);
            wonderbuy = (Button) view.findViewById(R.id.wonderbuy);
        }
    }

    public FilmConAdapter(List<FilmCon> filmConList){
        mfilmConList = filmConList;
    }

    @NonNull
    @Override
    public FilmConAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.film_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmConAdapter.ViewHolder holder, int position) {
        FilmCon filmCon = mfilmConList.get(position);
        String s;
        holder.filmname.setText(filmCon.getName());
        if( filmCon.getDate()>20180725){
            s = Long.toString(filmCon.getDesire()) + "人想看";
            holder.wonderbuy.setBackgroundResource(R.drawable.book);
        } else {
            s = "观众评分" + Double.toString(filmCon.getCommit());
        }
        holder.commit.setText(s);
        holder.directors.setText("导演:"+filmCon.getDirectors());
        holder.actors.setText("演员:"+filmCon.getActors());
        Glide.with(mContext).load(filmCon.getImageId()).into(holder.filmImage);
    }

    @Override
    public int getItemCount() {
        return mfilmConList.size();
    }
}
