package com.example.teamwork.filmui.homepagepackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.teamwork.filmui.R;

import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class SearchViewActivity extends AppCompatActivity {

    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        searchView= (SearchView) findViewById(R.id.search_view);
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
    }
}
