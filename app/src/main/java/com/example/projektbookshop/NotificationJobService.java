package com.example.projektbookshop;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJobService extends JobService {
    private  NotificationHandler mNotificationHelper;

    @Override
    public boolean onStartJob(JobParameters params) {
        mNotificationHelper = new NotificationHandler(getApplicationContext());
        mNotificationHelper.send("Ne habozz! Vegyél és olvass könyvet! \\ (•◡•) /");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}

