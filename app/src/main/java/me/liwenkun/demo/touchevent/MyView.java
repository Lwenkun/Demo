package me.liwenkun.demo.touchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by lwenkun on 2017/4/6.
 */

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("MyView", "onMeasure called");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("MyView", "onLayout called");
    }

    private float currX;
    private float currY;

    private float lastX;
    private float lastY;

    ViewConfiguration configuration = ViewConfiguration.get(getContext());


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        boolean consumed = super.dispatchTouchEvent(event);
        Log.d("MyView", consumed + "");
        return consumed;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("MyView", getLeft() + ";" + getTop() + ";" + getRight() + ";" + getBottom());

        currX = event.getRawX();
        currY = event.getRawY();

        Log.d("MyView", "onTouchEvent:" + Integer.toHexString(event.getAction()));

        Log.d("MyView", "event.x=" + event.getX() + ";event.y=" + event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                lastX = currX;
                lastY = currY;

                break;
            case MotionEvent.ACTION_MOVE:

                float diffX = currX - lastX;
                float diffY = currY - lastY;

                lastX = currX;
                lastY = currY;


                Log.d("MyView" , "width=" + getWidth() + ";height=" + getHeight() + ";left=" + getLeft() + ";right=" + getRight());

//                if (diffX > configuration.getScaledTouchSlop()) {
//                    setLeft((int)(getLeft() + diffX));
//                    setRight((int)(getRight() + diffX));
////                }
//
////                if (diffY > configuration.getScaledTouchSlop()) {
//                    setTop((int)(getTop() + diffY));
//                    setBottom((int)(getBottom() + diffY));
////                }

                setTranslationX(getTranslationX() + diffX);
                setTranslationY(getTranslationY() + diffY);

                return false;

            case MotionEvent.ACTION_DOWN:
                lastX = currX;
                lastY = currY;

                return true;
        }



        return false;

       // return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MyView", "onDraw");
    }
}
