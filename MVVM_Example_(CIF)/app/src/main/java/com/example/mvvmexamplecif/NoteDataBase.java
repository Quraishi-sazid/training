package com.example.mvvmexamplecif;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.mvvmexamplecif.dao.NoteDao;
import com.example.mvvmexamplecif.model.Note;

/*
*
* */


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    private static NoteDataBase instance;
    public abstract NoteDao noteDao(); //it's an abstract function whose implementation is done by room.

    public static synchronized NoteDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDataBase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

   private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
   {
       NoteDataBase noteDataBase;
       public PopulateDbAsyncTask(NoteDataBase noteDataBase)
       {
           this.noteDataBase=noteDataBase;
       }

       @Override
       protected Void doInBackground(Void... voids)
       {
           noteDataBase.noteDao().InsertNote(new Note("Title 1","Desc 1","1"),
                   new Note("Title 2","Desc 2","2"),
                   new Note("Title 3","Desc 3","3"));
           return null;
       }
   }
}
