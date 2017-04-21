package me.liwenkun.demo.lifecircle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.liwenkun.demo.R;

public class LifeCircleActivity extends AppCompatActivity {

    private static String TAG = "LifeCircleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_circle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //        在此暂停线程不会导致编译器警告，同时 Activity 可见，但布局不可见
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        在此暂停线程不会导致编译器警告，同时 Activity 可见，但布局不可见
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // weakReference = new WeakReference<Activity>(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");

    }
}
