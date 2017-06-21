package com.example.yudhisthira.localservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by yudhisthira on 21/06/17.
 */

public class LocalServiceConnection implements ServiceConnection {

    private Context             context;

    private LocalService        localService;

    public LocalServiceConnection(Context context) {
        this.context = context;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        localService = ((LocalService.LocalBinder)iBinder).getService();

        Toast.makeText(context, "onServiceConnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

        localService = null;

        Toast.makeText(context, "onServiceDisconnected", Toast.LENGTH_SHORT).show();

    }

    public LocalService getLocalService() {
        return localService;
    }
}
