//package com.example.teamwork.filmui.theatrepagepackage.utils;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Handler;
//import android.os.Message;
//import android.text.TextUtils;
//
//import com.baidu.mapapi.http.HttpClient;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Map;
//
//import static com.example.teamwork.filmui.UriStorage.alreadydelete_uri;
//import static com.example.teamwork.filmui.UriStorage.alreadypush_uri;
//import static com.example.teamwork.filmui.UriStorage.wannadelete_uri;
//import static com.example.teamwork.filmui.UriStorage.wannapush_uri;
//
//
//public class PushMovieData {
//    /**
//     * Function  :   发送Post请求到服务器
//     * Param     :   params请求体内容，encode编码格式
//     */
//    public static String submitPostData(String strUrlPath, String username, String MovieID) throws IOException {
//
//        String data_2 = "";
//
//        //获得请求体
//        if(strUrlPath == wannapush_uri || strUrlPath == wannadelete_uri){
//            data_2 = "username=" + URLEncoder.encode(username, "UTF-8") + "&want=" + URLEncoder.encode(MovieID, "UTF-8");
//        }
//        if(strUrlPath == alreadypush_uri || strUrlPath == alreadydelete_uri) {
//            data_2 = "username=" + URLEncoder.encode(username, "UTF-8") + "&watched=" + URLEncoder.encode(MovieID, "UTF-8");
//        }
//
//        //String urlPath = "http://192.168.1.9:80/JJKSms/RecSms.php";
//        URL url = new URL(strUrlPath);
//
//        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//        httpURLConnection.setConnectTimeout(5000);     //设置连接超时时间
//        httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
//        httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
//        httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
//        httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
//        //至少要设置的两个请求头//
//        httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//        httpURLConnection.setRequestProperty("Content-Length", data_2.length()+"");
//        //获得输出流，向服务器写入数据
//        OutputStream outputStream = httpURLConnection.getOutputStream();
//        outputStream.write(data_2.getBytes());
//
//        int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
//        if(response == HttpURLConnection.HTTP_OK) {
//            InputStream inptStream = httpURLConnection.getInputStream();
//            return dealResponseResult_2(inptStream);                     //处理服务器的响应结果
//        }
//        return "false";
//    }
//
//
//    /**
//     * Function  :   处理服务器的响应结果（将输入流转化成字符串）
//     * Param     :   inputStream服务器的响应输入流
//     */
//    public static String dealResponseResult_2(InputStream inputStream) throws IOException {
//        //存储处理结果
//        String resultData = null;
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        byte[] data = new byte[1024];
//        int len = 0;
//        while((len = inputStream.read(data)) != -1) {
//            byteArrayOutputStream.write(data, 0, len);
//        }
//        resultData = new String(byteArrayOutputStream.toByteArray());
//        return resultData;
//    }
//}
