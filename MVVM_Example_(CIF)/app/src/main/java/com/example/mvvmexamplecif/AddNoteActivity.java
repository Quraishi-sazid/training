package com.example.mvvmexamplecif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.mvvmexamplecif.model.Note;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etDescription;
    NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initialize();
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

    }

    private void initialize() {
        etTitle=findViewById(R.id.tv_title);
        etDescription=findViewById(R.id.tv_description);
        numberPicker=findViewById(R.id.np_priority);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
            saveNote();
            return true;
        }
        return true;
    }

    private void saveNote() {
        Intent intent=new Intent();
        intent.putExtra("Note",new Note(
                etTitle.getText().toString(),
                etDescription.getText().toString(),
                String.valueOf(numberPicker.getValue())
        ));
        setResult(RESULT_OK,intent);
        finish();
    }
}