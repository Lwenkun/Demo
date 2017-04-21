package me.liwenkun.demo.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.liwenkun.demo.BinderReceiver;
import me.liwenkun.demo.R;

public class BindServiceActivity extends AppCompatActivity {

    private ServiceConnection getConnection() {
        return new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                BinderReceiver receiver = BinderReceiver.Stub.asInterface(service);
                try {
                    receiver.receiveBinder(service);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.d("Common --> ", "service has been connected");
                Log.d("Common --> ", service.toString());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("TestService --> ", "bind a service from a service successfully");
            Log.d("TestServcie -->", Process.myPid() + "");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(BindServiceActivity.this, TestService2.class), conn, BIND_AUTO_CREATE);
                Log.d("MainActivity --> ", "you have bind a service");
            }
        });
    }
}
