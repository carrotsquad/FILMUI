package com.example.teamwork.filmui.homepagepackage.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by dongtianming on 2018/7/29.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViewList;
    public MyViewPagerAdapter(List<ImageView> imageViewList) {
        this.imageViewList=imageViewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //计算新的位置取余
        int newPosition=position%imageViewList.size();
        //1.将图片取出来
        ImageView imageView=imageViewList.get(newPosition);
        //2.将图片加到ViewPager容器里去
        container.addView(imageView);
        //3.返回图片
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
