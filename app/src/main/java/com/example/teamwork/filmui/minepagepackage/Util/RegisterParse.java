package com.example.teamwork.filmui.minepagepackage.Util;

import android.util.Log;

import com.example.teamwork.filmui.minepagepackage.Activity.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterParse {
    private static String REGISTER_URL = "http://47.106.95.140:8080/tpp/register";

    public static String RegisterByPost(String username,String password){
        Log.d(LoginActivity.TAG,"启动登录线程");
        String msg = "";
        try {
            URL url = new URL(REGISTER_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d(LoginActivity.TAG,"22222");

            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            String data = "username="+ URLEncoder.encode(username,"UTF-8")+"&password="+URLEncoder.encode(password,"UTF-8");

            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if (conn.getResponseCode()==200){
                InputStream is = conn.getInputStream();

                ByteArrayOutputStream message = new ByteArrayOutputStream();

                int len = 0;
                byte buffer[] = new byte[1024];
                while((len = is.read(buffer)) != -1){
                    message.write(buffer,0,len);
                }
                is.close();
                message.close();
                msg = new String(message.toByteArray());

                return msg;

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
