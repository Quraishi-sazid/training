package com.example.mvvmexamplecif;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mvvmexamplecif.ViewModel.NoteViewModel;
import com.example.mvvmexamplecif.adapter.NoteAdapter;
import com.example.mvvmexamplecif.model.Note;
import com.example.mvvmexamplecif.utils.SwipeToDeleteCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NoteViewModel noteViewModel;
    NoteAdapter noteAdapter;
    SwipeToDeleteCallback swipeToDeleteCallback;
    CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout=findViewById(R.id.cl_note);
        RecyclerView recyclerView=findViewById(R.id.rv_note);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter=new NoteAdapter();

        recyclerView.setAdapter(noteAdapter);
        noteViewModel=ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NoteViewModel.class);
        swipeToDeleteCallback=new SwipeToDeleteCallback(this,noteViewModel,noteAdapter);
        noteViewModel.getNoteLiveData().observe(this,notes -> {
            noteAdapter.submitList(notes);
        });
        noteViewModel.getDeleteNoteLiveData().observe(this,note -> {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Note is deleted", Snackbar.LENGTH_LONG).setAction("Undo Delete",v -> {
                        List<Note>tempList=new ArrayList<>();
                        List<Note> currentList = noteAdapter.getCurrentList();
                        tempList.addAll(currentList);
                        if(tempList.size()>=noteViewModel.lastDeletePos)
                            tempList.add(note);
                        else
                            tempList.add(noteViewModel.lastDeletePos,note);
                        noteAdapter.submitList(tempList);
                    });
            snackbar.show();
        });
        findViewById(R.id.fb_add_note).setOnClickListener(view->{
            startActivityForResult(new Intent(MainActivity.this,AddNoteActivity.class),123);
            });
        new ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(recyclerView);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Note note = (Note) data.getSerializableExtra("Note");
            noteViewModel.insert(note);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_all_note,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete_note:
                noteViewModel.deleteAllNote();
        }
        return true;
    }

}