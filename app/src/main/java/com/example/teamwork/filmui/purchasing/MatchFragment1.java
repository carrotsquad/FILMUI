package com.example.teamwork.filmui.purchasing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVObject;
import com.example.teamwork.filmui.R;

import org.apache.commons.logging.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor zhangqianyuan
 * @updateDes ${TODO}
 */
public class MatchFragment1 extends Fragment {
    RecyclerView recycle1;
    MatchAdapter adapter1 ;

    private String fragment_date;

    private String fragment_shuxing;

    private String Fragment_imageUrl;

    public String getFragment_date() {
        return fragment_date;
    }

    public String getFragment_imageUrl() {
        return Fragment_imageUrl;
    }

    public String getFragment_shuxing() {
        return fragment_shuxing;
    }

    public void setFragment_date(String fragment_date) {
        this.fragment_date = fragment_date;
    }

    public void setFragment_imageUrl(String fragment_imageUrl) {
        Fragment_imageUrl = fragment_imageUrl;
    }

    public void setFragment_shuxing(String fragment_shuxing) {
        this.fragment_shuxing = fragment_shuxing;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.first_fragment,null);

        recycle1 = view.findViewById(R.id.first_list);
        adapter1 =new MatchAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recycle1.setLayoutManager(manager);
        recycle1.setAdapter(adapter1);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setDataFragment(List<AVObject> list){
        adapter1.setData(list);
    }
}
