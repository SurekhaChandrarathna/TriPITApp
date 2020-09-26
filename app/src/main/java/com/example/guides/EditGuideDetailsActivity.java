package com.example.guides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class EditGuideDetailsActivity extends AppCompatActivity {

    private Button EditGuideDetailsButton , DeleteGuideDetailsBtn;
    private EditText EditGuideName , EditGuideConNumbaer ,  EditGuideAge , EditGuideExperiance , EditGuideAmount;
    private ImageView EditGuidePicture;

    private String guideID = "";
    private DatabaseReference guidesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guide_details);



        guideID = getIntent().getStringExtra("gid");
        guidesRef = FirebaseDatabase.getInstance().getReference().child("Guides").child(guideID);


        EditGuideDetailsButton =  findViewById(R.id.editGuideDetailBtn);
        EditGuideName = findViewById(R.id.guideName_maintain);
        EditGuideConNumbaer = findViewById(R.id.guideConNumber_maintain);
        EditGuideAge = findViewById(R.id.guideAge_maintain);
        EditGuideExperiance = findViewById(R.id.guideExperiance_maintain);
        EditGuideAmount = findViewById(R.id.guideAmount_maintain);
        EditGuidePicture = findViewById(R.id.myguide_image_maintain);
        DeleteGuideDetailsBtn = findViewById(R.id.deleteGuideDetailBtn);



        displaySpecificGuideInfo();

        EditGuideDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditGuideDetailsButton();
            }
        });

        DeleteGuideDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DeleteThisGuide();

            }
        });

    }

    private void DeleteThisGuide()
    {

        guidesRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Intent intent = new Intent(EditGuideDetailsActivity.this, AdminGuideCategoryActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(EditGuideDetailsActivity.this, "The Guide is Deleted Succesfully..", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void EditGuideDetailsButton()
    {
        String EditName = EditGuideName.getText().toString();
        String EditContactNumber = EditGuideConNumbaer.getText().toString();
        String EditAge = EditGuideAge.getText().toString();
        String EditExperiance = EditGuideExperiance.getText().toString();
        String EditAmount = EditGuideAmount.getText().toString();

        if(EditName.equals("null"))
        {
            Toast.makeText(this, "Enter the guide's name..", Toast.LENGTH_SHORT).show();
        }
        else if(EditContactNumber.equals("null"))
        {
            Toast.makeText(this, "Enter the guide's contact number..", Toast.LENGTH_SHORT).show();
        }
        else if(EditAge.equals("null"))
        {
            Toast.makeText(this, "Enter the guide's age...", Toast.LENGTH_SHORT).show();
        }
        else if(EditExperiance.equals("null"))
        {
        Toast.makeText(this, "Enter guide's Experiance..", Toast.LENGTH_SHORT).show();
        }
        else if(EditAmount.equals("null"))
        {
            Toast.makeText(this, "Enter the guide's amount..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String, Object> guideMap = new HashMap<>();
            guideMap.put("gid", guideID);
            guideMap.put("guidename", EditName);
            guideMap.put("guidecontactnumber", EditContactNumber);
            guideMap.put("guideage", EditAge);
            guideMap.put("guideexperiance", EditExperiance);
            guideMap.put("guideamount", EditAmount);

            guidesRef.updateChildren(guideMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(EditGuideDetailsActivity.this, "Changes Applied Successfully..", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(EditGuideDetailsActivity.this, AdminGuideCategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }



    }

    private void displaySpecificGuideInfo() {

        guidesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String EditGuideName2 = dataSnapshot.child("guidename").getValue().toString();
                    String EditGuideConNumber2 = dataSnapshot.child("guidecontactnumber").getValue().toString();
                    String EditGuideAge2 = dataSnapshot.child("guideage").getValue().toString();
                    String EditGuideExperiance2 = dataSnapshot.child("guideexperiance").getValue().toString();
                    String EditGuideAmount2 = dataSnapshot.child("guideamount").getValue().toString();
                    String EditGuidePicture2 = dataSnapshot.child("guidimage").getValue().toString();

                    EditGuideName.setText(EditGuideName2);
                    EditGuideConNumbaer.setText(EditGuideConNumber2);
                    EditGuideAge.setText(EditGuideAge2);
                    EditGuideExperiance.setText(EditGuideExperiance2);
                    EditGuideAmount.setText(EditGuideAmount2);
                    Picasso.get().load(EditGuidePicture2).into(EditGuidePicture);


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}