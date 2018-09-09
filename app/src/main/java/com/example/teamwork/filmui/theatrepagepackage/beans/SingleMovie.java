package com.example.teamwork.filmui.theatrepagepackage.beans;

public class SingleMovie {

    private String imageId;
    private String title;
    private String actors;
    private String directors;
    private double commit;
    private String id;
    private String tags;

    public SingleMovie(String title, String imageId, String actors, String directors, double commit){
        this.title = title;
        this.imageId = imageId;
        this.actors = actors;
        this.directors = directors;
        this.commit = commit;
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

    public double getCommit(){
        return commit;
    }
}
