package me.liwenkun.demo.hookams;

import android.content.pm.ActivityInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lwenkun on 2017/4/14.
 */

public class PMSHookHanlder implements InvocationHandler {

    public Object raw;

    public PMSHookHanlder(Object raw) {
        this.raw = raw;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("getActivityInfo".equals(method.getName())) {
            ActivityInfo activityInfo = new ActivityInfo();
            return  activityInfo;
        }
        return method.invoke(raw, args);
    }
}
