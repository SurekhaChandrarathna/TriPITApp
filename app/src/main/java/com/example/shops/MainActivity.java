package com.example.shops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button adminShops, userShops;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminShops = (Button)findViewById(R.id.adminshops);
        userShops = (Button)findViewById(R.id.usershops);

        adminShops.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, AddItems_2nd_Shops.class);
                startActivity(intent);
            }
        });


        userShops.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ShopsHomeUser.class);
                startActivity(intent);
            }
        });

    }
}