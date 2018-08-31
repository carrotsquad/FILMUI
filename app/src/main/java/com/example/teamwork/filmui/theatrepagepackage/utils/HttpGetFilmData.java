package com.example.teamwork.filmui.theatrepagepackage.utils;

import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetFilmData {

    private static StringBuilder stringBuilder;
    public static void getStringBuilder(final Handler handler, final String path){
        new Thread(){
            @Override
            public void run() {
                try {
                    String string="city=重庆";
                    URL url=new URL(path.toString().trim()+string);
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
                    Message msg=Message.obtain();
                    msg.obj=stringBuilder;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    Message msg=Message.obtain();
                    msg.obj=null;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
