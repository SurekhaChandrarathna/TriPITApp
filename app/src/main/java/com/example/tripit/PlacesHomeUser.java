package com.example.tripit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tripit.Model.Places;
import com.example.tripit.ViewHolder.PlaceViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class PlacesHomeUser extends AppCompatActivity {

    private DatabaseReference PlacesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private  EditText search_input; //new for search function

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

        search_input= (EditText) findViewById(R.id.searchPlace); //new for search function

        LoadData(""); //new for search

        search_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!((s.toString()) == null))
                {
                    LoadData(s.toString());
                }
                else
                {
                    LoadData("");
                }

            }
        });
        //new for search

    }


//view details
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
    //view details





    //new for search function
    private void LoadData(String data)
    {
        Query query = PlacesRef.orderByChild("name").startAt(data).endAt(data+"\uf8ff");

        FirebaseRecyclerOptions<Places> options = new FirebaseRecyclerOptions.Builder<Places>()
                .setQuery(PlacesRef,Places.class).build();

        FirebaseRecyclerAdapter<Places, PlaceViewHolder> adapter = new FirebaseRecyclerAdapter<Places, PlaceViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PlaceViewHolder holder, int position, @NonNull final Places model) {
                holder.txtPlaceName.setText(model.getName());
                holder.txtPlaceAddress.setText(model.getAddress());
                Picasso.get().load(model.getImage()).into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlacesHomeUser.this, PlaceDetailsActivity.class);
                        intent.putExtra("name", model.getName());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_layout, parent, false);
                PlaceViewHolder holder = new PlaceViewHolder(view);

                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    //new for search function


}