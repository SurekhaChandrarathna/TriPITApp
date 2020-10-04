package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminHotelHome extends AppCompatActivity
{
    private Button btnAddHotel  ;
    private ImageButton BackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hotel_home);

        btnAddHotel= (Button)findViewById(R.id.btn_admin_add_hotels);
        BackToHome= (ImageButton)findViewById(R.id.admin_backto_home);

        btnAddHotel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHotelHome.this, AddHotelActivity.class);
                startActivity(intent);

            }
        });

        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHotelHome.this, AdminHome.class);
                startActivity(intent);
            }
        });


    }
}