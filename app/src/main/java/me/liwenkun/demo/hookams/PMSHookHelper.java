package me.liwenkun.demo.hookams;

import android.content.ContextWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * Created by lwenkun on 2017/4/14.
 */

public class PMSHookHelper {

    private static Class<?> activityThreadClass;
    private static Class<?> iPackageManagerClass;
    private static Object sPackageManager;

    static {

        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            iPackageManagerClass = Class.forName("android.content.pm.IPackageManager");
            sPackageManager = getSPackageManagerField().get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hook(ContextWrapper contextWrapper) {

        try {
            Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
            Field mPackageManagerField = contextImplClass.getDeclaredField("mPackageManager");
            mPackageManagerField.setAccessible(true);
            mPackageManagerField.set(contextWrapper.getBaseContext(), null);


            Object hookedPackageManager = Proxy.newProxyInstance(PMSHookHelper.class.getClassLoader(), new Class[]{iPackageManagerClass},
                    new PMSHookHanlder(sPackageManager));

            getSPackageManagerField().set(null, hookedPackageManager);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void unHook() {
        Field sPackageManagerField = getSPackageManagerField();
        try {
            sPackageManagerField.set(null, sPackageManager);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static Field getSPackageManagerField() {

        Field sPackageManagerField = null;
        try {
            sPackageManagerField = activityThreadClass.getDeclaredField("sPackageManager");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (sPackageManagerField != null)
        sPackageManagerField.setAccessible(true);
        return sPackageManagerField;
    }


}
