package me.liwenkun.demo.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import me.liwenkun.demo.R;
import me.liwenkun.demo.launchmode.TaskTest2Activity;
import me.liwenkun.demo.launchmode.TaskTest4Activity;
import me.liwenkun.demo.hotfix.HotFixActivity;

/**
 * Created by lwenkun on 2017/4/10.
 */

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button btnStartActivity = (Button) findViewById(R.id.btn_start_notification);
        btnStartActivity.setText("开启通知");
        btnStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    private int a  = 0;

    private void showNotification() {
        PendingIntent.getActivity(this, 1, new Intent(this, HotFixActivity.class), 0);
        TaskStackBuilder builder = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(new Intent(this, TaskTest4Activity.class));
        PendingIntent.getActivity(this, 2, new Intent(this, TaskTest2Activity.class), 0);
        Notification notificationCompat = new NotificationCompat.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("This is a notification")
                .setContentText("this is a content text")
               // .setDeleteIntent(builder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT))
                .setSubText("this is a sub text")
                .setContentIntent(builder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT))
                .setShowWhen(true)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentInfo("Content Info")
                .setGroup("cake")
                .setGroupSummary(true)
                .setLights(0xff3377, 2000, 1000)
                .build();



        NotificationManager notiMgr = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        notiMgr.notify(a++, notificationCompat);

    }
}
