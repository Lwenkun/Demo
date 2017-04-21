package me.liwenkun.demo.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.liwenkun.demo.R;
import me.liwenkun.demo.animation.ViewAnimationActivity;

public class ScrollingActivity extends AppCompatActivity {

    private static final int REQUEST_TAKE_PICTURE = 0x00;
    private static final int REQUEST_TAKE_VIDEO = 0x01;
    private FloatingActionButton fab;

    private MediaScanner mediaScanner;

    private Preview preview;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preview = (Preview) findViewById(R.id.pic_iv);

        findViewById(R.id.btn_view_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScrollingActivity.this, ViewAnimationActivity.class));
            }
        });



        findViewById(R.id.btn_start_transition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View sceneRoot = findViewById(R.id.scene_root);

                Scene endScene = Scene.getSceneForLayout(((ViewGroup) sceneRoot), R.layout.transition_layout, ScrollingActivity.this);
                android.support.transition.Transition transition = new android.support.transition.ChangeBounds();

                TransitionManager.go(endScene, transition);
            }
        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Toast.makeText(ScrollingActivity.this, "you should install a camera", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ActivityCompat.checkSelfPermission(ScrollingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(ScrollingActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(ScrollingActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 0);
                else if(safeOpenCamera(Camera.CameraInfo.CAMERA_FACING_BACK)) {
                    preview.setCamera(camera);
                }
                 //   startCamera();
//                captureVideo();
            }
        });
    }



    private void captureVideo() {
        Intent captureVideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (captureVideo.resolveActivity(getPackageManager()) != null) {
            File tempFile = null;
            try {
                tempFile = createTempFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (tempFile != null ) {
           //     Uri uri = FileProvider.getUriForFile(this, "net.bingyan.hustpass.fileprovider", )
            }
            startActivityForResult(captureVideo, REQUEST_TAKE_VIDEO);
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(currentFile);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Camera camera;



    @Override
    protected void onPause() {
        super.onPause();
     //   releaseCameraAndPreview();
       // camera.stopPreview();
//        camera.stopPreview();
//        camera.release();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
     //   startCamera();
    }



    private File currentFile;

    public void takePicture() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePicture.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;

            try  {
                imageFile = createTempFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (imageFile != null) {
                currentFile = imageFile;
                Uri uri = FileProvider.getUriForFile(ScrollingActivity.this, "net.bingyan.hustpass.fileprovider", imageFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(takePicture, REQUEST_TAKE_PICTURE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                Log.d(this.getClass().getName(), array2String(permissions));
                Log.d(this.getClass().getName(), "" + grantResults.length);
                if (isAllPermissionGranted(grantResults)) {
                    //takePicture();
                    //startCamera();
                    if(safeOpenCamera(Camera.CameraInfo.CAMERA_FACING_BACK)) {
                        preview.setCamera(camera);
                    }

                } else  {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 0);
                }
        }
    }

    private boolean safeOpenCamera(int id) {

        boolean opened = false;

        try {
            releaseCameraAndPreview();
            camera = Camera.open(id);
            opened = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return opened;
    }

    private void releaseCameraAndPreview() {
        preview.setCamera(null);
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    public String array2String(String[] p) {
        StringBuilder sb = new StringBuilder();
        for (String d : p) {
            sb.append(d);
        }
        return sb.toString();
    }

    public boolean isAllPermissionGranted(int[] grantResults) {
        for(int r : grantResults) {
            if (r == PackageManager.PERMISSION_DENIED) return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_VIDEO) {
            if (resultCode == RESULT_OK) {

//                if (data == null) return;
//                Toast.makeText(this, "ffadfadf", Toast.LENGTH_SHORT).show();
//                ((VideoView)findViewById(R.id.pic_iv)).setVideoURI(data.getData());
//                ((VideoView)findViewById(R.id.pic_iv)).start();
////                galleryAddPic();
//                ContentValues values = new ContentValues();
//
//                values.put(MediaStore.Video.Media.DATE_TAKEN, System.currentTimeMillis());
//                values.put(MediaStore.Video.Media.MIME_TYPE, "video/*");
//
//                values.put(MediaStore.MediaColumns.DATA, data.getDataString());
//
//                getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
              //  if (mediaScanner == null) mediaScanner = new MediaScanner(this);
//                MediaScannerConnection.scanFile(this,
//                        new String[] { currentFile.getAbsolutePath() }, null,
//                        new MediaScannerConnection.OnScanCompletedListener() {
//                            public void onScanCompleted(String path, Uri uri) {
//                                Log.i("ExternalStorage", "Scanned " + path + ":");
//                                Log.i("ExternalStorage", "-> uri=" + uri);
//                            }
//                        });
              //  mediaScanner.scanFile(new MediaScanner.MediaFile(currentFile.getAbsolutePath(), "image/jpg"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Bitmap decodeBitmap(File file, ImageView iv) {

        int width = iv.getWidth();
        int height = iv.getHeight();

        if (file == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentFile.getAbsolutePath(), options);

        int picWidth = options.outWidth;
        int picHeight = options.outHeight;

        int inSampleSize = 1;

        if (picWidth > width || picHeight > height) {
            int targetWidth = picWidth;
            int targetHeight = picHeight;
            while (targetHeight / 2 > height || targetWidth / 2 > width) {
                inSampleSize *= 2;
                targetHeight /= 2;
                targetWidth /= 2;
            }
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(currentFile.getAbsolutePath(), options);
    }

    public File createTempFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.SIMPLIFIED_CHINESE).format(new Date());
        String fileName = "JEPG_" + timeStamp + "_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (isExternalStorage()) {
            Log.d(getClass().getName(), dir.getAbsolutePath() +";" + dir.getCanonicalPath() + ";" + dir.getName() + ";" +
            dir.getParent() + ";" + dir.getPath() + ";" + dir.getParentFile().toString());
            File test = new File("cad");
            Log.d(getClass().getName(), test.getAbsolutePath() +";" + test.getCanonicalPath() + ";" + test.getName() + ";" +
                    test.getParent() + ";" + test.getPath() + ";" );
            return File.createTempFile(fileName, ".jpg", dir);
        }
        return null;
    }

    public boolean isExternalStorage() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
            return true;
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaScanner != null) mediaScanner.realease();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
