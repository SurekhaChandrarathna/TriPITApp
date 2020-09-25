package com.example.tripit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripit.Model.Places;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PlaceDetailsActivity extends AppCompatActivity
{
    private ImageView placeDetailImage;
    private TextView placeDetailName , placeDetailAddress ,placeDetailDescription;
    private String placeName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        placeName = getIntent().getStringExtra("name");

        placeDetailImage = (ImageView)findViewById(R.id.place_detail_image);
        placeDetailName= (TextView)findViewById(R.id.place_detail_name);
        placeDetailAddress= (TextView)findViewById(R.id.place_detail_address);
        placeDetailDescription= (TextView)findViewById(R.id.place_detail_description);

        getPlaceDetails(placeName);

    }

    private void getPlaceDetails(String placeName)
    {
        DatabaseReference placeReference = FirebaseDatabase.getInstance().getReference().child("Places");

        placeReference.child(placeName).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    Places places = snapshot.getValue(Places.class);

                    placeDetailName.setText(places.getName());
                    placeDetailAddress.setText(places.getAddress());
                    placeDetailDescription.setText(places.getDescription());
                    Picasso.get().load(places.getImage()).into(placeDetailImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

    }

}