package com.example.tripit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.tripit.Model.Shops;
import com.example.tripit.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ItemDetailsActivity extends AppCompatActivity
{
    private Button AddToTheCart;
    private ElegantNumberButton NumberButton;
    private ImageView ItemImageView;
    private TextView ItemCategory, ItemDescription, ItemPrice;
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
        AddToTheCart = (Button)findViewById(R.id.AddToCartButton);
        NumberButton = (ElegantNumberButton) findViewById(R.id.number_btn);


        getItemDetails(PID);

        AddToTheCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                addingToCartList();


            }
        });

    }

    private void addingToCartList()
    {

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate =  Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());

        final DatabaseReference cartListRef =  FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("pid", PID);
        cartMap.put("nam", ItemCategory.getText().toString());
        cartMap.put("price", ItemPrice.getText().toString());
        cartMap.put("size", ItemDescription.getText().toString());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);
        cartMap.put("quantity", NumberButton.getNumber());


        cartListRef.child("User View").child(Prevalent.currentOnlineUser.getphone()).child("Items").child(PID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    cartListRef.child("Admin View").child(Prevalent.currentOnlineUser.getphone())
                            .child("Items").child(PID)
                            .updateChildren(cartMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(ItemDetailsActivity.this, "Added to the cart list successfully..", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(ItemDetailsActivity.this, ShopsHomeUser.class);
                                        startActivity(intent);
                                    }

                                }
                            });


                }

            }
        });

    }





    private void getItemDetails(String PID)
    {
        DatabaseReference shopReference = FirebaseDatabase.getInstance().getReference().child("Items");

        shopReference.child(PID).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    Shops shop = snapshot.getValue(Shops.class);

                    ItemCategory.setText(shop.getNam());
                    ItemDescription.setText(shop.getSize());
                    ItemPrice.setText(shop.getPrice());

                    Picasso.get().load(shop.getImage()).into(ItemImageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }

}