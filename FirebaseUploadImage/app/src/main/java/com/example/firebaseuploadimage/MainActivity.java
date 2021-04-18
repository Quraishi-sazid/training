package com.example.firebaseuploadimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.firebaseuploadimage.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    private static final int PICK_IMAGE = 2;
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    EventHandling eventHandling;
    public ProgressData progressData;
    private  Uri imageUri;
    private MainActivityViewModel mainActivityViewModel;
    @Inject ProgressDataFactory progressDataFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setEventHandler(new EventHandling());
        progressData=progressDataFactory.create(0);
        binding.setProgressData(progressData);
        mainActivityViewModel= new ViewModelProvider(this).get(MainActivityViewModel.class);
        setLiveDataAction();
    }

    private void setLiveDataAction() {
        mainActivityViewModel.imageUploadSucceded.observe(this, isUploaded -> {
            if(isUploaded)
                Toast.makeText(MainActivity.this,"Image Uploaded successfully",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Image Upload Unsuccessful",Toast.LENGTH_LONG).show();
        });
        mainActivityViewModel.imageRepository.getPercentLiveData().observe(this,percent->{
            progressData.setPercent(percent.intValue());
        });
    }

    private void pickImage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE){
            if(data!=null){
                Uri data1 = data.getData();
                binding.ivImage.setImageURI(data1);
                imageUri=data1;
            }
        }
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{READ_EXTERNAL_STORAGE},READ_EXTERNAL_STORAGE_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==READ_EXTERNAL_STORAGE_REQUEST_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                pickImage();
            }
        }
    }
    public class EventHandling {

        public EventHandling(){

        }
        public void selectImageFile(View view) {
            if(ActivityCompat.checkSelfPermission(MainActivity.this,READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
            else {
                requestPermission();
            }
        }
        public void UploadImageFile() {
            if(imageUri!=null)
                mainActivityViewModel.uploadImage(imageUri,Utility.getFileExtension(MainActivity.this,imageUri));
        }
    }



}