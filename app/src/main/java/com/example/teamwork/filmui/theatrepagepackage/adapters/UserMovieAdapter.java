//package com.example.teamwork.filmui.theatrepagepackage.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.teamwork.filmui.R;
//import com.example.teamwork.filmui.activities.FilmToTheatreActivity;
//import com.example.teamwork.filmui.activities.MovieDetailActivity;
//import com.example.teamwork.filmui.theatrepagepackage.beans.SingleHotMovie;
//import com.example.teamwork.filmui.theatrepagepackage.beans.SingleMovie;
//import com.example.teamwork.filmui.theatrepagepackage.utils.GetImageFromWeb;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_ID;
//import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_RATING;
//import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_TAGS;
//import static com.example.teamwork.filmui.activities.MovieDetailActivity.MOVIE_TITLE;
//import static com.example.teamwork.filmui.purchasing.MatchSelectActivity.actionStart;
//
//public class UserMovieAdapter extends RecyclerView.Adapter<UserMovieAdapter.ViewHolder>{
//    private Context mContext;
//
//    private ArrayList<SingleMovie> singleMovieArrayList = new ArrayList<>();
//
//    private SingleMovie singleMovie;
//
//    static class ViewHolder extends RecyclerView.ViewHolder{
//    CardView cardView;
//    ImageView filmImage;
//    TextView filmname;
//    TextView commit;
//    TextView actors;
//    TextView directors;
//    LinearLayout linearLayout;
//    Button wonderbuy;
//
//
//    public ViewHolder(View view){
//        super(view);
//        cardView = (CardView)view;
//        filmImage = (ImageView) view.findViewById(R.id.film_image);
//        filmname = (TextView) view.findViewById(R.id.filmname);
//        commit = (TextView) view.findViewById(R.id.filmcommit);
//        actors = (TextView) view.findViewById(R.id.filmactors);
//        directors = (TextView) view.findViewById(R.id.filmdirectors);
//        linearLayout = (LinearLayout) view.findViewById(R.id.hotmovieitem_linearlayout);
//        wonderbuy = (Button) view.findViewById(R.id.wonderbuy);
//    }
//    }
//
//    public UserMovieAdapter(ArrayList<SingleMovie> singleMovieArrayList){
//
//    }
//
//    @NonNull
//    @Override
//    public UserMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if(mContext == null){
//            mContext = parent.getContext();
//        }
//        View view = LayoutInflater.from(mContext).inflate(R.layout.film_item, parent,false);
//        final UserMovieAdapter.ViewHolder holder = new UserMovieAdapter.ViewHolder(view);
//
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        holder.filmImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
////                SingleHotMovie singleHotMovie1 = hotMovieBeanList.get(position);
////                if(info.size()==0) {
////
////                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
////                    intent.putExtra(MOVIE_RATING, singleHotMovie1.getCommit());
////                    intent.putExtra(MOVIE_ID, singleHotMovie1.getId());
////                    intent.putExtra(MOVIE_TITLE, singleHotMovie1.getTitle());
////                    intent.putExtra(MOVIE_TAGS, singleHotMovie1.getTags());
//////                intent.putExtra(MOVIE_POSTER, singleHotMovie1.getImageId());
////                    mContext.startActivity(intent);
////                }else {
////                    Log.d("filllll",  info.get(0)+"           "+info.get(1)+"           ");
////                    actionStart(mContext,info.get(0),singleHotMovie1.getTitle(),info.get(1),singleHotMovie1.getTags(),singleHotMovie1.getImageId());
////                }
//            }
//        });
//
//        holder.wonderbuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                SingleHotMovie singleHotMovie1 = hotMovieBeanList.get(position);
//                if(info.size()==0) {
//                    Intent intent1=new Intent(mContext, FilmToTheatreActivity.class);
//                    intent1.putExtra("filmname", singleHotMovie1.getTitle());
//                    intent1.putExtra("filmgenre", singleHotMovie1.getTags());
//                    intent1.putExtra("filmposter",singleHotMovie1.getImageId());
//                    mContext.startActivity(intent1);
//                }
//            }
//        });
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UserMovieAdapter.ViewHolder holder, int position) {
//        singleMovie = singleMovieArrayList.get(position);
//        holder.filmname.setText(singleMovie.getTitle());
//        holder.commit.setText("评分："+Double.toString(singleMovie.getCommit()));
//        holder.directors.setText("导演："+singleMovie.getDirectors());
//        holder.actors.setText("演员："+singleMovie.getActors());
//        GetImageFromWeb.setImageView(singleMovie.getImageId(), holder.filmImage, singleMovie);
//    }
//
//    @Override
//    public int getItemCount() {
//        return singleMovieArrayList.size();
//    }
//}
