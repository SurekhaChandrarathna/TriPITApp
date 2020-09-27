package com.example.tripit.ViewHolder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripit.Interface.PlaceClickListner;
import com.example.tripit.R;

public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtPlaceName, txtPlaceAddress;
    public ImageView imageView;
    public PlaceClickListner listner;



    public PlaceViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView= (ImageView)itemView.findViewById(R.id.place_image);
        txtPlaceName= (TextView)itemView.findViewById(R.id.place_name);
        txtPlaceAddress= (TextView)itemView.findViewById(R.id.place_address);

    }

    public void setPlaceClickListner(PlaceClickListner listner)
    {
        this.listner = listner;
    }


    @Override
    public void onClick(View v)
    {
        listner.onClick(v, getAdapterPosition(),false);
    }
}
