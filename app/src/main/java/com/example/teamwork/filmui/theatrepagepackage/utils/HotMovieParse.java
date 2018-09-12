package com.example.teamwork.filmui.theatrepagepackage.utils;

import android.util.Log;

import com.example.teamwork.filmui.theatrepagepackage.beans.HotMovieBean;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleHotMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotMovieParse {

    public static List<SingleHotMovie> getHotMoiveBean(StringBuilder stringBuilder) {
        List<SingleHotMovie> singleHotMovies = new ArrayList<>();
        HotMovieBean HotMovieBean = new HotMovieBean();
        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(stringBuilder));
            //第一层解析
            int count = jsonObject.optInt("count");
            int start = jsonObject.optInt("start");
            int total = jsonObject.optInt("total");
            JSONArray subjects = jsonObject.optJSONArray("subjects");
//            Log.d("subjects的大小：",String.valueOf(subjects.length()));
            String title = jsonObject.optString("title");
            //第一层javaBean对象
            HotMovieBean.setCount(count);
            HotMovieBean.setTitle(title);
            HotMovieBean.setStart(start);
            HotMovieBean.setTotal(total);
            HotMovieBean.setSubjects(new ArrayList<HotMovieBean.SubjectsBean>());
            //第二层解析
            for (int i = 0; i < subjects.length(); i++) {
                SingleHotMovie singleHotMovie = null;
                JSONObject jsonObject1 = subjects.getJSONObject(i);
                if (jsonObject1 != null) {
                    JSONObject rating = jsonObject1.optJSONObject("rating");
                    JSONArray genres = jsonObject1.optJSONArray("genres");
                    String title1 = jsonObject1.optString("title");
                    JSONArray casts = jsonObject1.optJSONArray("casts");
                    int collect_count = jsonObject1.optInt("collect_count");
                    String original_title = jsonObject1.optString("original_title");
                    String subtype = jsonObject1.optString("subtype");
                    JSONArray directors = jsonObject1.optJSONArray("directors");
                    String year = jsonObject1.optString("year");
                    JSONObject images = jsonObject1.optJSONObject("images");
                    String alt = jsonObject1.optString("alt");
                    String id = jsonObject1.optString("id");
                    //第二层封装javaBean
                    HotMovieBean.SubjectsBean subjectsBean = new HotMovieBean.SubjectsBean();
                    subjectsBean.setTitle(title1);
                    subjectsBean.setAlt(alt);
                    subjectsBean.setCasts(new ArrayList<HotMovieBean.SubjectsBean.CastsBean>());
                    subjectsBean.setCollect_count(collect_count);
                    subjectsBean.setDirectors(new ArrayList<HotMovieBean.SubjectsBean.DirectorsBean>());
                    subjectsBean.setId(id);
                    subjectsBean.setGenres(new ArrayList<String>());
                    HotMovieBean.SubjectsBean.ImagesBean imagesBean = new HotMovieBean.SubjectsBean.ImagesBean();
                    subjectsBean.setImages(imagesBean);
                    subjectsBean.setOriginal_title(original_title);
                    HotMovieBean.SubjectsBean.RatingBean ratingBean = new HotMovieBean.SubjectsBean.RatingBean();
                    subjectsBean.setRating(ratingBean);
                    subjectsBean.setSubtype(subtype);
                    subjectsBean.setYear(year);
                    HotMovieBean.getSubjects().add(subjectsBean);
                    //第三层解析
//                    int max = rating.optInt("max");
//                    int min = rating.optInt("min");
                    double average = rating.optDouble("average");
                    String stars = rating.optString("stars");
                    //封装
//                    ratingBean.setMax(max);
//                    ratingBean.setAverage(average);
//                    ratingBean.setMin(min);
                    ratingBean.setStars(stars);
                    String small = images.optString("small");
                    String large = images.optString("large");
                    String medium = images.optString("medium");
                    imagesBean.setLarge(large);
                    imagesBean.setMedium(medium);
                    imagesBean.setSmall(small);
                    Log.d("网址：",small);
                    StringBuilder dirs = new StringBuilder();
                    StringBuilder acts = new StringBuilder();
                    StringBuilder tags = new StringBuilder();
                    for (int j = 0; j < casts.length(); j++) {
                        JSONObject castsJSONObject = casts.getJSONObject(j);
                        String alt1 = castsJSONObject.optString("alt");
                        String name = castsJSONObject.optString("name");
                        String id1 = castsJSONObject.optString("id");
//                        JSONObject avatars = castsJSONObject.optJSONObject("avatars");
//                        //封装
//                        HotMovieBean.SubjectsBean.CastsBean castsBean = new HotMovieBean.SubjectsBean.CastsBean();
//                        castsBean.setName(name);
//                        castsBean.setAlt(alt1);
//                        castsBean.setId(id1);
//                        HotMovieBean.SubjectsBean.CastsBean.AvatarsBean avatarsBean = new HotMovieBean.SubjectsBean.CastsBean.AvatarsBean();
//                        castsBean.setAvatars(avatarsBean);
//                        subjectsBean.getCasts().add(castsBean);
                        //解析
//                        String small1 = avatars.optString("small");
                        //                       String large1 = avatars.optString("large");
                        //                      String medium1 = avatars.optString("medium");
                        //封装
                        //                     avatarsBean.setSmall(small1);
                        //                     avatarsBean.setMedium(medium1);
                        //                    avatarsBean.setLarge(large1);
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
                    singleHotMovie = new SingleHotMovie(title1,small,acts.toString(),dirs.toString(),Double.toString(average),id,tags.toString());
                }
                singleHotMovies.add(singleHotMovie);
                Log.d("在里面的list大小：",String.valueOf(singleHotMovies.size()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.d("666666666", HotMovieBean.getSubjects().get(0).getImages().getLarge());
        //       Log.d("666666666", HotMovieBean.getSubjects().get(0).getAlt());
        //       Log.d("666666666", HotMovieBean.getSubjects().get(0).getTitle());

        //       Log.d("666666666", HotMovieBean.getSubjects().get(0).getSubtype());
        //      Log.d("666666666", HotMovieBean.getSubjects().get(0).getOriginal_title());


        //      Log.d("666666666", HotMovieBean.getSubjects().get(0).getYear());
        //      Log.d("666666666", String.valueOf(HotMovieBean.getSubjects().get(0).getCollect_count()));
        //     Log.d("666666666", HotMovieBean.getSubjects().get(0).getCasts().get(0).getName());
        //     Log.d("666666666", HotMovieBean.getSubjects().get(0).getCasts().get(0).getId());
        return singleHotMovies;
    }
}
