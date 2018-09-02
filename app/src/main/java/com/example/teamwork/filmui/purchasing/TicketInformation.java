package com.example.teamwork.filmui.purchasing;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TicketInformation {
    private String movie_title;
    private String cinema_title;
    private String date_time;
    private String shuxing;
    private String place;
    private String seat_location;
    private String imageUrl;

    public TicketInformation(String movie_title, String cinema_title, String date_time, String shuxing, String place, String seat_location, String imageUrl) {
        this.movie_title = movie_title;
        this.cinema_title = cinema_title;
        this.date_time = date_time;
        this.shuxing = shuxing;
        this.place = place;
        this.seat_location = seat_location;
        this.imageUrl = imageUrl;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getCinema_title() {
        return cinema_title;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getShuxing() {
        return shuxing;
    }

    public String getPlace() {
        return place;
    }

    public String getSeat_location() {
        return seat_location;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
