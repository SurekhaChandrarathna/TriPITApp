package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity
{
    private Button placesButtonUser , guidesButtonUser, shopsButtonUser , hotelsButtonUser;
    private ImageButton LogOutUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        placesButtonUser= (Button)findViewById(R.id.btn_user_places);
        guidesButtonUser= (Button)findViewById(R.id.btn_user_guides);
        shopsButtonUser= (Button)findViewById(R.id.btn_user_shop);
        hotelsButtonUser= (Button)findViewById(R.id.btn_user_hotels);
        LogOutUser= (ImageButton) findViewById(R.id.imgBtnLogOut);

        //logging out the user
        LogOutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        // navigate page into  places interface
        placesButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this, PlacesHomeUser.class);
                startActivity(intent);
            }
        });

        //navigate page into  guides page
        guidesButtonUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this, GuideHome.class);
                startActivity(intent);
            }
        });

        //navigate page into  shops page

        //navigate page into hotels page
        hotelsButtonUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this, HotelUserActivity.class);
                startActivity(intent);
            }
        });


    }
}