package com.example.teamwork.filmui;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class BuyActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"0wgDPsak0SgSff40LjboRC2y-gzGzoHsz","I3lwqwL5bt4O6gmcdozBhzL7");
    }
}
