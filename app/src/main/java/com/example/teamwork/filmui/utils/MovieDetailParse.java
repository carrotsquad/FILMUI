package com.example.teamwork.filmui.utils;

import com.example.teamwork.filmui.beans.SingleCast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailParse {

    public static List<SingleCast> getMovieDetail(StringBuilder stringBuilder){
        List<SingleCast> singleCasts = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(stringBuilder));
            //第一层解析
            StringBuilder akas = new StringBuilder();
            StringBuilder countries = new StringBuilder();
            int wish_count = jsonObject.optInt("wish_count");
            int collect_count = jsonObject.optInt("collect_count");
            String summary = jsonObject.optString("summary");
            JSONArray country = jsonObject.optJSONArray("countries");
            JSONArray aka = jsonObject.optJSONArray("aka");
            JSONArray directors = jsonObject.optJSONArray("directors");
            JSONArray casts = jsonObject.optJSONArray("casts");
            JSONObject images = jsonObject.optJSONObject("images");
//          //第二层解析

            String poster = images.optString("medium");

            for (int j = 0;j<aka.length();j++){
                akas.append(aka.get(j)+" ");
            }

            for (int j = 0;j<country.length();j++){
                countries.append(country.get(j)+" ");
            }

            for(int i = 0;i<directors.length();i++){
                String name = directors.getJSONObject(i).optString("name");
                String id = directors.getJSONObject(i).optString("id");
                JSONObject avatars = directors.getJSONObject(i).optJSONObject("avatars");
                String avatar = avatars.optString("small");
                singleCasts.add(new SingleCast(name,avatar,id,summary,akas.toString(),countries.toString(),wish_count,collect_count,poster));
            }

            for(int i = 0;i<casts.length();i++){
                String name = casts.getJSONObject(i).optString("name");
                String id = casts.getJSONObject(i).optString("id");
                JSONObject avatars = casts.getJSONObject(i).optJSONObject("avatars");
                String avatar = avatars.optString("small");
                singleCasts.add(new SingleCast(name,avatar,id,summary,aka.toString(),countries.toString(),wish_count,collect_count,poster));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return singleCasts;
    }
}
