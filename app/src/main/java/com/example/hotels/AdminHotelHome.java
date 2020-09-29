package com.example.hotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHotelHome extends AppCompatActivity
{
    private Button btnAddHotel ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hotel_home);

        btnAddHotel= (Button)findViewById(R.id.btn_admin_add_hotels);

        btnAddHotel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHotelHome.this, AddHotelActivity.class);
                startActivity(intent);

            }
        });


    }
}