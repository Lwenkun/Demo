package me.liwenkun.demo.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;


/**
 * Created by lwenkun on 2017/5/7.
 */

public class ImageView extends android.support.v7.widget.AppCompatImageView {

    private static final String TAG = "ImageView";
    public ImageView(Context context) {
        super(context);
    }

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        Log.d(TAG, canvas.getClipBounds() + ";" + canvas.getWidth() + ";" + canvas.getHeight());
        Log.d(TAG, "onDraw() was called");
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setDither(true);
        Path path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawArc(new RectF(2, 2, 1000, 1000), 0, 360, false, paint);
        canvas.clipRect(new Rect(0, 0, 200, 200), Region.Op.INTERSECT);
        Log.d(TAG, canvas.getClipBounds() + ";" + canvas.getWidth() + ";" + canvas.getHeight());
        canvas.restore();
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
