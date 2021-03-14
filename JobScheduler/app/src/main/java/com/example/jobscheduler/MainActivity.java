package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
* This app is an example of job scheduler
* Job scheduler can be said as intelligent alarm manager.
* Instead of setting the criteria to time we can set many criteria fire.
* We need to have a class extending Job Service. which has onStartJob and onStopJob.
*  onStartJob return type is false if it runs in current thread and true if
* it runs in background thread.If it runs in the background thread then we have to
* finsih the job by ourselves calling jobFinished(params,false).
* On stop job will be called if the job running criteria fails.
* 1. we need a ComponentName.
* 2.Then we need a JobInfo with jobId and ComponentName.
* 3.Then we need a job scheduler. it's instance can't be created with new.
*   we need getSystemService(JOB_SCHEDULER_SERVICE) to get the scheduler.
* 4.then jobScheduler.schedule(jobInfo); can be used.
*
* Related links:
* https://www.youtube.com/watch?v=3EQWmME-hNA&list=PLrnPJCHvNZuBhmqlWEQfvxbNtY6B_XJ3n&index=1
*
*
* */
public class MainActivity extends AppCompatActivity {
    public static int JOB_ID=123;
    private JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.bt_start_job)).setOnClickListener((View.OnClickListener) v -> {
            scheduleJob();
        });
        ((Button)findViewById(R.id.bt_end_job)).setOnClickListener((View.OnClickListener) v -> {
            cancelJob();
        });

    }
    public void scheduleJob()
    {
        ComponentName componentName=new ComponentName(this,ExampleJobService.class);
        JobInfo jobInfo=new JobInfo.Builder(JOB_ID,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();
        jobScheduler=(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int is=jobScheduler.schedule(jobInfo);
        int z=4;
    }
    public void cancelJob()
    {
        jobScheduler.cancel(JOB_ID);
    }

    View.OnClickListener startJobCallback=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scheduleJob();
        }
    };
    View.OnClickListener endJobCallback=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cancelJob();
        }
    };
}