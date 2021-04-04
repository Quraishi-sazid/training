package com.example.mvvmexamplecif.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mvvmexamplecif.NoteDataBase;
import com.example.mvvmexamplecif.dao.NoteDao;
import com.example.mvvmexamplecif.model.Note;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository
{
    NoteDao noteDao;
    LiveData<List<Note>>noteLiveData;
    public NoteRepository(Application application)
    {
        noteDao= NoteDataBase.getInstance(application).noteDao();
        noteLiveData=noteDao.getAllNotes();
    }
    public void insert(Note ... notes)
    {
        Execute(new Runnable() {
            @Override
            public void run() {
                noteDao.InsertNote(notes);
            }
        });
    }
    public void update(Note ... notes)
    {
        Execute(new Runnable() {
            @Override
            public void run() {
                noteDao.UpdateNote(notes);
            }
        });
    }
    public void delete(Note ... notes)
    {
        Execute(new Runnable() {
            @Override
            public void run() {
                noteDao.DeleteNote(notes);
            }
        });
    }

    public void deleteAllNote()
    {
        Execute(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteAllFromTable();
            }
        });
    }

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }

    void Execute(Runnable iRunnable)
    {
        ExecutorService service=Executors.newFixedThreadPool(1);
        service.execute(iRunnable);
    }
}
