package com.example.teamwork.filmui.purchasing;

import com.avos.avoscloud.AVObject;

import java.io.Serializable;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Match implements Serializable {

    private String startTime;
    private String endTime;
    private String shuxing;
    private String place;
    private String price;
    boolean isSold[][];
    float all_price; //总价
    String date;

    public Match(String startTime, String endTime, String shuxing, String place, String price, boolean isSold[][], float all_price,String data) {
        this.endTime = endTime;
        this.place = place;
        this.price = price;
        this.shuxing = shuxing;
        this.startTime = startTime;
        this.all_price = all_price;
        this.isSold = isSold;
        this.date=data;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPlace() {
        return place;
    }

    public String getPrice() {
        return price;
    }

    public String getShuxing() {
        return shuxing;
    }

    public String getStartTime() {
        return startTime;
    }

    public boolean[][] getIsSold() {
        return isSold;
    }

    public float getAll_price() {
        return all_price;
    }
public String getDate(){
        return date;
    }
}
