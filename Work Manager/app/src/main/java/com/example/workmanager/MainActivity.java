package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.ArrayCreatingInputMerger;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
* WorkManager runs on background thread. It has similarity with job scheduler.but It has many more functionality.
* WorkManager can run on api level 14
* we can observe the work with live data.
* To start a work we have to follow the steps:
* 1->Create a Myworker class which extends worker. In the doWork method define your job.
* 2->Then create work request. we have to add necessary data,constrains in the work request.
* 3.->Then enqueue the work with WorkManager.
* 4.->We can run works in parallel. in that case work request list is passed to enqueue method.
* 5.->Work continuation can be used to do work sequentially.
* 6->one Work output is other work input.setInputMerger in request to handle the data input.(setInputMerger(new ArrayCreatingInputMerger())
* 7.->we can add tag to requests. works has id but it is uuid. we can define own tag. a request can have multiple tag. a tag can be used to define multiple works.
*     we can get live data of all works by tag.
* 8->we can observe work by livedata.
* 9.->beginUniqueWork line(63)
* link:
* https://www.youtube.com/watch?v=IrKoBFLwTN0&t=593s
* https://www.youtube.com/watch?v=uyUkH5v3S-Y&t=201s (3 more videos)
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data data=new Data.Builder().putString("name","sazid").build();
        Data data1=new Data.Builder().putString("name","ekhono ashe nai").build();
        Data data2=new Data.Builder().putString("name","afroja").build();
        Constraints constraints=new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest request=new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).addTag("ami 1").setInputMerger(ArrayCreatingInputMerger.class).setInputData(data).build();
        OneTimeWorkRequest request1=new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).addTag("ami 2").setInputMerger(ArrayCreatingInputMerger.class).setInputData(data1).build();
        OneTimeWorkRequest request2=new OneTimeWorkRequest.Builder(MyWorker2.class).setConstraints(constraints).setInputMerger(ArrayCreatingInputMerger.class).setInputData(data2).build();
        findViewById(R.id.btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*List<OneTimeWorkRequest>requestList=new ArrayList<>();
                requestList.add(request);
                requestList.add(request1);
               // WorkManager.getInstance(MainActivity.this).enqueue(requestList);
                WorkContinuation workContinuation = WorkManager.getInstance(MainActivity.this).beginWith(requestList);
                workContinuation= workContinuation.then(request);
                workContinuation.enqueue();*/
                WorkManager.getInstance(MainActivity.this).beginUniqueWork("sazid", ExistingWorkPolicy.APPEND,request).enqueue();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WorkManager.getInstance(MainActivity.this).beginUniqueWork("sazid", ExistingWorkPolicy.APPEND,request1).enqueue();

            }
        });
        TextView textView=findViewById(R.id.tex);

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                Data outputData = workInfo.getOutputData();
                String[] names = outputData.getStringArray("name");
                if(names!=null)
                textView.setText(textView.getText()+workInfo.getId().toString()+"\n"+workInfo.getState().name()+"\n"+names.toString());
                else
                    textView.setText(textView.getText()+workInfo.getId().toString()+"\n"+workInfo.getState().name()+"\n");

            }
        });
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request2.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                Data outputData = workInfo.getOutputData();
                String[] names = outputData.getStringArray("name");
                if(names!=null)
                    textView.setText(textView.getText()+workInfo.getId().toString()+"\n"+workInfo.getState().name()+"\n"+names.toString());
                else
                    textView.setText(textView.getText()+workInfo.getId().toString()+"\n"+workInfo.getState().name()+"\n");
            }
        });
    }
}