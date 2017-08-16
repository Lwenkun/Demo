package me.liwenkun.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by lwenkun on 2017/4/21.
 */

public class ReflectionUtils {

    public static Class<?> get_class(String name) {

          Class<?> result = null;
        try {
            result = Class.forName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Field get_field(String className, String fieldName) {
        Class<?> clss = get_class(className);
        return get_field(clss, fieldName);

    }

    public static Object get_field_object(String className, String fieldName, Object host) {
        Object object = null;
        Field field = get_field(className, fieldName);
        return get_field_object(field, host);

    }

    public static Object get_field_object(Field field, Object host) {
        Object object = null;
        try {
            if (field != null) {
                if ((field.getModifiers() & Modifier.STATIC)  == Modifier.STATIC) {
                    object = field.get(null);
                } else if (host != null) {
                    object = field.get(host);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static Field get_field(Class<?> clss, String fieldName) {
        Field result = null;
        // Class<?> clss = get_class(className);
        if (clss != null) {
            try {
                result = clss.getDeclaredField(fieldName);
                result.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
