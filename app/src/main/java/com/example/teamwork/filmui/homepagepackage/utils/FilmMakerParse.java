package com.example.teamwork.filmui.homepagepackage.utils;


import com.example.teamwork.filmui.homepagepackage.beans.FilmMakerBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dongtianming on 2018/8/3.
 */

public class FilmMakerParse {
    public static FilmMakerBean getFilmMakerBean(StringBuilder stringBuilder){
        FilmMakerBean filmMakerBean=new FilmMakerBean();
        try {
            JSONObject jsonObject=new JSONObject(String.valueOf(stringBuilder));
            String mobile_url = jsonObject.optString("mobile_url");
            String name = jsonObject.optString("name");
            String gender = jsonObject.optString("gender");
            String id = jsonObject.optString("id");
            String name_en = jsonObject.optString("name_en");
            String born_place = jsonObject.optString("born_place");
            String alt = jsonObject.optString("alt");
            JSONObject avatars = jsonObject.optJSONObject("avatars");//第二层里面去解析
            JSONArray works = jsonObject.optJSONArray("works");
            JSONArray aka_en = jsonObject.optJSONArray("aka_en");
            JSONArray aka = jsonObject.optJSONArray("aka");
            //封装
            //FilmMakerBean filmMakerBean=new FilmMakerBean();//new对象
            FilmMakerBean.AvatarsBean avatarsBean=new FilmMakerBean.AvatarsBean();//第二层里面去封装
            filmMakerBean.setMobile_url(mobile_url);
            filmMakerBean.setName(name);
            filmMakerBean.setGender(gender);
            filmMakerBean.setId(id);
            filmMakerBean.setName_en(name_en);
            filmMakerBean.setBorn_place(born_place);
            filmMakerBean.setAlt(alt);
            filmMakerBean.setAvatars(avatarsBean);
            filmMakerBean.setWorks(new ArrayList<FilmMakerBean.WorksBean>());//第二层解析
            filmMakerBean.setAka_en(new ArrayList<String>());//第二层解析
            filmMakerBean.setAka(new ArrayList<String>());//第二层解析
            //第二层解析
            String small = avatars.optString("small");
            String large = avatars.optString("large");
            String medium = avatars.optString("medium");
            //第二层封装
            avatarsBean.setSmall(small);
            avatarsBean.setLarge(large);
            avatarsBean.setMedium(medium);
            for (int i=0;i<works.length();i++){
                JSONObject worksJSONObject = works.getJSONObject(i);
                JSONObject subject = worksJSONObject.optJSONObject("subject");//第三层解析
                JSONArray roles = worksJSONObject.optJSONArray("roles");//第三层解析
                //第二层封装
                FilmMakerBean.WorksBean worksBean=new FilmMakerBean.WorksBean();
                FilmMakerBean.WorksBean.SubjectBean subjectBean=new FilmMakerBean.WorksBean.SubjectBean();//第三层封装
                worksBean.setSubject(subjectBean);
                worksBean.setRoles(new ArrayList<String>());//第三层封装
                filmMakerBean.getWorks().add(worksBean);
                //第三层解析
                String title = subject.optString("title");
                String original_title = subject.optString("original_title");
                String subtype = subject.optString("subtype");
                String year = subject.optString("year");
                String alt1 = subject.optString("alt");
                String id1 = subject.optString("id");
                int collect_count = subject.optInt("collect_count");
                JSONObject rating = subject.optJSONObject("rating");//第四层解析
                JSONObject images = subject.optJSONObject("images");//第四层解析
                JSONArray genres = subject.optJSONArray("genres");//第四层封装
                JSONArray directors = subject.optJSONArray("directors");//第四层封装
                JSONArray casts = subject.optJSONArray("casts");//第四层封装
                //第三层封装
                subjectBean.setTitle(title);
                subjectBean.setOriginal_title(original_title);
                subjectBean.setSubtype(subtype);
                subjectBean.setYear(year);
                subjectBean.setAlt(alt1);
                subjectBean.setId(id1);
                subjectBean.setCollect_count(collect_count);
                FilmMakerBean.WorksBean.SubjectBean.RatingBean ratingBean=new FilmMakerBean.WorksBean.SubjectBean.RatingBean();//第四次对象封装
                FilmMakerBean.WorksBean.SubjectBean.ImagesBean imagesBean=new FilmMakerBean.WorksBean.SubjectBean.ImagesBean();//第四次对象封装
                subjectBean.setRating(ratingBean);
                subjectBean.setImages(imagesBean);
                subjectBean.setCasts(new ArrayList<FilmMakerBean.WorksBean.SubjectBean.CastsBean>());//第四次封装
                subjectBean.setGenres(new ArrayList<String>());//第四次封装
                subjectBean.setDirectors(new ArrayList<Object>());//第四次封装
                //第三层数组解析
                for (int j=0;j<=roles.length();j++){
                    String optString = roles.optString(j);
                    //第三层数组封装
                    worksBean.getRoles().add(optString);
                }
                //第四层解析
                int max = rating.optInt("max");
                int min = rating.optInt("min");
                double average = rating.optDouble("average");
                String stars = rating.optString("stars");
                //第四层封装
                ratingBean.setMax(max);
                ratingBean.setMin(min);
                ratingBean.setAverage(average);
                ratingBean.setStars(stars);
                //第四层解析
                String small1 = images.optString("small");
                String large1 = images.optString("large");
                String medium1 = images.optString("medium");
                //第四层封装
                imagesBean.setLarge(small1);
                imagesBean.setMedium(medium1);
                imagesBean.setLarge(large1);
                //第四层数组解析
                for (int j=0;j<genres.length();j++){
                    String string = genres.optString(j);
                    //第四层数组封装
                    subjectBean.getGenres().add(string);
                }
                //第四层数组解析
                for (int j=0;j<directors.length();j++){
                }
                //第四层数组解析
                for (int j=0;j<casts.length();j++){
                    JSONObject jsonObject1 = casts.optJSONObject(j);
                    String alt2 = jsonObject1.optString("alt");
                    String name2 = jsonObject1.optString("name");
                    String id2 = jsonObject1.optString("id");
                    JSONObject avatars2 = jsonObject1.optJSONObject("avatars");//第五层解析
                    //封装
                    FilmMakerBean.WorksBean.SubjectBean.CastsBean castsBean=new FilmMakerBean.WorksBean.SubjectBean.CastsBean();
                    castsBean.setAlt(alt2);
                    castsBean.setId(id2);
                    castsBean.setName(name2);
                    FilmMakerBean.WorksBean.SubjectBean.CastsBean.AvatarsBeanX avatarsBeanX=new FilmMakerBean.WorksBean.SubjectBean.CastsBean.AvatarsBeanX();//第五层封装
                    castsBean.setAvatars(avatarsBeanX);
                    subjectBean.getCasts().add(castsBean);
                    //第五层解析
//                    String small2 = avatars2.optString("small");
//                    String large2 = avatars2.optString("large");
//                    String medium2 = avatars2.optString("medium");
                    //第五层封装
//                    avatarsBeanX.setLarge(large2);
//                    avatarsBeanX.setMedium(medium2);
  //                  avatarsBeanX.setSmall(small2);
                }
            }
            for (int i=0;i<aka_en.length();i++){
                String optString = aka_en.optString(i);
                //第二层封装
                filmMakerBean.getAka_en().add(optString);
            }
            for (int i=0;i<aka.length();i++){
                String optString = aka.optString(i);
                //第二层封装
                filmMakerBean.getAka().add(optString);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return filmMakerBean;
    }
}
