package me.liwenkun.demo.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by lwenkun on 2017/2/26.
 */

public class Preview extends FrameLayout implements SurfaceHolder.Callback {

    private SurfaceHolder holder;

    private Camera camera;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Preview", "surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("Preview", "surfaceChanged");
        if (camera == null) return;
//        Camera.Parameters parameters = camera.getParameters();
//        parameters.setPreviewSize(width, height);
//        requestLayout();
//        camera.setParameters(parameters);
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null ) {
            camera.stopPreview();
        }
        //Toast.makeText(getContext(), "surface has been destroyed", Toast.LENGTH_SHORT).show();
        Log.d("Preview", "surface has been destoryed");
    }

    public Preview(Context context) {
        super(context);
    }

    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceView surfaceView = new SurfaceView(context);
        this.addView(surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
    }

    public Preview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private List<Camera.Size> mSupportedPreviewSize;

    private void stopPreviewAndFreeCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    public void setCamera(Camera camera) {

        stopPreviewAndFreeCamera();

        this.camera = camera;


        if (this.camera != null) {
            camera.setDisplayOrientation(90);
            List<Camera.Size> localSizes = this.camera.getParameters().getSupportedPreviewSizes();
            mSupportedPreviewSize = localSizes;
            requestLayout();

            try {
                camera.setPreviewDisplay(holder);
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.camera.startPreview();
        }
    }





//    private void startCamera() {
//        if (safeOpenCamera(Camera.CameraInfo.CAMERA_FACING_BACK)) {
//            ((Preview)findViewById(R.id.pic_iv)).setCamera(camera);
//        }
//    }
}
