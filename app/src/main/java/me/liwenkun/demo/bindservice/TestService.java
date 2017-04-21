package me.liwenkun.demo.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import me.liwenkun.demo.BinderReceiver;


/**
 * Created by lwenkun on 2016/12/12.
 */

public class TestService extends Service {


    public BinderReceiver binder = new BinderReceiver.Stub() {

        @Override
        public void receiveBinder(IBinder binder) throws RemoteException {
           if (this == binder) {
               Log.d("TestService --> ", "ha ha , the client give his binder back to me and this binder is the same as origin");
               Log.d("binder's hashcode --> ", binder.toString());
           } else {
               Log.d("TestService --> ", "oh, the binder that client give back to me is not the same as origin");
           }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TestService --> ", "I'll never be bound for more than one time");
        return binder.asBinder();
    }
}
