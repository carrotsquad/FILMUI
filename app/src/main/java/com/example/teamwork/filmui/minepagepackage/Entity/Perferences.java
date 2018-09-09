package com.example.teamwork.filmui.minepagepackage.Entity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.File;

public class Perferences {
    private Context context;

    public Perferences(Context context) {
        this.context = context;
    }

    public void sava(String name) {
        SharedPreferences preferences = context.getSharedPreferences("users", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name",name);

        editor.commit();
    }
}
