package com.example.yudhisthira.localservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yudhisthira on 21/06/17.
 */

public class LocalService extends Service {

    private static final String TAG = LocalService.class.getSimpleName();

    private NotificationManager     notificationManager;
    private IBinder                 binder;

    public class LocalBinder extends Binder {

        public LocalService getService() {
            return LocalService.this;
        }

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        binder = new LocalBinder();

        //showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        //We can start Thread for long running task

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");

        //We can start Thread for long running task

        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public void showNotification() {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Local Service Started")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Local Service Started")
                .setContentText("Local Service Started")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(1, notification);

    }
}
