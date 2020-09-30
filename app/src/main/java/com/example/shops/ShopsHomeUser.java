package com.example.shops;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shops.Model.Shops;
import com.example.shops.ViewHolder.ShopViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ShopsHomeUser extends AppCompatActivity
{

    private DatabaseReference ShopsRef;
    private RecyclerView recyclerViewShops;
    RecyclerView.LayoutManager shopslayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_home_user);

        ShopsRef = FirebaseDatabase.getInstance().getReference().child("Items");

        recyclerViewShops = findViewById(R.id.shops_recycleview);
        recyclerViewShops.setHasFixedSize(true);
        shopslayoutManager = new LinearLayoutManager(this);
        recyclerViewShops.setLayoutManager(shopslayoutManager);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Shops> options =
                new FirebaseRecyclerOptions.Builder<Shops>()
                        .setQuery(ShopsRef, Shops.class)
                        .build();


        FirebaseRecyclerAdapter<Shops, ShopViewHolder> adapter =
                new FirebaseRecyclerAdapter<Shops, ShopViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ShopViewHolder holder, int position, @NonNull final Shops model) {

                        holder.txtShopItemName.setText( model.getNam());
                        holder.txtShopItemNumber.setText("Item number :  " + model.getName());
                        holder.txtShopItemPrice.setText("Item Price :  " + model.getPrice());
                        holder.txtShopItemDescription.setText("Item description :  " + model.getSize());
                        holder.txtShopItemQuantity.setText("Quantity :  " + model.getQuantitiy());
                        Picasso.get().load(model.getImage()).into(holder.ShopImageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                Intent intent =  new Intent(ShopsHomeUser.this, ItemDetailsActivity.class);
                                intent.putExtra("pid",model.getPid());
                                startActivity(intent);
                            }
                        });


                    }

                    @NonNull
                    @Override
                    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shops_layout, parent,false);
                        ShopViewHolder holder = new ShopViewHolder(view);
                        return holder;

                    }
                };

        recyclerViewShops.setAdapter(adapter);
        adapter.startListening();

    }

}