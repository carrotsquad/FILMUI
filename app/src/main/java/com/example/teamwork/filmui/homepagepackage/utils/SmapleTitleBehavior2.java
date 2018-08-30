package com.example.teamwork.filmui.homepagepackage.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamwork.filmui.R;


/**
 * Created by dongtianming on 2018/8/7.
 */

public class SmapleTitleBehavior2 extends CoordinatorLayout.Behavior<ViewGroup> {
    public static LinearLayout loadll;
    public static Activity activity;
    private int loadChange=0;//load图片的更换
    int id[]={R.drawable.xiala2,R.drawable.xiala3,R.drawable.xiala4,R.drawable.xiala5,R.drawable.xiala6,R.drawable.xiala7};
    int colorid[]={R.color.xiala2,R.color.xiala3,R.color.xiala4,R.color.xiala5,R.color.xiala6,R.color.xiala2,R.color.xiala7};
    int lastPs;
    int deltay1;
    private float deltaY;//标题顶部与recycler的距离
    private boolean isRfresh=false;

    public SmapleTitleBehavior2() {;
    }

    public SmapleTitleBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ViewGroup child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ViewGroup child, View dependency) {
        TextView textView= (TextView) loadll.getChildAt(0);
        ImageView imageView= (ImageView) loadll.getChildAt(1);
        ImageView imageView1= (ImageView) loadll.getChildAt(3);
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
            deltay1= (int) (dependency.getY()-deltaY);
            lastPs=deltay1;
        }
       /* float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        System.out.println("666666666666666666666666666666666距离dy"+dy+"  "+"getY"+dependency.getY()+"   "+"getHeight"+child.getHeight());
        float y = -(dy / deltaY) * child.getHeight();
        System.out.println("666666666666666666666666666666666距离y"+y+"  "+"getY"+dependency.getY()+"   "+"getHeight"+child.getHeight());*/
        float y=dependency.getY()-deltaY;
        if (y<0){
            y=0;
        }
        View view=view=child.getChildAt(0);;
        if (y>14){
            isRfresh=true;
            imageView1.setAlpha(1.0f);
            view.setAlpha(0);
           // view.setEnabled(false);
        }
        if ((y<=14)&&(isRfresh==true)){
            isRfresh=false;
            imageView1.setAlpha(0.0f);
            view.setAlpha(1);
           // view.setEnabled(true);
            ObjectAnimator animator=ObjectAnimator.ofFloat(view,"translationX",child.getWidth(),0);
            animator.setDuration(200);
            animator.start();
        }
        child.setTranslationY(-y);
        float alpha = (1/ y)*20;
        child.setAlpha(alpha);
        int avaflag= (int) (deltay1/6);//平均距离
        if (lastPs-y>=avaflag){
            lastPs= (int) y;
            imageView.setBackgroundResource(id[loadChange]);
            textView.setTextColor(activity.getColor(colorid[loadChange]));
            loadChange++;
            if (loadChange>=5){
                loadChange=5;
            }
        }
        if(y-lastPs>=avaflag){
            lastPs= (int) y;
            imageView.setBackgroundResource(id[loadChange]);
            textView.setTextColor(activity.getColor(colorid[loadChange]));
            loadChange--;
            if (loadChange<=0){
                loadChange=0;
            }
        }
        return true;
    }
}
