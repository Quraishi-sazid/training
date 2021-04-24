package com.example.firestoreexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.firestoreexample.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    CollectionReference collectionReference=FirebaseFirestore.getInstance().collection("Notebook");
    Note note=new Note();
    Map<String, Object> user = new HashMap<>();
    public String titleText;
    public String descriptionText;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setTitleText(titleText);
        activityMainBinding.setDescriptionText(descriptionText);
        activityMainBinding.setEventHandle(new EventHandle());
        activityMainBinding.setNote(note);
    }

    @Override
    protected void onStart() {
        super.onStart();
       /* collectionReference.document("my_first_note").addSnapshotListener(this,(documentSnapshot,e)->{
           if(e!=null) {
               return;
           }
            note = documentSnapshot.toObject(Note.class);
        });*/
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null)
                    return;
                activityMainBinding.tvAllData.setText("");
                List<DocumentChange> documentChanges = value.getDocumentChanges();
                for(DocumentChange documentChange:documentChanges)
                {
                    int oldIndex=1;
                    int newIndex=2;
                    switch (documentChange.getType())
                    {

                        case ADDED:
                            oldIndex=documentChange.getOldIndex();
                            newIndex=documentChange.getNewIndex();
                            break;
                        case REMOVED:
                            oldIndex=documentChange.getOldIndex();
                            newIndex=documentChange.getNewIndex();
                            break;
                        case MODIFIED:
                            oldIndex=documentChange.getOldIndex();
                            newIndex=documentChange.getNewIndex();
                    }
                }
                for(QueryDocumentSnapshot documentSnapshot: value){
                    Note note = documentSnapshot.toObject(Note.class);
                    activityMainBinding.tvAllData.setText(activityMainBinding.tvAllData.getText().toString()+
                            "id: "+note.getId()+"\ntitle: "+note.getTitle()+"\ndesc:"+note.getDescription()
                            +"\npriority:"+note.getPriority()+"\n\n");
                }
            }
        });
    }

    private void uploadData() {
       // collectionReference.document("my_first_note").set(note);
        collectionReference.add(note);
    }

    public class EventHandle
    {
        public void handleSubmit(){
            uploadData();
        }
        public void load(){
            loadData();
        }

        public void applyPagination(){
            MainActivity.this.applyPagination();
        }
        public void update(){
            note.setTitle(null);
            HashMap<String,Object>noteMap=new HashMap<>();
            noteMap.put("description","update desc");
            /*update or create new Data
            collectionReference.document("my_first_note").set(noteMap, SetOptions.merge());
            */
            collectionReference.document("my_first_note").update("description","update with field");
            collectionReference.document("my_first_note").update(noteMap);//but it won't create if it's not exitst

            collectionReference.document("my_first_note").update("description", FieldValue.delete());
            collectionReference.document("my_first_note").delete();
        }
    }

    private void loadData() {
        /*collectionReference.document("my_first_note").get().addOnSuccessListener(data->{
           *//* Map<String, Object> data1 = data.getData();
            note.setTitle((String) data.get("title"));
            note.setDescription((String) data.get("description"));*//*
            note = data.toObject(Note.class);
        });*/
        collectionReference.whereGreaterThan("priority","0")
                .whereEqualTo("title","title1").orderBy("priority").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                activityMainBinding.tvAllData.setText("");
                for(QueryDocumentSnapshot queryDocumentSnapshot: queryDocumentSnapshots)
                {
                    Note note = queryDocumentSnapshot.toObject(Note.class);
                    activityMainBinding.tvAllData.setText(activityMainBinding.tvAllData.getText().toString()+
                            "id: "+note.getId()+"\ntitle: "+note.getTitle()+"\ndesc:"+note.getDescription()
                            +"\npriority:"+note.getPriority()+"\n\n");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "onFailure: "+e.getCause());
            }
        });
    }
    public void doOrQuery()
    {
        Task<QuerySnapshot> task1 = collectionReference.whereGreaterThan("priority", "2").get();
        Task<QuerySnapshot> task2 = collectionReference.whereLessThan("priority", "3").get();
        Task<List<QuerySnapshot>> listTask = Tasks.whenAllSuccess(task1, task2);
        listTask.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
            @Override
            public void onSuccess(List<QuerySnapshot> querySnapshots) {
                activityMainBinding.tvAllData.setText("");
                for (QuerySnapshot queryDocumentSnapshots: querySnapshots)
                {
                    for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots)
                    {
                        Note note = documentSnapshot.toObject(Note.class);
                        activityMainBinding.tvAllData.setText(activityMainBinding.tvAllData.getText().toString()+
                                "id: "+note.getId()+"\ntitle: "+note.getTitle()+"\ndesc:"+note.getDescription()
                                +"\npriority:"+note.getPriority()+"\n\n");
                    }
                }
            }
        });

    }
    DocumentSnapshot lastDocument;
    public void applyPagination(){
        Query query;
        if(lastDocument==null) {
            activityMainBinding.tvAllData.setText("");
            query=collectionReference.limit(3);
        }else{
            query=collectionReference.startAfter(lastDocument).limit(3);
        }
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot queryDocumentSnapshot: queryDocumentSnapshots){
                    Note note = queryDocumentSnapshot.toObject(Note.class);
                    activityMainBinding.tvAllData.setText(activityMainBinding.tvAllData.getText().toString()+
                            "id: "+note.getId()+"\ntitle: "+note.getTitle()+"\ndesc:"+note.getDescription()
                            +"\npriority:"+note.getPriority()+"\n\n");
                    lastDocument=queryDocumentSnapshot;
                }
            }
        });
    }
}