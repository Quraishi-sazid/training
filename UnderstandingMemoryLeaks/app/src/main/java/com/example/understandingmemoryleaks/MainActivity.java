package com.example.understandingmemoryleaks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
/* if we don't use static here, we are facing a memory leak although we use WeakReference<Context> . It's because if the nested class is not static it holds an anonymous reference of the outer class. Here My AsyncTask class holding an instance of MainActivity anonymously. which we can access by writing MainActivity.this . That instance can't be garbage collected even onDestroy method is called. So memory leak is happening.
NB: I am not a pro. please correct me if I am wrong.
Ref :
https://stackoverflow.com/questions/70324/java-inner-class-and-static-nested-class?noredirect=1&lq=1
https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
https://www.youtube.com/watch?v=bNM_3YkK2Ws
*/
public class MainActivity extends AppCompatActivity {
    MyAsyncTask myAsyncTask;
    private static final String TAG = "MainActivity";
    public int x=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.btn_go)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myAsyncTask!=null)
                {
                    finish();
                }
                Log.d(TAG,"calling on click method");
                myAsyncTask=new MyAsyncTask(MainActivity.this);
                myAsyncTask.execute();
            }
        });
    }

    public static class MyAsyncTask extends AsyncTask<Void,Void,Void>
    {
        //WeakReference<Context> context;
        Context context;

        public MyAsyncTask(Context context) {
            //this.context = new WeakReference<>(context);
            this.context=context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Bitmap bitmap= BitmapFactory.decodeResource(context .getResources(),R.drawable.ic_launcher_background);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"calling on destroy method");
    }
}