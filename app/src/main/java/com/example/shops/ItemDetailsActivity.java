package com.example.shops;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shops.Model.Shops;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ItemDetailsActivity extends AppCompatActivity
{
    private ImageView ItemImageView;
    private TextView  ItemCategory, ItemDescription, ItemPrice;
    private String PID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        PID = getIntent().getStringExtra("pid");

        ItemImageView= (ImageView)findViewById(R.id.shop_detail_image);
        ItemCategory= (TextView)findViewById(R.id.shop_detail_nam);
        ItemDescription= (TextView)findViewById(R.id.shop_detail_description);
        ItemPrice= (TextView)findViewById(R.id.shop_detail_price);

        getItemDetails();

    }


    private void getItemDetails()
    {
        DatabaseReference shopReference = FirebaseDatabase.getInstance().getReference().child("Items");

        shopReference.child(PID).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    Shops places = snapshot.getValue(Shops.class);

                    ItemCategory.setText(places.getNam());
                    ItemDescription.setText(places.getSize());
                    ItemPrice.setText(places.getPrice());

                    Picasso.get().load(places.getImage()).into(ItemImageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }

}