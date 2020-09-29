package com.example.hotels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotels.Model.Hotels;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HotelViewDetails extends AppCompatActivity
{
    private ImageView hotelDetailImage;
    private TextView hotelDetailName , hotelDetailAddress ,hotelDetailAbout;
    private String HotelName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view_details);

        hotelDetailImage = (ImageView)findViewById(R.id.hotel_detail_image);
        hotelDetailName= (TextView)findViewById(R.id.hotel_detail_name);
        hotelDetailAddress= (TextView)findViewById(R.id.hotel_detail_address);
        hotelDetailAbout= (TextView)findViewById(R.id.hotel_detail_description);

        ShowHotelDetails(HotelName);

    }

    private void ShowHotelDetails(String HotelName)
    {
        DatabaseReference hotelReference = FirebaseDatabase.getInstance().getReference().child("Hotels");

        hotelReference.child(HotelName).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    Hotels hotels = snapshot.getValue(Hotels.class);

                    hotelDetailName.setText(hotels.getHotelName());
                    hotelDetailAddress.setText(hotels.getHotelAddress());
                    hotelDetailAbout.setText(hotels.getAboutHotel());
                    Picasso.get().load(hotels.getHotelimage()).into(hotelDetailImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });


    }

}