package com.example.teamwork.filmui.theatrepagepackage.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.teamwork.filmui.UriStorage.ticketpush_uri;


public class PushUserTickets {

    /**
     * Function  :   发送Post请求到服务器
     * Param     :   params请求体内容，encode编码格式
     */
    public static String pushUserTickets(String movie_title, String cinema_title, String date_time, String shuxing, String place, String seat_location, String imageUrl, String username, String num) throws IOException {

        String req="movie_title="+URLEncoder.encode(movie_title, "UTF-8")+"&cinema_title="+URLEncoder.encode(cinema_title, "UTF-8")
                +"&date_time="+URLEncoder.encode(date_time, "UTF-8")+"&shuxing="+URLEncoder.encode(shuxing, "UTF-8")
                +"&place="+URLEncoder.encode(place, "UTF-8")+"&seat_location="+URLEncoder.encode(seat_location, "UTF-8")
                +"&imageUrl="+URLEncoder.encode(imageUrl, "UTF-8") +"&username="+URLEncoder.encode(username, "UTF-8")
                +"&num="+URLEncoder.encode(num, "UTF-8");

        //String urlPath = "http://192.168.1.9:80/JJKSms/RecSms.php";
        URL url_2 = new URL(ticketpush_uri);

        HttpURLConnection httpURLConnection = (HttpURLConnection)url_2.openConnection();
        httpURLConnection.setConnectTimeout(5000);     //设置连接超时时间
        httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
        httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
        httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
        httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
        //至少要设置的两个请求头//
        httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", req.length()+"");
        //获得输出流，向服务器写入数据
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(req.getBytes());

        int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
        if(response == HttpURLConnection.HTTP_OK) {
            InputStream inptStream = httpURLConnection.getInputStream();
            return ResponseResult_2(inptStream);                     //处理服务器的响应结果
        }
        return "false";
    }


    /**
     * Function  :   处理服务器的响应结果（将输入流转化成字符串）
     * Param     :   inputStream服务器的响应输入流
     */
    public static String ResponseResult_2(InputStream inputStream) throws IOException {

        //存储处理结果
        String resultData = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        while((len = inputStream.read(data)) != -1) {
            byteArrayOutputStream.write(data, 0, len);
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;

    }
}
