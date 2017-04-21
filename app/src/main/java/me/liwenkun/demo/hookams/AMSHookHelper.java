package me.liwenkun.demo.hookams;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * Created by lwenkun on 2017/4/14.
 */

public class AMSHookHelper {

    private static String TAG = "AMSHookHelper";

    private static boolean hasHooked = false;

    static Intent target;

    private static Intent placeHolderIntent;

    public static Intent getPlaceHolderIntent() {
        return placeHolderIntent;
    }

    public static void hook(final Context context) {

        if (hasHooked) return;

        if (placeHolderIntent == null) {
            placeHolderIntent = new Intent();
            placeHolderIntent.setComponent(new ComponentName(context, PlaceHolderActivity.class));
        }

        hookAMSProxy(context);
        hookActivityThread();

        hasHooked = true;
    }

    private static void hookActivityThread() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            Object sCurrentActivityThread = sCurrentActivityThreadField.get(null);

            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            final Handler mH = (Handler) mHField.get(sCurrentActivityThread);

            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            mCallbackField.set(mH, new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Log.d(TAG, "msg" + msg + " has been intercepted");
                    switch (msg.what) {
                        case 100:
                            handleHookActivity(msg);
                            break;
                    }

                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hookAMSProxy (Context context) {

        try {
            Class<?> activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
            Field gDefaultField = activityManagerNativeClass.getDeclaredField("gDefault");
            gDefaultField.setAccessible(true);
            Object gDefault = gDefaultField.get(null);
            Field mInstanceField = gDefaultField.getType().getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            final Object mInstance = mInstanceField.get(gDefault);

            Class<?> iActivityManagerClass = Class.forName("android.app.IActivityManager");

            Object activityManagerProxy = Proxy.newProxyInstance(AMSHookHelper.class.getClassLoader(), new Class[]{
                    iActivityManagerClass}, new IActivityManagerInvocationHandler(mInstance));

            mInstanceField.set(gDefault, activityManagerProxy);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void handleHookActivity(Message message) {
        // ActivityClientRecord
        Object object = message.obj;

        try {
            Field intentField = object.getClass().getDeclaredField("intent");
            intentField.setAccessible(true);
            Intent origin = target;
            Log.d(TAG, "intent from AMS:" + intentField.get(object) + "\n" +  "origin target:" + origin);
            intentField.set(object, origin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
