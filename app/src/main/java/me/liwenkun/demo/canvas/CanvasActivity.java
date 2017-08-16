package me.liwenkun.demo.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.liwenkun.demo.R;

public class CanvasActivity extends AppCompatActivity {

    private Canvas mcanvas;
    private Bitmap bitmap;
    CustomImageView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        v = ((CustomImageView)findViewById(R.id.iv_picture));

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vd) {
                v.draw(mcanvas);
                v.saveToFile(bitmap);
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (mcanvas == null) {
            bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
            mcanvas = new Canvas(bitmap);
        }
    }
}
