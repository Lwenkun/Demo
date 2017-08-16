package me.liwenkun.demo.touchevent;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import me.liwenkun.demo.R;

import static android.content.res.Resources.getSystem;

/**
 * Created by lwenkun on 2017/4/13.
 */

public class TouchEventTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_touch_event_test);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Drawable resources = getSystem().getDrawable(android.R.drawable.ic_lock_power_off);
            Drawable resource = getDrawable(android.R.drawable.ic_lock_power_off);
            Log.d(this.getClass().getCanonicalName(), resources.toString());
        }

    }

    // 运行一个 JAVA 程序
    public void onClick(View view) {
        try {
            Process pro = Runtime.getRuntime().exec("app_process -Djava.class.path=/sdcard/test.dex /sdcard me.liwenkun.sound.Test uuu");
            InputStream in = pro.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            Toast.makeText(this, br.readLine(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, br.readLine(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("SortActivity", "dispatchTouchEvent" + "-------------------------");
//        Log.d("SortActivity", (ev.getAction() == MotionEvent.ACTION_CANCEL) + "");
        // if (ev.getAction() == MotionEvent.ACTION_CANCEL) Toast.makeText(this, "event type is cancel", Toast.LENGTH_SHORT).show();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("SortActivity", "onTouchEvent");
        return super.onTouchEvent(event);
    }

}
