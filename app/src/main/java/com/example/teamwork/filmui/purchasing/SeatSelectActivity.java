package com.example.teamwork.filmui.purchasing;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;
import com.example.teamwork.filmui.R;
import com.google.gson.Gson;

public class SeatSelectActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    int count = 0;
    float all_price;

    //所有座位的拼接
    String all_position;


    //电影类
    AVObject mAVObject;

    //电影院标题栏
    TextView title;

    //电影title
    TextView movie_title;

    //电影属性
    TextView movie_shuxing;
    ImageView back;


    SoldAndCheck mSoldAndCheck;

    //服务器isSold 数据
    String mIsSold;

    //三个座位位置的 TextView
    TextView[] locations = new TextView[3];
    TextView price_buy;
    PopupWindow mPopupWindow;
    private static final String TAG = "shit";
    public SeatTable seatTableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_test);
        intent = getIntent();
        mIsSold = intent.getStringExtra("mSoldAndCheck");
        mSoldAndCheck = new Gson().fromJson(mIsSold, SoldAndCheck.class);
        initView();

    }

    public void initView() {
        back = findViewById(R.id.back);
        movie_title = findViewById(R.id.movie_title);
        movie_shuxing = findViewById(R.id.match_shuxing);
        title = findViewById(R.id.title);
        seatTableView = findViewById(R.id.seatTable);
        initPopupWindow();
        back.setOnClickListener(this);
        mAVObject = intent.getParcelableExtra("match");
        title.setText(mAVObject.getString("cinemaTitle"));
        movie_title.setText(mAVObject.getString("movieTitle"));
        movie_shuxing.setText(mAVObject.getString("start_time") + "~" + mAVObject.getString("end_time")+" "+mAVObject.getString("shuxing"));
        seatTableView.setScreenName(mAVObject.getString("place") + "荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(3);//设置最多选中
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                return mSoldAndCheck.isSold[row][column];
            }

            @Override
            public void checked(int row, int column) {
                locations[count].setText("第" + (row + 1) + "排" + " " + "第" + (column + 1) + "座");
                mSoldAndCheck.isCheck[row][column] = true;
                count++;
                mPopupWindow.showAtLocation(title, Gravity.BOTTOM, 0, 0);
                price_buy.setText(String.valueOf(count * Integer.valueOf(mAVObject.getString("price")) + "元   确定购票"));
                all_price = (float) (count * Integer.valueOf(mAVObject.getString("price")));
            }

            @Override
            public void unCheck(int row, int column) {
                count--;
                mSoldAndCheck.isCheck[row][column] = false;
                locations[count].setText("");
                price_buy.setText(String.valueOf(count * Integer.valueOf(mAVObject.getString("price")) + "元   确定购票"));
                if (count == 0) {
                    mPopupWindow.dismiss();
                }
                all_price = (float) (count * Integer.valueOf(mAVObject.getString("price")));
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return new String[0];
            }
        });
        seatTableView.setData(10, 15);
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);
        price_buy = view.findViewById(R.id.price_buy);
        locations[0] = view.findViewById(R.id.location1);
        locations[1] = view.findViewById(R.id.location2);
        locations[2] = view.findViewById(R.id.location3);
        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setContentView(view);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(false);
        price_buy.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.price_buy:
                all_position = locations[0].getText() + " ||" + locations[1].getText() + " ||" + locations[2].getText();
                Intent intent = new Intent(SeatSelectActivity.this, OutTicketActivity.class);
                intent.putExtra("mSoldAndCheck", mSoldAndCheck);
                intent.putExtra("match", mAVObject);
                intent.putExtra("all_price", all_price);
                intent.putExtra("first_seat", locations[0].getText());
                intent.putExtra("second_seat", locations[1].getText());
                intent.putExtra("third_seat", locations[2].getText());
                intent.putExtra("position", all_position);
                intent.putExtra("cinema_title", title.getText());
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }


}
