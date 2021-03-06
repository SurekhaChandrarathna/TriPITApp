package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.paperdb.Paper;

public class AdminHome extends AppCompatActivity
{
    Button LogoutButtonAdmin, placesButtonAdmin , guidesButtonAdmin , shopsButtonAdmin, hotelsButtonAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        LogoutButtonAdmin= (Button)findViewById(R.id.logout_btn_admin);
        placesButtonAdmin= (Button)findViewById(R.id.btn_admin_places);
        guidesButtonAdmin= (Button)findViewById(R.id.btn_admin_guides);
        hotelsButtonAdmin= (Button)findViewById(R.id.btn_admin_hotels);
        shopsButtonAdmin= (Button)findViewById(R.id.btn_admin_shops);


        //Go back to main Activity where login buttons appears
        LogoutButtonAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Paper.book().destroy();

                Intent intent = new Intent(AdminHome.this,MainActivity.class);
                startActivity(intent);

            }
        });


        //Navigate to places page where admin have to do places related updates, adding..
        placesButtonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHome.this,PlacesHome.class);
                startActivity(intent);
            }
        });



        //Navigate to guides page where admin do "guides" related changes
        guidesButtonAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHome.this,AdminGuideCategoryActivity.class);
                startActivity(intent);
            }
        });


        //Navigate to shops page where admin do "shops" related changes
        shopsButtonAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHome.this,AddItems_2nd_Shops.class);
                startActivity(intent);
            }
        });


        //Navigate to hotels page where admin do "shops" related changes
        hotelsButtonAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHome.this,AdminHotelHome.class);
                startActivity(intent);
            }
        });
    }
}