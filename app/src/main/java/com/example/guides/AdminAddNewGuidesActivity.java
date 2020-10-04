package com.example.guides;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
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


public class AdminAddNewGuidesActivity extends AppCompatActivity {

    private String  CategoryGuideName , Gname , Gconnumber , Gage , Gexperience , Gamount , saveCurrentDate , saveCurrentTime;
    private Button AddNewGuideButton;
    private ImageView InputGuideImage;
    private EditText InputGuideName, InputGuideConNumber, InputGuideAge, InputExperience ,InputAmount;
    private static final int galleryPick = 1 ;
    private Uri ImageGuideUri;
    private String guideRandomKey , downloadGuideImageuRL;
    private StorageReference GuideImageRef;
    private DatabaseReference GuidesRef;
    private ProgressDialog loadingBar;
    private AwesomeValidation guideawesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_guides);
        guideawesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);





        CategoryGuideName= getIntent().getExtras().get("categoryGuide").toString();
        GuideImageRef = FirebaseStorage.getInstance().getReference().child("Guides Images");
        GuidesRef = FirebaseDatabase.getInstance().getReference().child("Guides");





        AddNewGuideButton = (Button) findViewById(R.id.add_new_guide);
        InputGuideImage   = (ImageView) findViewById(R.id.select_guide_image);
        InputGuideName    = (EditText) findViewById(R.id.guide_name);
        InputGuideConNumber=(EditText) findViewById(R.id.guide_con_number);
        InputGuideAge = (EditText) findViewById(R.id.guide_age);
        InputExperience = (EditText) findViewById(R.id.guide_experiance);
        InputAmount = (EditText) findViewById(R.id.guide_amount);

        //Intitialize Validation Style

        guideawesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add validation for name:
        guideawesomeValidation.addValidation(AdminAddNewGuidesActivity.this,R.id.guide_name,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        //Validation for telephone-number

        guideawesomeValidation.addValidation(AdminAddNewGuidesActivity.this,R.id.guide_con_number,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobileNumber);

        AddNewGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gname = InputGuideName.getText().toString();
                Gconnumber = InputGuideConNumber.getText().toString();
                Gage = InputGuideAge.getText().toString();
                Gexperience = InputExperience.getText().toString();
                Gamount = InputAmount.getText().toString();



                //Check Validation
                if(guideawesomeValidation.validate()){
                    //On success
                    Toast.makeText(AdminAddNewGuidesActivity.this, "Form Validate Successfully...", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(AdminAddNewGuidesActivity.this, "Validate Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });



        loadingBar = new ProgressDialog(this);

        InputGuideImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });

        AddNewGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                ValidateGuideData();

            }
        });

    }
    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,galleryPick);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==galleryPick && resultCode==RESULT_OK && data!=null)
        {
            ImageGuideUri =  data.getData();
            InputGuideImage.setImageURI(ImageGuideUri);

        }
    }

    private void ValidateGuideData()
    {
        Gname = InputGuideName.getText().toString();
        Gconnumber = InputGuideConNumber.getText().toString();
        Gage = InputGuideAge.getText().toString();
        Gexperience = InputExperience.getText().toString();
        Gamount = InputAmount.getText().toString();

        if(ImageGuideUri == null)
        {
            Toast.makeText(this, "Project image is mandatory... ", Toast.LENGTH_SHORT).show();
            
        }
        else if(TextUtils.isEmpty(Gname))
        {
            Toast.makeText(this, "Please enter guide's name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Gconnumber))
        {
            Toast.makeText(this, "Please enter guide's contact number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Gage))
        {
            Toast.makeText(this, "Please enter guide's age", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Gexperience))
        {
            Toast.makeText(this, "Please enter guide's experience", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Gamount))
        {
            Toast.makeText(this, "Please enter amount per-day", Toast.LENGTH_SHORT).show();
        }
        else {
                StoreGuideImageInformation();

        }
    }

    private void StoreGuideImageInformation()
    {

        loadingBar.setTitle("login Guides");
        loadingBar.setMessage("Please wait until we are adding new guide to the system...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate =  new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime =  new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        guideRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filepath = GuideImageRef.child(ImageGuideUri.getLastPathSegment() + guideRandomKey + ".jpg");

        final UploadTask uploadTask = filepath.putFile(ImageGuideUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String massage = e.toString();
                Toast.makeText(AdminAddNewGuidesActivity.this, "Error:" + massage, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(AdminAddNewGuidesActivity.this, "Guide Image Uploaded successfully..", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();

                        }

                        downloadGuideImageuRL = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if(task.isSuccessful())
                        {

                            downloadGuideImageuRL = task.getResult().toString();

                            Toast.makeText(AdminAddNewGuidesActivity.this, "Getting guide image url successfully..", Toast.LENGTH_SHORT).show();

                            saveGuideInfoToDataBase();

                        }



                    }
                });

            }
        });

    }

    private void saveGuideInfoToDataBase()
    {
        HashMap<String, Object> guideMap = new HashMap<>();
        guideMap.put("gid", guideRandomKey);
        guideMap.put("date", saveCurrentDate);
        guideMap.put("time", saveCurrentTime);
        guideMap.put("guidename", Gname);
        guideMap.put("guidimage", downloadGuideImageuRL);
        guideMap.put("category", CategoryGuideName);
        guideMap.put("guidecontactnumber", Gconnumber);
        guideMap.put("guideage", Gage);
        guideMap.put("guideexperiance", Gexperience);
        guideMap.put("guideamount", Gamount);

        GuidesRef.child(guideRandomKey).updateChildren(guideMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(AdminAddNewGuidesActivity.this, AdminGuideCategoryActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AdminAddNewGuidesActivity.this, "Guide is added successfully...", Toast.LENGTH_SHORT).show();
                        }
                        else
                            {
                                loadingBar.dismiss();
                                String massage = task.getException().toString();
                                Toast.makeText(AdminAddNewGuidesActivity.this, "Error"+ massage, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


}