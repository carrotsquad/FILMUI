package com.example.teamwork.filmui.homepagepackage.utils;

import android.os.Handler;
import android.os.Message;


import com.example.teamwork.filmui.homepagepackage.beans.ComingSoonMovieBean;
import com.example.teamwork.filmui.homepagepackage.beans.FilmMakerBean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongtianming on 2018/8/4.
 */

public class HttpGetJsonData2 {
    static StringBuilder stringBuilder;
    public static void sendMessage(final Handler handler){
        new Thread(){
            @Override
            public void run() {
                try {
                    StringBuilder cm = getStringBuilder("http://api.douban.com/v2/movie/in_theaters");
                    ComingSoonMovieBean comingSoonMovieBean = ComingSoonMovieParse.getComingSoonMovieBean(cm);
                    List<FilmMakerBean> filmMakerBeanList = new ArrayList<FilmMakerBean>();
                    for (int i = 0; i < comingSoonMovieBean.getSubjects().size(); i++) {
                        System.out.println(i+"   "+comingSoonMovieBean.getSubjects().size());
                        if (comingSoonMovieBean.getSubjects().get(i).getCasts().isEmpty()){
                            continue;
                        }
                        StringBuilder stringBuilder1 = getStringBuilder("http://api.douban.com/v2/movie/celebrity/" + comingSoonMovieBean.getSubjects().get(i).getCasts().get(0).getId());
                        FilmMakerBean filmMakerBean = FilmMakerParse.getFilmMakerBean(stringBuilder1);
                        filmMakerBeanList.add(filmMakerBean);
                        System.out.println("test");
                    }
                    Message msg = Message.obtain();
                    msg.obj = filmMakerBeanList;
                    handler.sendMessage(msg);
                }
                catch (Exception e){}
            }
        }.start();
    }
    public static StringBuilder getStringBuilder(String path){
        try {
            URL url=new URL(path.toString().trim());
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            int code=httpURLConnection.getResponseCode();
            if (code==200){
                InputStream inputStream=httpURLConnection.getInputStream();
                stringBuilder= new StringBuilder();
                byte buffer[]=new byte[1024];
                int len=-1;
                while ((len=inputStream.read(buffer))!=-1){
                    stringBuilder.append(new String(buffer,0,len));
                }
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
