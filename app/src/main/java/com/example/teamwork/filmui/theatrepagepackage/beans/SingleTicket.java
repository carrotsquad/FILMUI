package com.example.teamwork.filmui.theatrepagepackage.beans;

public class SingleTicket {

    private String movie_title;
    private String cinema_title;
    private String date_time;
    private String shuxing;
    private String place;
    private String seat_location;
    private String imageUrl;
    private String username;
    private String num;

    public void setCinema_title(String cinema_title) {
        this.cinema_title = cinema_title;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setSeat_location(String seat_location) {
        this.seat_location = seat_location;
    }

    public void setShuxing(String shuxing) {
        this.shuxing = shuxing;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCinema_title() {
        return cinema_title;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getNum() {
        return num;
    }

    public String getPlace() {
        return place;
    }

    public String getSeat_location() {
        return seat_location;
    }

    public String getShuxing() {
        return shuxing;
    }

    public String getUsername() {
        return username;
    }
}
