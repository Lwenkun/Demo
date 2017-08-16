package me.liwenkun.demo.java.reflection;

import java.lang.reflect.Method;

/**
 * Created by lwenkun on 2017/7/5.
 */

/**
 * 反射获取某个类的方法只能够获取到该类中定义的方法或者该类重写父类的方法，
 * 不能够获取直接从父类继承下来并且没有重写的方法。
 */

public class ReflectionTest {
    public static void main(String[] args) {
        // 正常运行
        try {
            Method method =  Test.class.getDeclaredMethod("test1");
            method.invoke(new Test());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 运行时报错
        try {
            Method method = Test.class.getDeclaredMethod("test2");
            method.invoke(new Test());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 正常运行
        try {
            Method method = AbsTest.class.getDeclaredMethod("test1");
            method.invoke(new Test());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
