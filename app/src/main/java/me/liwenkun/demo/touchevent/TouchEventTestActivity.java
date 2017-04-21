package me.liwenkun.demo.touchevent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import me.liwenkun.demo.R;

/**
 * Created by lwenkun on 2017/4/13.
 */

public class TouchEventTestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_touch_event_test);
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
