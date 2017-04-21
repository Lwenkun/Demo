package me.liwenkun.demo.camera;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lwenkun on 2017/2/24.
 */

public class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

    private boolean paused = true;

    private BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

    public static final class MediaFile {
        public MediaFile(String file, String mimeType) {
            this.file = file;
            this.mimeType = mimeType;
        }
        private String file;
        private String mimeType;
    }

    private MediaScannerConnection mediaScannerConnection;

    public MediaScanner(Context context) {
        mediaScannerConnection = new MediaScannerConnection(context, this);
    }

    @Override
    public void onMediaScannerConnected() {
        scan();
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        Log.d("MediaScanner", "path:" + path + "; uri:" + uri);
    }

    private void scan() {
        if (! paused || ! mediaScannerConnection.isConnected()) return;
        paused = false;
        Runnable task;
        while((task = taskQueue.poll()) != null) {
            task.run();
        }
        paused = true;
    }

    public void scanFiles(List<MediaFile> mediaFiles) {
        if (! mediaScannerConnection.isConnected()) {
            mediaScannerConnection.connect();
        }
        postScanningWork(new ScanningWork(mediaFiles));
    }

    public void scanFile(MediaFile mediaFile) {
        if (! mediaScannerConnection.isConnected()) {
            mediaScannerConnection.connect();
        }
        postScanningWork(new ScanningWork(mediaFile));
    }

    private void postScanningWork(Runnable work) {
        taskQueue.offer(work);
        scan();
    }

    private final class ScanningWork implements Runnable {

        private List<MediaFile> mediaFiles = new ArrayList<>();

        public ScanningWork(List<MediaFile> mediaFiles) {
            this.mediaFiles.addAll(mediaFiles);
        }

        public ScanningWork(MediaFile mediaFile) {
            mediaFiles.add(mediaFile);
        }

        @Override
        public void run() {
            for (MediaFile f : mediaFiles)
                mediaScannerConnection.scanFile(f.file, f.mimeType);
        }
    }

    public void realease() {
        if (mediaScannerConnection.isConnected())
            mediaScannerConnection.disconnect();
    }

}
