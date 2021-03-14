package com.example.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;


public class ExampleJobService extends JobService {
    public static String Tag="ExampleJobService";
    /*This variable is needed because if job is cancelled by onStopJob then we have to
    return from doBackgroundWork method.other wise this may lead to malfunction.
    */
    public boolean isJobCancelled=false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(Tag,"onStartJob called");
        doBackgroundWork(params);
        return true;// true creates new thread. false means job will run in existing thread.
    }

    private void doBackgroundWork(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++)
                {
                    Log.d(Tag,"Job running "+i);
                    Log.d(Tag,"isJobCancelled ="+isJobCancelled);
                    if(isJobCancelled)
                    {
                        isJobCancelled=false;
                        return;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        Log.d(Tag,"Job ended "+i);
                    }
                }
                jobFinished(params,false);
                Log.d(Tag,"Job finished ");
            }
        }).start();
    }
/*This method is called if the system decides the job must be stopped.
* even before you’ve had a chance to call jobFinished(JobParameters, boolean).
* ow does the system know when my job stops

When the constraint(condition) that u hv set in your jobinfo doesnt meets or the conditions is not meeting anymore than the app knows that the job has stopped.
* */
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(Tag,"Job cancelled ");
        isJobCancelled=true;
        return false;
    }
}
