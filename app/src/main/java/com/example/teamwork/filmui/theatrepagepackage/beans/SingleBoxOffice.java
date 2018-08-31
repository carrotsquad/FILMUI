package com.example.teamwork.filmui.theatrepagepackage.beans;

/**
 * 票房类，Created by Zqy on 2018/8/2
 */
public class SingleBoxOffice {

    private int Irank;
    private String  MovieName;
    private Double BoxOffice;
    private Double sumBoxOffice;
    private int movieDay;
    private Double boxPer;
    private String time;

    public SingleBoxOffice(int Irank, String MovieName, Double BoxOffice, Double sumBoxOffice, int movieDay, Double boxPer, String time){
        this.Irank = Irank;
        this.MovieName = MovieName;
        this.BoxOffice = BoxOffice;
        this.sumBoxOffice = sumBoxOffice;
        this.movieDay = movieDay;
        this.boxPer = boxPer;
        this.time = time;
    }

    public Double getBoxOffice() {
        return BoxOffice;
    }

    public Double getBoxPer() {
        return boxPer;
    }

    public Double getSumBoxOffice() {
        return sumBoxOffice;
    }

    public int getIrank() {
        return Irank;
    }

    public int getMovieDay() {
        return movieDay;
    }

    public String getMovieName() {
        return MovieName;
    }

    public String getTime() {
        return time;
    }

}
