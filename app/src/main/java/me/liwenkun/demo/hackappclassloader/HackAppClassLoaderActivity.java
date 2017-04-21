package me.liwenkun.demo.hackappclassloader;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import dalvik.system.DexFile;
import me.liwenkun.demo.R;
import me.liwenkun.demo.hookams.AMSHookHelper;
import me.liwenkun.demo.hookams.PMSHookHelper;

public class HackAppClassLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack_app_class_loader);
        findViewById(R.id.btn_hack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hackClassLoader();
                Intent i = new Intent();
                i.setComponent(new ComponentName("com.guokr.fanta", "com.guokr.fanta.ui.activity.SplashActivity"));
                startActivity(i);
//                PackageInfo packageInfo = getPackageManager().getPackageArchiveInfo(Environment.getExternalStorageDirectory() + "/fenda.apk", 0);
//                Intent intent = getPackageManager().getLaunchIntentForPackage(packageInfo.packageName);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
            }
        });

        AMSHookHelper.hook(this);
        PMSHookHelper.hook(this);

    }

    // failed, I don't know the reason
    // log: Caused by: java.io.IOException: No original dex files found for dex location /storage/emulated/0/dex.dex
    // solved, because I forget to get runtime permission for this application :P
    // tips: if you just run demo on your machine and are too lazy to write code about runtime permissions,
    // just declare permission in your manifest. After the app run on the target machine, go to Settings to turn on all the permission
    // you have declared
    private void hackClassLoader() {
        try {
            Class<?> clss = Class.forName("dalvik.system.BaseDexClassLoader");
            ClassLoader classLoader = getClassLoader();
            Field pathListField = clss.getDeclaredField("pathList");
            pathListField.setAccessible(true);
            Object dexPathList = pathListField.get(classLoader);
            Class<?> dexPathListClass = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathListClass.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);
            Object dexElements = dexElementsField.get(dexPathList);
            Class<?> elementClass = dexElements.getClass().getComponentType();

            Object newDexElements = Array.newInstance(elementClass, Array.getLength(dexElements) + 1);
            System.arraycopy(dexElements, 0, newDexElements, 1, Array.getLength(dexElements));

            Object dexFile = DexFile.loadDex(Environment.getExternalStorageDirectory() + "/fenda.apk", getDir("out", MODE_PRIVATE).getAbsolutePath() +
                     "/dex.dex", 0);

            Constructor<?> constructor = elementClass.getConstructor(File.class, boolean.class, File.class, DexFile.class);
            Array.set(newDexElements, 0, constructor.newInstance(new File(""), false, new File(Environment.getExternalStorageDirectory(), "fenda.apk"), dexFile));
            dexElementsField.set(dexPathList, newDexElements);

            //由于 Android 7.0 以上 BaseDexClassLoader 增加了 addDexPath 隐藏接口，因此给 ClassLoader 增加 dex path 就只需要以下几行，方便了很多
//            PathClassLoader pathClassLoader = (PathClassLoader) getClassLoader();
//            Method addDexPathMethod = BaseDexClassLoader.class.getDeclaredMethod("addDexPath", String.class);
//            addDexPathMethod.setAccessible(true);
//            addDexPathMethod.invoke(pathClassLoader, Environment.getExternalStorageDirectory() + "/dex.dex");

          //  Class.forName("me.liwenkun.demo.Test").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
