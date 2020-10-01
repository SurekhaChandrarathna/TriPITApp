package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlacesHome extends AppCompatActivity {

    Button AddPlacesButton, ViewPlaceButton ;
    ImageButton BackToHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_home);

        AddPlacesButton= (Button)findViewById(R.id.btn_admin_add_places);
        ViewPlaceButton= (Button) findViewById(R.id.btn_admin_view_places);
        BackToHomeButton= (ImageButton)findViewById(R.id.admin_backto_home);

        AddPlacesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(PlacesHome.this,AddPlacesActivity.class);
                startActivity(intent);
            }
        });

        ViewPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(PlacesHome.this,PlaceListAdmin.class);
                startActivity(intent);
            }
        });


        BackToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(PlacesHome.this,AdminHome.class);
                startActivity(intent);
            }
        });

    }
}