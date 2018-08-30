package com.example.teamwork.filmui.homepagepackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dongtianming on 2018/8/12.
 */

public class IndexView extends View {
    private OnIndexChangeListener onIndexChangeListener;

    public void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.onIndexChangeListener = onIndexChangeListener;
    }

    private int touchIndex=-1;
    private int itemWidth;
    private int itemHeight;
    private int wordWidth;
    private int wordHeight;
    private Context mContext;
    private Paint paint;
    public static String[] word = { "定位", "热门","A", "B", "C", "D", "E", "F", "G", "H", "J", "K",
            "L", "M", "N", "P", "Q", "R", "S", "T", "W", "X", "Y", "Z"};
    public IndexView(Context context) {
        super(context);
        this.mContext=context;
        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    public IndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IndexView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext=context;
        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemHeight=getMeasuredHeight()/word.length;
        itemWidth=getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //字是算的左下角的坐标
        //paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextSize(30);
        for (int i=0;i<word.length;i++){
            if (touchIndex==i){
                paint.setColor(Color.GRAY);
            }
            else {
                paint.setColor(Color.parseColor("#598cd4"));
            }
            String itemWord=word[i];
            Rect rect=new Rect();
            paint.getTextBounds(itemWord,0,itemWord.length(),rect);
            wordHeight=rect.height();
            wordWidth=rect.width();
            float wordX=itemWidth/2-wordWidth/2;
            float wordY=itemHeight/2+wordHeight/2+i*itemHeight;
            canvas.drawText(itemWord,wordX,wordY,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y=event.getY();
               int index= (int) (y/itemHeight);
                if (touchIndex!=index){
                    touchIndex=index;
                    invalidate();//重绘
                    if (onIndexChangeListener!=null&&touchIndex<word.length){
                        onIndexChangeListener.indexChange(word[touchIndex]);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                touchIndex=-1;
                invalidate();
                break;
        }
        return true;
    }
    public interface OnIndexChangeListener{
        void indexChange(String word);
    }
}
