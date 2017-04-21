package me.liwenkun.demo.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by lwenkun on 2017/4/16.
 */

public class CustomImageView extends AppCompatImageView {

    private Canvas mcanvas;
    private Bitmap bitmap;

    public CustomImageView(Context context) {
        super(context);

    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
//        if (mcanvas == null) {
//            bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
//            mcanvas = new Canvas(bitmap);
//        }
        super.onDraw(canvas);
    }

    public void saveToFile (Bitmap b) {
        if (b != null) {
            try {
                FileOutputStream out = getContext().openFileOutput("random", Context.MODE_PRIVATE);
                b.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
