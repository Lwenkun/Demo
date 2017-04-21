package me.liwenkun.demo.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lwenkun on 2016/12/13.
 */

public class TestService2 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TestService2 -->", Process.myPid() + "");
        return new Binder();
    }
}
