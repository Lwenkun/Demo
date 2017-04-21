package me.liwenkun.demo.hookams;

import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * Created by lwenkun on 2017/4/14.
 */

public class IActivityManagerInvocationHandler implements InvocationHandler {

    private static String TAG = "InvocationHandler";

    private Object raw;

    public IActivityManagerInvocationHandler(Object raw) {
        this.raw = raw;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(TAG, "method " + method.getName() + " in " + method.getDeclaringClass().getName() + " has been called");
        if ("startActivity".equals(method.getName())) {
            int index = -1;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                AMSHookHelper.target = (Intent) args[index];

                args[index] = AMSHookHelper.getPlaceHolderIntent();
            } else
                throw new Exception("no Intent type args found");
        }

        return method.invoke(raw, args);
    }
}
