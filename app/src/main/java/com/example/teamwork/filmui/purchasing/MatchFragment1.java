package com.example.teamwork.filmui.purchasing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVObject;
import com.example.teamwork.filmui.R;

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
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MatchFragment1 extends Fragment {
    RecyclerView recycle1;
    MatchAdapter adapter1 ;
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
    public void setDataFragment(List<AVObject> list){
        adapter1.setData(list);
    }
}
