package com.example.teamwork.filmui.purchasing;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DataProducer {
    public void initRecycleView() {

    }
    public static void  putDataInbackground(String cinemaTitle,String movieTitle,int j,String poster,MyCallBack<Integer> callBack){
        for (int i=0;i<21;i+=2){
            String cost = String.valueOf(25+new Random().nextInt(20));
            Match match = new Match(i+":30",(i+2)+":30","国语3d","3号厅",cost,new boolean[10][15],0,getOldDate(j));
            AVObject avObject = new AVObject("Match");
            avObject.put("movieTitle",movieTitle);
            avObject.put("cinemaTitle",cinemaTitle);
            avObject.put("start_time",match.getStartTime());
            avObject.put("mSoldAndCheck", new Gson().toJson(new SoldAndCheck()));
            avObject.put("end_time",match.getEndTime());
            avObject.put("shuxing",match.getShuxing());
            avObject.put("date",match.getDate());
            avObject.put("place",match.getPlace());
            avObject.put("price",match.getPrice());
            avObject.put("poster",poster);
            final int finalJ = j;
            final int finalI = i;
            avObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e==null){
                        callBack.onSuccess(j);
                    }
                }
            });
        }}



    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);}
    public static  void downLoadData(String movieTitle,String cinemaTitle,int i,final MyCallBack<List<AVObject>> callBack){

        AVQuery<AVObject> query = new AVQuery<>("Match");
        query.whereEqualTo("date", DataProducer.getOldDate(i));
        query.whereEqualTo("movieTitle",movieTitle);
        query.whereEqualTo("cinemaTitle",cinemaTitle);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    callBack.onSuccess(list);
                }
            }
        });

    }}
