package me.liwenkun.demo.hotfix;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;

import dalvik.system.DexClassLoader;
import me.liwenkun.demo.R;
import me.liwenkun.demo.hookclipboard.MainActivity;

/**
 * Created by lwenkun on 2017/3/8.
 */

public class HotFixActivity extends AppCompatActivity {

    private static String TAG = "HotFixActivity";

    private TextView tvResult;
    private static final int[] collection = {21, 70, 1, 88, 4, 54, 22, 10, 9, 104, 37};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hot_fix);
        tvResult = (TextView) findViewById(R.id.tv_result);
        tvResult.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, event.getX() + ";" + event.getY());
                if (event.getAction() == MotionEvent.ACTION_UP) Toast.makeText(HotFixActivity.this, "action up", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotFixActivity.this, MainActivity.class));
            }
        });


        tvResult.measure(View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec((1 << 30 ) - 1, View.MeasureSpec.AT_MOST));

        tvResult.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("dddddddd" + tvResult.getWidth());
                System.out.println("dddddddd" + tvResult.getHeight());
            }
        }, 2000);

        Log.d("SortActiviiy", "tvResult -->  width:" + tvResult.getMeasuredWidth() + ", height:" + tvResult.getMeasuredHeight());
        Sort sort;
        if (hasUpdate()) { //如果排序算法有更新
            sort = getNewSort(); // 应用新的排序算法
        } else {
            sort = getDefaultSort(); // 否则用默认实现的排序算法
        }
        sort.sort(collection);
        // 显示排序结果
        StringBuilder result = new StringBuilder(sort.getName() + ": ");
        for (int i = 0; i < collection.length; i++) {
            result.append(collection[i]);
            if (i != collection.length - 1) result.append(", ");
        }
        tvResult.setText(result.toString());
    }

    private Sort getNewSort() {
        Sort newSort = null;
        try {
            Class<?> clazz = getNewSortClass(); // 获取新的 Sort 实现类
            newSort = (Sort) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newSort; // 用该实现类实现的新的排序算法对
    }


    private boolean hasUpdate(){
        try {
            FileReader reader = new FileReader(Environment.
                    getExternalStorageDirectory().getAbsolutePath()
                    + "/has_update.txt");
            int hasUpdate = reader.read();
            System.out.println("hasUpdate:" + hasUpdate);
            return hasUpdate == '1';
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private Class<?> getNewSortClass() throws ClassNotFoundException {
//        // 从服务器下载到本地磁盘
//        downLoadCodeFromServer();
        // 假定下载的 .dex 文件目录为应用私有文件目录，文件名为 newsort.dex
        String path =  Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "newsort.dex";
        String optimizedDirectory = getDir("code", MODE_PRIVATE).getAbsolutePath();
        // 创建一个 DexClassLoader，用来加载从网络上下载的 .dex 文件
        DexClassLoader loader = new DexClassLoader(path, optimizedDirectory, null, getClassLoader());
        return loader.loadClass("me.liwenkun.demo.NewSort");
    }

    private Sort getDefaultSort() {
        return new InsertSort();
    }
}
