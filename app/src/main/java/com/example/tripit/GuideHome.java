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


import com.example.tripit.Model.Guides;
import com.example.tripit.ViewHolder.GuideViwHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class GuideHome extends AppCompatActivity {


    private DatabaseReference GuidesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager guidelayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_home);

        GuidesRef = FirebaseDatabase.getInstance().getReference().child("Guides");

        recyclerView = findViewById(R.id.guide_recycleview);
        recyclerView.setHasFixedSize(true);
        guidelayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(guidelayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Guides> options =
                new FirebaseRecyclerOptions.Builder<Guides>()
                        .setQuery(GuidesRef, Guides.class)
                        .build();


        FirebaseRecyclerAdapter<Guides, GuideViwHolder> adapter =
                new FirebaseRecyclerAdapter<Guides, GuideViwHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull GuideViwHolder holder, int position, @NonNull Guides model) {

                        holder.txtguidesCatagory.setText( model.getCategory());
                        holder.txtGuideName.setText("Guide's Name :  " + model.getGuidename());
                        holder.txtGuideAge.setText("Guide's Age :  " + model.getGuideage());
                        holder.txtGuideContactNumber.setText("Guide's Contactnumber :  " + model.getGuidecontactnumber());
                        holder.txtGuideExperiance.setText("Experiance :  " + model.getGuideexperiance());
                        holder.txtGuideAmountPerDay.setText("Amount Per-day :"  +model.getGuideamount() + "$");
                        Picasso.get().load(model.getGuidimage()).into(holder.guideImageView);




                    }

                    @NonNull
                    @Override
                    public GuideViwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guides_layout, parent,false);
                        GuideViwHolder holder = new GuideViwHolder(view);
                        return holder;

                    }
                };



        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }


}