package com.example.teamwork.filmui.utils;

import android.util.Log;

import com.example.teamwork.filmui.beans.BoxOfficeBean;
import com.example.teamwork.filmui.beans.SingleBoxOffice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BoxOfficeParse {

    public static List<SingleBoxOffice> getSingleBoxOfficeList(StringBuilder stringBuilder){
        List<SingleBoxOffice> singleBoxOfficeList = new ArrayList<>();
        BoxOfficeBean boxOfficeBean = new BoxOfficeBean();
        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(stringBuilder));
            /* 第一层解析 */
            int error_code = jsonObject.optInt("error_code");
            String reason = jsonObject.optString("reason");
            JSONArray datas = jsonObject.getJSONArray("data");
            for(int i = 0;i < datas.length(); i++){
                /* 第二层解析 */
                SingleBoxOffice singleBoxOffice = null;
                JSONObject jsonObject1 = datas.getJSONObject(i);
                if (jsonObject1!=null){
                    int Irank = jsonObject1.getInt("Irank");
                    String  MovieName = jsonObject1.getString("MovieName");
                    Double BoxOffice = jsonObject1.getDouble("BoxOffice");
                    Double sumBoxOffice = jsonObject1.getDouble("sumBoxOffice");
                    int movieDay = jsonObject1.getInt("movieDay");
                    Double boxPer = jsonObject1.getDouble("boxPer");
                    String time = jsonObject1.getString("time");
                    singleBoxOffice = new SingleBoxOffice(Irank,MovieName,BoxOffice,sumBoxOffice,movieDay,boxPer,time);
                }
                singleBoxOfficeList.add(singleBoxOffice);
                Log.d("在里面的list大小：",String.valueOf(singleBoxOfficeList.size()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return singleBoxOfficeList;
    }


}
