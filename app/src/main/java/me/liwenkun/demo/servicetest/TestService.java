package me.liwenkun.demo.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import me.liwenkun.demo.launchmode.TaskTest2Activity;

/**
 * Created by lwenkun on 2017/4/15.
 */

public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startActivity(new Intent(this, TaskTest2Activity.class));
        return super.onStartCommand(intent, flags, startId);
    }
}
