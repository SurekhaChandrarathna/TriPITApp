package com.example.tripit.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripit.Interface.HotelClickListner;
import com.example.tripit.R;


public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtHotelName, txtHotelAddress;
    public ImageView imageView;
    public HotelClickListner listner;

    public HotelViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView= (ImageView)itemView.findViewById(R.id.hotel_image);
        txtHotelName= (TextView)itemView.findViewById(R.id.hotel_name);
        txtHotelAddress= (TextView)itemView.findViewById(R.id.hotel_address);
    }

    public void setHotelClickListner(HotelClickListner listner)
    {
        this.listner = listner;
    }


    @Override
    public void onClick(View v)
    {
        listner.onClick(v, getAdapterPosition(),false);
    }
}
