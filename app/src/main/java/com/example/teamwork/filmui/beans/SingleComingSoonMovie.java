package com.example.teamwork.filmui.beans;

/**
 * “即将上映”电影类
 */
public class SingleComingSoonMovie {

    private String imageId;
    private String title;
    private String actors;
    private String directors;
    private int collectcount;

    public SingleComingSoonMovie(String title, String imageId, String actors, String directors, int collectcount){
        this.title = title;
        this.imageId = imageId;
        this.actors = actors;
        this.directors = directors;
        this.collectcount = collectcount;
    }

    public String getImageId(){
        return imageId;
    }

    public String getTitle(){
        return title;
    }

    public String getActors(){
        return actors;
    }

    public String getDirectors(){
        return directors;
    }

    public int getCollectcount(){
        return collectcount;
    }
}
