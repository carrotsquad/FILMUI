package com.example.teamwork.filmui;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor zhangqianyuan
 * @updateDes 我把这个活动单独拿出来是因为，要连接服务器，必须这样
 */
public class BuyActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"0wgDPsak0SgSff40LjboRC2y-gzGzoHsz","I3lwqwL5bt4O6gmcdozBhzL7");
    }
}
