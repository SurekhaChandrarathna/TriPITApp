package com.example.shops;

import androidx.annotation.NonNull;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddNew extends AppCompatActivity
{

    private String categoryName , Name , Price , Size , Quantity , saveCurrentData , saveCurrentTime;
    private Button Add;
    private ImageView select_image;
    private EditText name;
    private EditText price;
    private EditText size;
    private EditText quantity;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String itemRanKey , downloadImgUrl;
    private StorageReference ItemImageRef;
    private DatabaseReference ItemRef;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        categoryName = getIntent().getExtras().get("category").toString();
        ItemImageRef= FirebaseStorage.getInstance().getReference().child("Item Image");
        ItemRef = FirebaseDatabase.getInstance().getReference().child("Items");

        Add = (Button) findViewById(R.id.Add);
        select_image = (ImageView) findViewById(R.id.select_image);
        name = (EditText) findViewById(R.id.name);
        price = (EditText) findViewById(R.id.price);
        quantity = (EditText) findViewById(R.id.quantity);
        size = (EditText) findViewById(R.id.size);
        loadingBar = new ProgressDialog(this);

        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateData();
            }
        });
    }

    private void openGallery(){
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode ,resultCode ,data);

        if(requestCode == GalleryPick && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            select_image.setImageURI(ImageUri);
        }
    }

    private void  ValidateData(){

        Name = name.getText().toString();
        Size = size.getText().toString();
        Price = price.getText().toString();
        Quantity = quantity.getText().toString();

        if(ImageUri == null)
        {
            Toast.makeText(this, "Please choose an image...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Name))
        {
            Toast.makeText(this, "Please enter the item number...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Please enter the price...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Size))
        {
            Toast.makeText(this, "Please enter the sizes...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(Quantity))
        {
            Toast.makeText(this, "Please enter the quantity...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreItemInformation();
        }

    }
    private void StoreItemInformation(){

        loadingBar.setTitle("Adding New Item");
        loadingBar.setMessage("Please wait, while we are adding the new item!!");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calander = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyy");
        saveCurrentData = currentDate.format(calander.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a ");
        saveCurrentTime = currentTime.format(calander.getTime());

        itemRanKey = saveCurrentData + saveCurrentTime;

        final StorageReference filePath = ItemImageRef.child(ImageUri.getLastPathSegment() + itemRanKey);

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AddNew.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(AddNew.this, "Image uploaded successfully.....", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                        downloadImgUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful())
                        {
                            downloadImgUrl= task.getResult().toString();
                            Toast.makeText(AddNew.this, "get the image url successfully...", Toast.LENGTH_SHORT).show();

                            SaveItemInfoToDatabase();
                        }
                    }
                });
            }
        });

    }
    private void SaveItemInfoToDatabase()
    {
        HashMap<String , Object> itemMap = new HashMap<>();
        itemMap.put("pid",itemRanKey);
        itemMap.put("date",saveCurrentData);
        itemMap.put("time",saveCurrentTime);
        itemMap.put("image",downloadImgUrl);
        itemMap.put("name",Name);
        itemMap.put("nam",categoryName);
        itemMap.put("price",Price);
        itemMap.put("size",Size);
        itemMap.put("quantity",Quantity);

        ItemRef.child(itemRanKey).updateChildren(itemMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(AddNew.this,AddItems_2nd_Shops.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AddNew.this, "Item added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddNew.this, "Error"+message, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}