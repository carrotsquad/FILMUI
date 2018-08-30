package com.example.teamwork.filmui.homepagepackage.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dongtianming on 2018/8/1.
 */

public class GetImageFromCache {
    public static void setImageView(final String path, ImageView imageView, final Activity activity){
        final String[] paths=path.split("/");
        File file=new File(activity.getCacheDir().getAbsolutePath().toString(),paths[paths.length-1]);
        if (file.exists()){
            Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }
        else {
            GetImageFromInternet.setImageView(path,imageView,activity);
            new Thread(){
                @Override
                public void run() {
                    URL url= null;
                    try {
                        url = new URL(path);
                        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(5000);
                        int code=httpURLConnection.getResponseCode();
                        if (code==200){
                            File file1=new File(activity.getCacheDir().getAbsolutePath().toString(),paths[paths.length-1]);
                            FileOutputStream fileOutputStream=new FileOutputStream(file1);
                            InputStream inputStream=httpURLConnection.getInputStream();
                            byte []buffer=new byte[1024];
                            int len=-1;
                            while ((len=inputStream.read(buffer))!=-1){
                                fileOutputStream.write(buffer,0,len);
                            }
                            fileOutputStream.close();
                            inputStream.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
