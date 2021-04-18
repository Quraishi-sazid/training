package com.example.firebaseuploadimage;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import javax.inject.Inject;

public class ImageRepository {
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    MutableLiveData<Boolean>imageUploadSucceded;
    MutableLiveData<Long>percentLiveData;
    public static final String ImageDatabasePath="ImageUploadRecord";
    private static final String TAG="ImageRepository";

    @Inject
    public ImageRepository(StorageReference storageReference,DatabaseReference databaseReference,MutableLiveData<Boolean> imageUploadSucceded,MutableLiveData<Long>percentLiveData) {
        this.storageReference= storageReference;
        this.databaseReference= databaseReference;
        this.imageUploadSucceded=imageUploadSucceded;
        this.percentLiveData=percentLiveData;
    }

    public void playWithDataBase() {
        DatabaseReference child = databaseReference.child("users/1");
        ValueEventListener postListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User value = snapshot.getValue(User.class);
                if(value!=null)
                {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        child.addValueEventListener(postListener);
    }

    public void uploadImage(Uri uri,String fileExtension) {
        String imageName = System.currentTimeMillis() + "." + fileExtension;
        StorageReference fileReference=storageReference.child(imageName);
        fileReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler=new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                percentLiveData.postValue(new Long(0));
                            }
                        },5000);
                        imageUploadSucceded.postValue(true);
                        updateDataBase(imageName);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                        imageUploadSucceded.postValue(false);
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                long percent = snapshot.getBytesTransferred() / snapshot.getTotalByteCount() * 100;
                Log.d(TAG, "onProgress: "+percent);
                percentLiveData.postValue(percent);
            }
        });
    }

    private void updateDataBase(String imageName) {
        DatabaseReference imageReference=databaseReference.child("Images").child("1");
        imageReference.setValue(imageName);
    }

    public MutableLiveData<Boolean> getImageUploadSucceded() {
        return imageUploadSucceded;
    }

    public MutableLiveData<Long> getPercentLiveData() {
        return percentLiveData;
    }
}
