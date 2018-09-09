package com.example.teamwork.filmui.minepagepackage.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.minepagepackage.Fragment.TopFragment;


public class WebActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.top,new TopFragment());
        transaction.commit();

        WebView webView = (WebView)findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
    }
}
