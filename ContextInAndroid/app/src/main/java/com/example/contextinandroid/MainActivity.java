package com.example.contextinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/*This project is a very simple example of context.
* Context is related information.
* Here two textviews are created.one is created with ApplicationContext and other is AcltivityContext.
* Application theme is Theme.ContextInAndroid .
* Activity theme is ActivityTheme(see manifest file)
* two textviews have two different colors.
* we should not create anything with application context if it is not needed
* in the whole application. If we do this then related things will be in memory which
* will cause memory leak.
* for example the applicationContextTextView will prevent MainActivity to be garbage collected
* link: https://www.youtube.com/watch?v=-e6lyXIkIl0
* 
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout=findViewById(R.id.layout);

        TextView applicationContextTextView=new TextView(getApplicationContext());
        applicationContextTextView.setTextSize(40);
        applicationContextTextView.setText("ApplicationContext");

        TextView ActivityContextTextView=new TextView(this);
        ActivityContextTextView.setTextSize(40);
        ActivityContextTextView.setText("ActivityContext");

        linearLayout.addView(applicationContextTextView);
        linearLayout.addView(ActivityContextTextView);
    }
}