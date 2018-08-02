package com.example.teamwork.filmui.beans;

//电影信息类
public class FilmCon {

    private int imageId;
    private String name;
    private String actors;
    private String directors;
    private double commit;
    private long date;
    private long desire;

    public FilmCon(String name, int imageId, String actors, String directors, double commit,long date, long desire){
        this.name = name;
        this.imageId = imageId;
        this.actors = actors;
        this.directors = directors;
        this.commit = commit;
        this.date = date;
        this.desire = desire;
    }

    public int getImageId(){
        return imageId;
    }

    public String getName(){
        return name;
    }

    public String getActors(){
        return actors;
    }

    public String getDirectors(){
        return directors;
    }

    public double getCommit(){
        return commit;
    }

    public long getDate(){
        return date;
    }

    public long getDesire(){
        return desire;
    }
}
