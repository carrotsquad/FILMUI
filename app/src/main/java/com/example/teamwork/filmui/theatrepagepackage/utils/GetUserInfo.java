package com.example.teamwork.filmui.theatrepagepackage.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GetUserInfo {


    /**
     * Function  :   发送Post请求到服务器
     * Param     :   params请求体内容，encode编码格式
     */
    public static ArrayList<String> getUserInfo(String strUrlPath, String username) throws IOException {

        //获得请求体
        String data ="username=" + URLEncoder.encode(username, "UTF-8");

        //String urlPath = "http://192.168.1.9:80/JJKSms/RecSms.php";
        URL url = new URL(strUrlPath);

        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setConnectTimeout(5000);     //设置连接超时时间
        httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
        httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
        httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
        httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
        //至少要设置的两个请求头//
        httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", data.length()+"");
        //获得输出流，向服务器写入数据
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(data.getBytes());

        int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
        if(response == HttpURLConnection.HTTP_OK) {
            InputStream inptStream = httpURLConnection.getInputStream();
            return dealResponseResult(inptStream);                     //处理服务器的响应结果
        }
        return new ArrayList<>();
    }


    /**
     * Function  :   处理服务器的响应结果（将输入流转化成字符串）
     * Param     :   inputStream服务器的响应输入流
     */
    public static ArrayList dealResponseResult(InputStream inputStream) throws IOException {

        ArrayList<String> movielist=new ArrayList<>();

        //存储处理结果
        String resultData = null;
        String resultData_1 = null;
        String[] resultData_2=new String[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        while((len = inputStream.read(data)) != -1) {
            byteArrayOutputStream.write(data, 0, len);
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        if(resultData.length()<=2){
            return movielist;
        }
        resultData_1=resultData.substring(1,resultData.length()-1);
        resultData_2=resultData_1.split(",");
        for(int i=0; i<resultData_2.length; i++){
            movielist.add(resultData_2[i]);
        }
        System.out.println(movielist);

        Log.e("用户的电影",Integer.toString(movielist.size()));
        return movielist;
    }

}
