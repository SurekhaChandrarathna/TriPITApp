package com.example.guides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guides.Model.Guides;
import com.example.guides.ViewHolder.GuideViwHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class MaintainGuidesActivity extends AppCompatActivity {


    private DatabaseReference GuidesRef;
    private RecyclerView maintainrecyclerView;
    RecyclerView.LayoutManager guidelayoutManager;

    private String guidetype = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_guides);

        guidetype = getIntent().getExtras().get("guideAdmin2").toString();

        GuidesRef = FirebaseDatabase.getInstance().getReference().child("Guides");

        maintainrecyclerView = findViewById(R.id.maintainGuides_recucleview);
        maintainrecyclerView.setHasFixedSize(true);
        guidelayoutManager = new LinearLayoutManager(this);
        maintainrecyclerView.setLayoutManager(guidelayoutManager);



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
                    protected void onBindViewHolder(@NonNull GuideViwHolder holder, int position, @NonNull final Guides model) {

                        holder.txtguidesCatagory.setText( model.getCategory());
                        holder.txtGuideName.setText("Guide's Name :  " + model.getGuidename());
                        holder.txtGuideAge.setText("Guide's Age :  " + model.getGuideage());
                        holder.txtGuideContactNumber.setText("Guide's Contactnumber :  " + model.getGuidecontactnumber());
                        holder.txtGuideExperiance.setText("Experiance :  " + model.getGuideexperiance());
                        holder.txtGuideAmountPerDay.setText("Amount Per-day :"  +model.getGuideamount() + "$");
                        Picasso.get().load(model.getGuidimage()).into(holder.guideImageView);


                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(MaintainGuidesActivity.this, EditGuideDetailsActivity.class);
                                intent.putExtra("gid" , model.getGid());
                                startActivity(intent);

                            }
                        });



                    }

                    @NonNull
                    @Override
                    public GuideViwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guides_layout, parent,false);
                        GuideViwHolder holder = new GuideViwHolder(view);
                        return holder;

                    }
                };



        maintainrecyclerView.setAdapter(adapter);
        adapter.startListening();


    }


}
