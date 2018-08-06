package com.example.teamwork.filmui.beans;

public class SingleCast {

    private String avatar;
    private String name;
    private String id;
    private String summary;
    private String aka;
    private String countries;
    private int collect_count;
    private int wish_count;

    public SingleCast(String name, String avatar, String id,String summary, String aka,String countries, int wish_count, int collect_count) {
        this.summary = summary;
        this.aka = aka;
        this.countries = countries;
        this.collect_count = collect_count;
        this.wish_count = wish_count;
        this.name = name;
        this.avatar = avatar;
        this.id = id;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public String getAka() {
        return aka;
    }

    public String getCountries() {
        return countries;
    }

    public String getSummary() {
        return summary;
    }

    public String getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }
}
