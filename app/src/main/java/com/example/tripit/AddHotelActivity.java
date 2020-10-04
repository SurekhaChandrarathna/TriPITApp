package com.example.tripit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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

import java.util.HashMap;

public class AddHotelActivity extends AppCompatActivity
{
    private String HotelName, AboutHotel, HotelAddress, HotelContacts;

    private ImageView InputHotelImage;
    private EditText InputHotelName, InputHotelDescription, InputHotelContact,InputHotelAddress;
    private Button AddHotelButton;

    private static final int GalleryPick =1;
    private Uri ImageUri;

    private String  downloadImageUrl;
    private StorageReference HotelImagesRef;
    private DatabaseReference hotelsRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        HotelImagesRef = FirebaseStorage.getInstance().getReference().child("Hotels Images");
        hotelsRef= FirebaseDatabase.getInstance().getReference().child("Hotels");

        InputHotelImage= (ImageView)findViewById(R.id.image);

        InputHotelName= (EditText)findViewById(R.id.Ed_name_of_hotel);
        InputHotelDescription= (EditText)findViewById(R.id.Ed_description_of_hotel);
        InputHotelContact= (EditText)findViewById(R.id.Ed_contacts_of_hotel);
        InputHotelAddress= (EditText)findViewById(R.id.Ed_address_of_hotel);

        loadingBar = new ProgressDialog(this);

        AddHotelButton= (Button)findViewById(R.id.admin_add_hotel);

        InputHotelImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OpenGallery();
            }
        });

        AddHotelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ValidateHotelData();
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
            InputHotelImage.setImageURI(ImageUri);
        }

    }


    private void ValidateHotelData()
    {
        HotelName = InputHotelName.getText().toString();
        AboutHotel = InputHotelDescription.getText().toString();
        HotelAddress = InputHotelAddress.getText().toString();
        HotelContacts = InputHotelContact.getText().toString();


        if(ImageUri == null)
        {
            Toast.makeText(this, "A Image of the hotel is mandatory", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(HotelName))
        {
            Toast.makeText(this, "Please enter the name of the Hotel", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(AboutHotel))
        {
            Toast.makeText(this, "Please enter a brief description about the hotel", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(HotelAddress))
        {
            Toast.makeText(this, "Please enter the address of the hotel", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(HotelContacts))
        {
            Toast.makeText(this, "Please enter contact numbers of the hotel", Toast.LENGTH_SHORT).show();
        }

        else
        {
            StoreHotelInformation();
        }


    }


    private void StoreHotelInformation()
    {
        loadingBar.setTitle("Adding new hotel");
        loadingBar.setMessage("Please wait while we are adding data...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        final StorageReference filePath = HotelImagesRef.child(ImageUri.getLastPathSegment()+  ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AddHotelActivity.this, "Error :"+ message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
        {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AddHotelActivity.this, "Image of the hotel Uploaded Successfully...", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(AddHotelActivity.this, "Hotel Image successfully saved to database", Toast.LENGTH_SHORT).show();

                            SaveHotelInfoToDatabase();
                        }
                    }
                });

            }
        });


    }

    private void SaveHotelInfoToDatabase()
    {
        HashMap<String,Object> HotelMap = new HashMap<>();

        HotelMap.put("image" , downloadImageUrl);
        HotelMap.put("HotelName" , HotelName);
        HotelMap.put("AboutHotel" , AboutHotel);
        HotelMap.put("HotelAddress" , HotelAddress);
        HotelMap.put("HotelContacts" , HotelContacts);


        hotelsRef.child(HotelName).updateChildren(HotelMap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            //send back to the places interface
                            Intent intent = new Intent(AddHotelActivity.this,AdminHotelHome.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AddHotelActivity.this, "New Hotel is added successfully..", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddHotelActivity.this, "Error" +message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }


}