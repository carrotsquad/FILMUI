package com.example.teamwork.filmui.beans;

public class TheatreCon {

    private String name;
    private String address;
    private Double distance;

    public TheatreCon(String name, String address, double distance){
        this.name = name;
        this.address = address;
        this.distance = distance;
    }

    public String getTheatreName(){
        return name;
    }

    public String getTheatreAddress(){
        return address;
    }

    public Double getTheatreDistance(){
        return distance;
    }
}
