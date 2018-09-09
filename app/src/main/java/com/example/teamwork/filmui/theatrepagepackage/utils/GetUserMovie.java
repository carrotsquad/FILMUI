package com.example.teamwork.filmui.theatrepagepackage.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.teamwork.filmui.theatrepagepackage.beans.SingleCast;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleHotMovie;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;
import static com.example.teamwork.filmui.theatrepagepackage.utils.MovieDetailParse.getMovieDetail;

public class GetUserMovie {

    public static SingleHotMovie getUserMovie(StringBuilder stringBuilder) throws JSONException {

        JSONObject jsonObject = new JSONObject(String.valueOf(stringBuilder));
            //第一层解析
            String title = jsonObject.optString("title");
            JSONObject rating = jsonObject.optJSONObject("rating");
            StringBuilder countries = new StringBuilder();
            JSONArray genres = jsonObject.optJSONArray("genres");
            int wish_count = jsonObject.optInt("wish_count");
            int collect_count = jsonObject.optInt("collect_count");
            String summary = jsonObject.optString("summary");
            JSONArray country = jsonObject.optJSONArray("countries");
            JSONArray aka = jsonObject.optJSONArray("aka");
            JSONArray directors = jsonObject.optJSONArray("directors");
            JSONArray casts = jsonObject.optJSONArray("casts");
            JSONObject images = jsonObject.optJSONObject("images");
            String id = jsonObject.optString("id");
//          //第二层解析

            double average = rating.optDouble("average");
            String poster = images.optString("small");


            StringBuilder dirs = new StringBuilder();
            StringBuilder acts = new StringBuilder();

            StringBuilder tags = new StringBuilder();
            for (int j = 0; j < casts.length(); j++) {
                JSONObject castsJSONObject = casts.getJSONObject(j);
                String name = castsJSONObject.optString("name");
                acts.append(name+" ");
            }
            for (int j = 0; j < directors.length(); j++){
                JSONObject dirJSONObject = directors.getJSONObject(j);
                String name = dirJSONObject.optString("name");
                dirs.append(name+" ");
            }

            for (int j=0;j<genres.length();j++){
            tags.append(genres.get(j)+" ");
            }
            SingleHotMovie singlehotMovie = new SingleHotMovie(title, poster, acts.toString(), dirs.toString(), average, id,tags.toString());
            return singlehotMovie;
    }


    public static ArrayList<SingleHotMovie> initMovie(String movieIDList){
        Handler handler;
        ArrayList<SingleHotMovie> singleHotMovie = new ArrayList<>();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StringBuilder stringBuilder =(StringBuilder) msg.obj;
                try {
                    if (stringBuilder!=null && getUserMovie(stringBuilder)!=null){
                        Log.e("现在的list大小：","不为空");
                        singleHotMovie.add(getUserMovie(stringBuilder));
                    } else {
                        Log.e("现在的list：","为空");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        HttpGetDetailData.getPath(handler, "http://api.douban.com/v2/movie/subject/"+movieIDList);

        return singleHotMovie;
    }


    public static List<SingleHotMovie> initUserMovie(List<String> stringList){

        List<SingleHotMovie> singleHotMovieArrayList= new ArrayList<>();

        for(int i=0; i<stringList.size(); i++){
            Log.e("电影的ID",stringList.get(i));
            if(initMovie(stringList.get(i)).size()>0&&initMovie(stringList.get(i)).get(i)!=null) {
                singleHotMovieArrayList.add(initMovie(stringList.get(i)).get(i));
            }
        }

        Log.e("用户的电影数目", Integer.toString(stringList.size()));
        Log.e("用户的电影数目", Integer.toString(singleHotMovieArrayList.size()));
        return singleHotMovieArrayList;
    }


    public static String deleteCharString0(String sourceString, char chElemData) {
        String deleteString = "";
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != chElemData) {
                deleteString += sourceString.charAt(i);
            }
        }
        return deleteString;
    }
}
