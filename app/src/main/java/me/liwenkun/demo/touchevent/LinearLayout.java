package me.liwenkun.demo.touchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lwenkun on 2017/4/7.
 */

public class LinearLayout extends android.widget.LinearLayout {
    public LinearLayout(Context context) {
        super(context);
    }

    public LinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("LinearLayout", "event has been handled by onTouch");
                return true;
            }
        });

        setBackgroundColor(0xaaaaaa);
    }

    public LinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float lastX ;
    private float lastY;

    private float currX;
    private float currY;

    boolean first = true;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        currX = ev.getX();
        currY = ev.getY();

        Log.d("LinearLayout", "onInterceptTouchEvent");

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

        }
         else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            float diffX = currX - lastX;
            float diffY = currY - lastY;
            if (! first && Math.abs(diffX) - Math.abs(diffY) > 0) return true;
            first = false;
           // if (Math.abs(diffX) - Math.abs(diffY) > 0) return true;
        }

        lastX = currX;
        lastY =  currY;
        return false;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.d("LinearLayout", "draw");
        super.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("LinearLayout", "onTouchEvent");
        return false;
//        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("LinearLayout", "event.x=" + ev.getX() +  ";event.y=" + ev.getY());
        Log.d("linearLayoout", "scrollX=" + getScrollX() + "scrollY=" + getScrollY());
        Log.d("linearLayout", "dispatchTouchEvent:" + "-----------");
        boolean consumed = super.dispatchTouchEvent(ev);
        Log.d("LinearLayout", "dispatchTouchEvent:" + consumed + "");
        return consumed;
    }
}
