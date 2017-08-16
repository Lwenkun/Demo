package me.liwenkun.demo.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;


/**
 * Created by lwenkun on 2017/5/7.
 */

public class Button extends AppCompatButton {

    private static final String TAG = "Button";

    public Button(Context context) {
        super(context);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw() was called");

        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.d(TAG, "dispatchDrawIO was called");

        super.dispatchDraw(canvas);

    }

    @Override
    public void draw(Canvas canvas) {
        Log.d(TAG, "draw() was called");

        super.draw(canvas);
    }
}
