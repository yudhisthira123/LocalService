package com.example.yudhisthira.localservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button                  startButton;
    private Button                  bindButton;
    private Button                  unBindButton;
    private Intent                  serviceIntent;
    private LocalServiceConnection  serviceConnection;
    private boolean                 isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.btnStartService);
        bindButton = findViewById(R.id.btnBoundService);
        unBindButton = findViewById(R.id.btnUnBindService);

        startButton.setOnClickListener(this);
        bindButton.setOnClickListener(this);
        unBindButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.btnStartService:
                startService();
                break;

            case R.id.btnBoundService:
                bindService();
                break;

            case R.id.btnUnBindService:
                unBindService();
                break;

        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if(null != serviceConnection) {
            LocalService localService = serviceConnection.getLocalService();
            localService.showNotification();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(serviceConnection);
    }

    private void startService() {

        if(null == serviceIntent) {
            serviceIntent = new Intent(this, LocalService.class);
        }

        startService(serviceIntent);
    }

    private void bindService() {
        if(null == serviceIntent) {
            serviceIntent = new Intent(this, LocalService.class);
        }

        if(null == serviceConnection) {
            serviceConnection = new LocalServiceConnection(this);
        }

        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        isBound = true;
    }

    private void unBindService() {

        if(null != serviceConnection && false == isBound) {
            unbindService(serviceConnection);
        }

        isBound = false;
        serviceConnection = null;
    }
}
