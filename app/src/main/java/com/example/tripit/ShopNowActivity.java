package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShopNowActivity extends AppCompatActivity
{
    private Button  shopnowbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_now);

        shopnowbtn=(Button)findViewById(R.id.mainbtn);

        shopnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopNowActivity.this, ShopsHomeUser.class);
                startActivity(intent);
            }
        });

    }
}