package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddItems_2nd_Shops extends AppCompatActivity
{

    private ImageView T_shirt ,keytag,mug ,bag , cap , bottle;
    private ImageButton BackToHome;
    private Button viewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items_2nd__shops);


        T_shirt = (ImageView) findViewById(R.id.T_shirt);
        keytag = (ImageView) findViewById(R.id.keytag);
        mug = (ImageView) findViewById(R.id.mug);
        bag = (ImageView) findViewById(R.id.bag);
        cap = (ImageView) findViewById(R.id.cap);
        bottle = (ImageView) findViewById(R.id.bottle);

        viewItem=(Button)findViewById(R.id.item_view);
        BackToHome= (ImageButton)findViewById(R.id.admin_backto_home);

        T_shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItems_2nd_Shops.this,AddNew.class);
                intent.putExtra( "category", "T_shirt");
                startActivity(intent);

            }

        });

        keytag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItems_2nd_Shops.this,AddNew.class);
                intent.putExtra( "category", "keytag");
                startActivity(intent);

            }

        });

        mug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItems_2nd_Shops.this,AddNew.class);
                intent.putExtra( "category", "mug");
                startActivity(intent);

            }

        });
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItems_2nd_Shops.this,AddNew.class);
                intent.putExtra( "category", "bag");
                startActivity(intent);

            }

        });
        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItems_2nd_Shops.this,AddNew.class);
                intent.putExtra( "category", "cap");
                startActivity(intent);

            }

        });
        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItems_2nd_Shops.this,AddNew.class);
                intent.putExtra( "category", "bottle");
                startActivity(intent);

            }

        });


        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(AddItems_2nd_Shops.this,AdminHome.class);
                startActivity(intent);
            }
        });

        viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddItems_2nd_Shops.this,ShopsAdminViewList.class);
                startActivity(intent);
            }
        });



    }
}