package com.example.tripit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripit.Model.Places;
import com.example.tripit.ViewHolder.PlaceViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PlacesHomeUser extends AppCompatActivity {

    private DatabaseReference PlacesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_home_user);

        PlacesRef= FirebaseDatabase.getInstance().getReference().child("Places");

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Places> options = new FirebaseRecyclerOptions.Builder<Places>()
                .setQuery(PlacesRef,Places.class).build();


        FirebaseRecyclerAdapter<Places , PlaceViewHolder> adapter = new FirebaseRecyclerAdapter<Places, PlaceViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull PlaceViewHolder holder, int position, @NonNull final Places model)
            {
                holder.txtPlaceName.setText(model.getName());
                holder.txtPlaceAddress.setText(model.getAddress());
                Picasso.get().load(model.getImage()).into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent =  new Intent(PlacesHomeUser.this, PlaceDetailsActivity.class);
                        intent.putExtra("name",model.getName());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_layout, parent,false);
                PlaceViewHolder holder= new PlaceViewHolder(view);

                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}