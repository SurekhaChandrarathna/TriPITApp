package com.example.guides;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminGuideCategoryActivity extends AppCompatActivity {

    private ImageView withVehicals , withOutVehicals;
    private Button maintainGuides , maintainGuides2;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_guide_category);


        withVehicals = (ImageView) findViewById(R.id.withVehicals);
        withOutVehicals = (ImageView) findViewById(R.id.withOutVehicals);
        maintainGuides = (Button) findViewById(R.id.maintain_guides);
        maintainGuides2 = (Button) findViewById(R.id.guideMaintain);


        maintainGuides.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdminGuideCategoryActivity.this, GuideHome.class);
                        intent.putExtra("guideAdmin","guideAdmin");
                        startActivity(intent);



            }
        });
        maintainGuides2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminGuideCategoryActivity.this, MaintainGuidesActivity.class);
                intent.putExtra("guideAdmin2","guideAdmin2");
                startActivity(intent);



            }
        });








        withVehicals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminGuideCategoryActivity.this, AdminAddNewGuidesActivity.class);
                intent.putExtra("categoryGuide","withVehicals");
                startActivity(intent);

            }
        });

        withOutVehicals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminGuideCategoryActivity.this, AdminAddNewGuidesActivity.class);
                intent.putExtra("categoryGuide","withOutVehicals");
                startActivity(intent);

            }
        });



    }
}