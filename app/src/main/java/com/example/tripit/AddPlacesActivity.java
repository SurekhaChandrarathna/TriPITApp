package com.example.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.HashMap;

public class AddPlacesActivity extends AppCompatActivity
{
    private String name, district, description,address;
    private ImageView InputPlaceImage;
    private EditText  InputPlaceName, InputPlaceDistrict, InputPlaceDescription,InputPlaceAddress;
    private Button AddPlaceButton;

    private static final int GalleryPick =1;
    private Uri ImageUri;

    private String  downloadImageUrl;
    private StorageReference PlaceImagesRef;
    private DatabaseReference placesRef;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);

        PlaceImagesRef = FirebaseStorage.getInstance().getReference().child("Places Images");
        placesRef= FirebaseDatabase.getInstance().getReference().child("Places");

        InputPlaceImage = (ImageView)findViewById(R.id.image);

        InputPlaceName =(EditText)findViewById(R.id.Ed_name_of_place);
        InputPlaceDistrict =(EditText)findViewById(R.id.Ed_name_of_district);
        InputPlaceDescription =(EditText)findViewById(R.id.Ed_description_of_place);
        InputPlaceAddress =(EditText)findViewById(R.id.Ed_address_of_place);
        loadingBar = new ProgressDialog(this);

        AddPlaceButton = (Button)findViewById(R.id.admin_add_place);

        InputPlaceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                OpenGallery();
            }
        });

        AddPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidatePlaceData();
            }
        });

    }

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPick&& resultCode==RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            InputPlaceImage.setImageURI(ImageUri);
        }

    }

    private void ValidatePlaceData()
    {
        name = InputPlaceName.getText().toString();
        district = InputPlaceDistrict.getText().toString();
        description = InputPlaceDescription.getText().toString();
        address = InputPlaceAddress.getText().toString();

        if(ImageUri == null)
        {
            Toast.makeText(this, "A Image of the place is mandatory i", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please enter the name of the place", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(district))
        {
            Toast.makeText(this, "Please enter the district where the place is situated", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Please enter the description of the place", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(address))
        {
            Toast.makeText(this, "Please enter the address of the place", Toast.LENGTH_SHORT).show();
        }

        else
        {
            StorePlaceInformation();
        }

    }


    private void StorePlaceInformation()
    {
        loadingBar.setTitle("Adding new place");
        loadingBar.setMessage("Please wait while we are adding data...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        final StorageReference filePath = PlaceImagesRef.child(ImageUri.getLastPathSegment()+  ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AddPlacesActivity.this, "Error :"+ message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
        {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AddPlacesActivity.this, "Image of the Place Uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
                {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();

                        }

                        downloadImageUrl= filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if(task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();
                            Toast.makeText(AddPlacesActivity.this, "Place Image successfully saved to database", Toast.LENGTH_SHORT).show();

                            SavePlaceInfoToDatabase();
                        }
                    }
                });

            }
        });
    }

    private void SavePlaceInfoToDatabase()
    {
        HashMap<String,Object> PlaceMap = new HashMap<>();
        PlaceMap.put("image" , downloadImageUrl);
        PlaceMap.put("name" , name);
        PlaceMap.put("district" , district);
        PlaceMap.put("description" , description);
        PlaceMap.put("address" , address);

        placesRef.child(name).updateChildren(PlaceMap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            //send back to the places interface
                            Intent intent = new Intent(AddPlacesActivity.this,PlacesHome.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AddPlacesActivity.this, "New place is added successfully..", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddPlacesActivity.this, "Error" +message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

}