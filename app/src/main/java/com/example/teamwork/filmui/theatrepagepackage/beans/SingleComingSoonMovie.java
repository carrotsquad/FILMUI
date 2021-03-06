package com.example.teamwork.filmui.theatrepagepackage.beans;

/**
 * “即将上映”电影类
 */
public class SingleComingSoonMovie {

    private String imageId;
    private String title;
    private String actors;
    private String directors;
    private String wishcount;
    private String id;
    private String tags;

    public SingleComingSoonMovie(String title, String imageId, String actors, String directors, String wishcount,String id,String tags){
        this.title = title;
        this.tags = tags;
        this.imageId = imageId;
        this.actors = actors;
        this.directors = directors;
        this.wishcount = wishcount;
        this.id = id;
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

    public String getWishcount() {
        return wishcount;
    }

    public String getId(){return id;}

    public String getTags() {
        return tags;
    }
}
