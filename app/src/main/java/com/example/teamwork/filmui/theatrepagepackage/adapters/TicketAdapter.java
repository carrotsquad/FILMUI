package com.example.teamwork.filmui.theatrepagepackage.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teamwork.filmui.R;
import com.example.teamwork.filmui.homepagepackage.utils.GetImageFromInternet;
import com.example.teamwork.filmui.purchasing.TicketPage;
import com.example.teamwork.filmui.theatrepagepackage.beans.SingleTicket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    private Context mContext;

    private List<SingleTicket> singleTicketList;

    private SingleTicket singleTicket;

    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView filmImage;
        TextView filmname;
        TextView date;
        TextView seat;
        TextView cinema;
        LinearLayout linearLayout;
        TextView ticketscount;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            filmImage = (ImageView) view.findViewById(R.id.ticketitem_image);
            filmname = (TextView) view.findViewById(R.id.ticketitem_filmname);
            date = (TextView) view.findViewById(R.id.ticketitem_date);
            seat = (TextView) view.findViewById(R.id.ticketitem_seat);
            cinema = (TextView) view.findViewById(R.id.ticketitem_cinema);
            linearLayout = (LinearLayout) view.findViewById(R.id.ticketitem_linearlayout);
            ticketscount = (TextView) view.findViewById(R.id.ticketitem_num);
        }
    }

    public TicketAdapter(Activity activity, List<SingleTicket> singleTicketList){
        this.activity = activity;
        this.singleTicketList = singleTicketList;
    }


    @NonNull
    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.ticket_item, parent,false);
        final TicketAdapter.ViewHolder holder = new TicketAdapter.ViewHolder(view);

        /* 转到电影票页面 */
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(mContext, TicketPage.class);
                SingleTicket singleTicket = singleTicketList.get(position);
                intent.putExtra("filmTitle",singleTicket.getMovie_title());
                intent.putExtra("date",singleTicket.getDate_time()+" "+singleTicket.getShuxing());
                intent.putExtra("imageUri",singleTicket.getImageUrl());
                intent.putExtra("seatlocation",singleTicket.getSeat_location());
                intent.putExtra("cinemaplace",singleTicket.getCinema_title()+" "+singleTicket.getPlace());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.ViewHolder holder, int position) {
        singleTicket = singleTicketList.get(position);
        holder.cinema.setText(singleTicket.getCinema_title());
        holder.seat.setText(singleTicket.getSeat_location());
        holder.date.setText(singleTicket.getDate_time());
        GetImageFromInternet.setImageView(singleTicket.getImageUrl(), holder.filmImage, activity);
        holder.filmname.setText(singleTicket.getMovie_title());
        holder.ticketscount.setText(singleTicket.getNum()+"张");
    }

    @Override
    public int getItemCount() {
        return singleTicketList.size();
    }
}
