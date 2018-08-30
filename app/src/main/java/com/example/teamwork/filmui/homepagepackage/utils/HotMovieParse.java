package com.example.teamwork.filmui.homepagepackage.utils;

import com.example.teamwork.filmui.homepagepackage.beans.HotMoiveBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dongtianming on 2018/7/29.
 */

public class HotMovieParse {
    public static HotMoiveBean getHotMoiveBean(StringBuilder stringBuilder) {
        HotMoiveBean hotMoiveBean = new HotMoiveBean();
        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(stringBuilder));
            //第一层解析
            int count = jsonObject.optInt("count");
            int start = jsonObject.optInt("start");
            int total = jsonObject.optInt("total");
            JSONArray subjects = jsonObject.optJSONArray("subjects");
            String title = jsonObject.optString("title");
            //第一层javaBean对象
            hotMoiveBean.setCount(count);
            hotMoiveBean.setTitle(title);
            hotMoiveBean.setStart(start);
            hotMoiveBean.setTotal(total);
            hotMoiveBean.setSubjects(new ArrayList<HotMoiveBean.SubjectsBean>());
            //第二层解析
            for (int i = 0; i < subjects.length(); i++) {
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
                    HotMoiveBean.SubjectsBean subjectsBean = new HotMoiveBean.SubjectsBean();
                    subjectsBean.setTitle(title1);
                    subjectsBean.setAlt(alt);
                    subjectsBean.setCasts(new ArrayList<HotMoiveBean.SubjectsBean.CastsBean>());
                    subjectsBean.setCollect_count(collect_count);
                    subjectsBean.setDirectors(new ArrayList<HotMoiveBean.SubjectsBean.DirectorsBean>());
                    subjectsBean.setId(id);
                    subjectsBean.setGenres(new ArrayList<String>());
                    HotMoiveBean.SubjectsBean.ImagesBean imagesBean = new HotMoiveBean.SubjectsBean.ImagesBean();
                    subjectsBean.setImages(imagesBean);
                    subjectsBean.setOriginal_title(original_title);
                    HotMoiveBean.SubjectsBean.RatingBean ratingBean = new HotMoiveBean.SubjectsBean.RatingBean();
                    subjectsBean.setRating(ratingBean);
                    subjectsBean.setSubtype(subtype);
                    subjectsBean.setYear(year);
                    hotMoiveBean.getSubjects().add(subjectsBean);
                    //第三层解析
                    int max = rating.optInt("max");
                    int min = rating.optInt("min");
                    double average = rating.optDouble("average");
                    String stars = rating.optString("stars");
                    //封装
                    ratingBean.setMax(max);
                    ratingBean.setAverage(average);
                    ratingBean.setMin(min);
                    ratingBean.setStars(stars);
                    String small = images.optString("small");
                    String large = images.optString("large");
                    String medium = images.optString("medium");
                    imagesBean.setLarge(large);
                    imagesBean.setMedium(medium);
                    imagesBean.setSmall(small);
                    for (int j = 0; j < casts.length(); j++) {
                        JSONObject castsJSONObject = casts.getJSONObject(j);
                        String alt1 = castsJSONObject.optString("alt");
                        String name = castsJSONObject.optString("name");
                        String id1 = castsJSONObject.optString("id");
                        JSONObject avatars = castsJSONObject.optJSONObject("avatars");
                        //封装
                        HotMoiveBean.SubjectsBean.CastsBean castsBean = new HotMoiveBean.SubjectsBean.CastsBean();
                        castsBean.setName(name);
                        castsBean.setAlt(alt1);
                        castsBean.setId(id1);
                        HotMoiveBean.SubjectsBean.CastsBean.AvatarsBean avatarsBean = new HotMoiveBean.SubjectsBean.CastsBean.AvatarsBean();
                        castsBean.setAvatars(avatarsBean);
                        subjectsBean.getCasts().add(castsBean);
                        //解析
//                        String small1 = avatars.optString("small");
                        //                       String large1 = avatars.optString("large");
                        //                      String medium1 = avatars.optString("medium");
                        //封装
                        //                     avatarsBean.setSmall(small1);
                        //                     avatarsBean.setMedium(medium1);
                        //                    avatarsBean.setLarge(large1);
                    }
                    for (int j = 0; j < genres.length(); j++) {
                        String s = genres.optString(j);
                        subjectsBean.getGenres().add(s);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.d("666666666", hotMoiveBean.getSubjects().get(0).getImages().getLarge());
 //       Log.d("666666666", hotMoiveBean.getSubjects().get(0).getAlt());
 //       Log.d("666666666", hotMoiveBean.getSubjects().get(0).getTitle());
  //     Log.d("666666666", hotMoiveBean.getTitle());
 //       Log.d("666666666", hotMoiveBean.getSubjects().get(0).getSubtype());
  //      Log.d("666666666", hotMoiveBean.getSubjects().get(0).getOriginal_title());
 //       Log.d("666666666", String.valueOf(hotMoiveBean.getSubjects().get(0).getRating().getStars()));
  //      Log.d("666666666", hotMoiveBean.getSubjects().get(0).getYear());
  //      Log.d("666666666", String.valueOf(hotMoiveBean.getSubjects().get(0).getCollect_count()));
   //     Log.d("666666666", hotMoiveBean.getSubjects().get(0).getCasts().get(0).getName());
   //     Log.d("666666666", hotMoiveBean.getSubjects().get(0).getCasts().get(0).getId());
        return hotMoiveBean;
    }
}
