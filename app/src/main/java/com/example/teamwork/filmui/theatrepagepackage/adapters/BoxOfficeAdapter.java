package com.example.teamwork.filmui.theatrepagepackage.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleBoxOffice;

import java.util.List;

public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.ViewHolder> {

    private List<SingleBoxOffice> singleBoxOffices;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView MovieName;
        TextView Irank;
        TextView BoxOffice;
        TextView sumBoxOffice;
        TextView boxPer;

        public ViewHolder(View view){
            super(view);
            MovieName=(TextView)view.findViewById(R.id.boxoffice_title);
            Irank = (TextView)view.findViewById(R.id.boxoffice_Irank);
            BoxOffice = (TextView)view.findViewById(R.id.boxoffice_boxoffice);
            sumBoxOffice = (TextView)view.findViewById(R.id.boxoffice_sumBoxOffice);
            boxPer = (TextView)view.findViewById(R.id.boxoffice_boxPer);
        }
    }

    public BoxOfficeAdapter(List<SingleBoxOffice> singleBoxOffices){
        this.singleBoxOffices=singleBoxOffices;
    }


    @Override
    public BoxOfficeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.boxoffice_item,parent,false);
        BoxOfficeAdapter.ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoxOfficeAdapter.ViewHolder holder, int position) {
        SingleBoxOffice singleBoxOffice=singleBoxOffices.get(position);
        holder.Irank.setText(Integer.toString(singleBoxOffice.getIrank()));
        holder.boxPer.setText(Double.toString(singleBoxOffice.getBoxPer())+"%");
        holder.BoxOffice.setText(Double.toString(singleBoxOffice.getBoxOffice()));
        holder.MovieName.setText(singleBoxOffice.getMovieName());
        holder.sumBoxOffice.setText(Double.toString(singleBoxOffice.getSumBoxOffice()));
    }

    @Override
    public int getItemCount() {
        return singleBoxOffices.size();
    }
}
