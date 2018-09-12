package com.example.teamwork.filmui.theatrepagepackage.beans;

public class SingleHotMovie {

    private String imageId;
    private String title;
    private String actors;
    private String directors;
    private String commit;
    private String id;
    private String tags;

    public SingleHotMovie(String title, String imageId, String actors, String directors, String commit,String id,String tags){
        this.tags = tags;
        this.title = title;
        this.imageId = imageId;
        this.actors = actors;
        this.directors = directors;
        this.commit = commit;
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

    public String getCommit(){
        return commit;
    }

    public String getId(){return id;}

    public String getTags() {
        return tags;
    }
}
