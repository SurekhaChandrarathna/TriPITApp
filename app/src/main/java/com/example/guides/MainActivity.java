package com.example.guides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGuide = findViewById(R.id.btnGuides);

        buttonGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGuide();
            }
        });
    }

    public void openGuide(){
        Intent intent = new Intent(MainActivity.this,AdminGuideCategoryActivity.class);
        startActivity(intent);



    }
}