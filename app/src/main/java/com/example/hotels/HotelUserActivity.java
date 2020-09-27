package com.example.hotels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotels.Model.Hotels;
import com.example.hotels.ViewHolder.HotelViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HotelUserActivity extends AppCompatActivity
{
    private DatabaseReference HotelsRef;
    private RecyclerView recyclerViewHotel;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_user);

        HotelsRef= FirebaseDatabase.getInstance().getReference().child("Hotels");

        recyclerViewHotel = findViewById(R.id.recycler_menu_hotel);
        recyclerViewHotel.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerViewHotel.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Hotels> options = new FirebaseRecyclerOptions.Builder<Hotels>()
                .setQuery(HotelsRef,Hotels.class).build();


        FirebaseRecyclerAdapter<Hotels , HotelViewHolder> adapter = new FirebaseRecyclerAdapter<Hotels, HotelViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull HotelViewHolder holder, int position, @NonNull final Hotels model)
            {
                holder.txtHotelName.setText(model.getHotelName());
                holder.txtHotelAddress.setText(model.getHotelAddress());
                Picasso.get().load(model.getHotelimage()).into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent =  new Intent(HotelUserActivity.this, HotelViewDetails.class);
                        intent.putExtra("HotelName",model.getHotelName());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotels_layout, parent,false);
                HotelViewHolder holder= new HotelViewHolder(view);

                return holder;
            }
        };

        recyclerViewHotel.setAdapter(adapter);
        adapter.startListening();
    }

}