package com.example.mvvmexamplecif.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmexamplecif.model.Note;

import java.util.List;
/*
dao has to be an interface. But how we call a method in a interface.
room takes care of that. when we build the database it's generate the code for dao functions.
*/
@Dao
public interface NoteDao {
    @Insert
    void InsertNote(Note ... notes);
    @Update
    void UpdateNote(Note ... notes);
    @Delete
    void DeleteNote(Note ... notes);
    @Query("Select * from  note_table")
    LiveData<List<Note>>getAllNotes();
    @Query("Delete  from note_table")
    void deleteAllFromTable();

}
