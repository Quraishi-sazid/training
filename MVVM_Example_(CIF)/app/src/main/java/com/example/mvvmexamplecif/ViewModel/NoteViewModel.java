package com.example.mvvmexamplecif.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmexamplecif.Repository.NoteRepository;
import com.example.mvvmexamplecif.model.Note;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private LiveData<List<Note>> noteLiveData;
    NoteRepository noteRepository;
    private MutableLiveData<Note> deleteNoteLiveData=new MutableLiveData<>();
    public int lastDeletePos=-1;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);
        noteLiveData=noteRepository.getNoteLiveData();
    }
    public void insert(Note ... notes) {
        noteRepository.insert(notes);
    }
    public void update(Note ... notes) {
        noteRepository.update(notes);
    }
    public void delete(Note ... notes) {
        noteRepository.delete(notes);

        deleteNoteLiveData.setValue(notes[0]);
    }



    public void deleteAllNote() {
        noteRepository.deleteAllNote();
    }
    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }

    public MutableLiveData<Note> getDeleteNoteLiveData() {
        return deleteNoteLiveData;
    }
}
