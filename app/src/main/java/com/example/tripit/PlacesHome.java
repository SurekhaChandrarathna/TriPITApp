package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlacesHome extends AppCompatActivity {

    Button AddPlacesButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_home);

        AddPlacesButton= (Button)findViewById(R.id.btn_admin_add_places);

        AddPlacesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(PlacesHome.this,AddPlacesActivity.class);
                startActivity(intent);
            }
        });

    }
}