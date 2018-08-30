package com.example.teamwork.filmui.homepagepackage.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.homepagepackage.beans.FilmMakerBean;
import com.example.teamwork.filmui.homepagepackage.utils.GetImageFromInternet;

import java.util.List;
import java.util.Random;

/**
 * Created by dongtianming on 2018/8/3.
 */

public class InfoRecyclerViewAdapter extends RecyclerView.Adapter {
    private static final int TYPE0=0;
    private static final int TYPE1=1;
    private static final int TYPE2=2;
    private List<FilmMakerBean> filmMakerBeanList;
    private Activity activity;
    private int[] type=new int[20];

    public InfoRecyclerViewAdapter(List<FilmMakerBean> filmMakerBeanList, Activity activity) {
        this.filmMakerBeanList = filmMakerBeanList;
        this.activity = activity;
        for (int i=0;i<20;i++){
            type[i]=new Random().nextInt(2);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if (viewType==TYPE0){
            view=View.inflate(parent.getContext(), R.layout.info_item_1image,null);
            Image1ViewHolder image1ViewHolder=new Image1ViewHolder(view);
            return image1ViewHolder;
        }
        else if (viewType==TYPE1){
            view=View.inflate(parent.getContext(),R.layout.info_item_3image,null);
            Image3ViewHolder image3ViewHolder=new Image3ViewHolder(view);
            return image3ViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (filmMakerBeanList==null) {

        } else {
            try {
                if (getItemViewType(position) == TYPE0) {
                    Image1ViewHolder image1ViewHolder = (Image1ViewHolder) holder;
                    image1ViewHolder.info_item_1image_textview_name.setText(filmMakerBeanList.get(position).getName().toString());
                    image1ViewHolder.info_item_1image_textview_born_place.setText(filmMakerBeanList.get(position).getBorn_place().toString());
                    image1ViewHolder.info_item_1image_textview_mobile_url.setText(filmMakerBeanList.get(position).getMobile_url().toString());
                    image1ViewHolder.info_item_1image_textview_name_en.setText(filmMakerBeanList.get(position).getName_en().toString());
                    GetImageFromInternet.setImageView(filmMakerBeanList.get(position).getAvatars().getLarge(), image1ViewHolder.info_item_1image_imageview, activity);
                } else if (getItemViewType(position) == TYPE1) {
                    Image3ViewHolder image3ViewHolder = (Image3ViewHolder) holder;
                    image3ViewHolder.info_item_3image_textview.setText(filmMakerBeanList.get(position).getName() + "的作品");
                    GetImageFromInternet.setImageView(filmMakerBeanList.get(position).getWorks().get(0).getSubject().getImages().getLarge(), image3ViewHolder.info_item_3image_imageview1, activity);
                    GetImageFromInternet.setImageView(filmMakerBeanList.get(position).getWorks().get(1).getSubject().getImages().getLarge(), image3ViewHolder.info_item_3image_imageview2, activity);
                    GetImageFromInternet.setImageView(filmMakerBeanList.get(position).getWorks().get(2).getSubject().getImages().getLarge(), image3ViewHolder.info_item_3image_imageview3, activity);
                }
            }
            catch (Exception e){}
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        if (type[position]==TYPE0){
            return 0;
        }
        else if (type[position]==TYPE1){
            return 1;
        }
        return 2;
    }

    private class Image3ViewHolder extends RecyclerView.ViewHolder{
        ImageView info_item_3image_imageview1;
        ImageView info_item_3image_imageview2;
        ImageView info_item_3image_imageview3;
        TextView info_item_3image_textview;;
    public Image3ViewHolder(View itemView) {
        super(itemView);
        info_item_3image_imageview1=itemView.findViewById(R.id.info_item_3image_imageview1);
        info_item_3image_imageview2=itemView.findViewById(R.id.info_item_3image_imageview2);
        info_item_3image_imageview3=itemView.findViewById(R.id.info_item_3image_imageview3);
        info_item_3image_textview=itemView.findViewById(R.id.info_item_3image_textview);
    }
}
    private class Image1ViewHolder extends RecyclerView.ViewHolder{
        ImageView info_item_1image_imageview;
        TextView info_item_1image_textview_name;
        TextView info_item_1image_textview_name_en;
        TextView info_item_1image_textview_mobile_url;
        TextView info_item_1image_textview_born_place;
        public Image1ViewHolder(View itemView) {
            super(itemView);
            info_item_1image_imageview=(ImageView) itemView.findViewById(R.id.info_item_1image_imageview);
            info_item_1image_textview_name=(TextView)itemView.findViewById(R.id.info_item_1image_textview_name);
            info_item_1image_textview_name_en=(TextView)itemView.findViewById(R.id.info_item_1image_textview_name_en);
            info_item_1image_textview_mobile_url=(TextView)itemView.findViewById(R.id.info_item_1image_textview_mobile_url);
            info_item_1image_textview_born_place=(TextView)itemView.findViewById(R.id.info_item_1image_textview_born_place);
        }
    }
}
