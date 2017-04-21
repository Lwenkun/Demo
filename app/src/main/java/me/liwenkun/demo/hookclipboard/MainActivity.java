package me.liwenkun.demo.hookclipboard;

import android.content.ClipData;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import me.liwenkun.demo.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //get binder proxy for clipboard manager service
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Method getServiceMethod = serviceManager.getDeclaredMethod("getService", String.class);
            getServiceMethod.setAccessible(true);
            IBinder clipboardProxy = (IBinder) getServiceMethod.invoke(null, "clipboard");

            IBinder hookedProxy = (IBinder) Proxy.newProxyInstance(getClassLoader(),
                    new Class[]{IBinder.class}, new BinderProxyHookedHandler(clipboardProxy));

            Field sCacheField = serviceManager.getDeclaredField("sCache");
            sCacheField.setAccessible(true);
            Map<String, IBinder> sCache = (Map) sCacheField.get(null);
            sCache.put("clipboard", hookedProxy);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * hook clipboard manager
     */
    private class ClipboardHookedHandler implements InvocationHandler {

        // Service Interface
        IBinder rowBinder;

        // real
        Object manager;

        public ClipboardHookedHandler(IBinder binderProxy) {
            rowBinder = binderProxy;
            try {
                // we should get real clipboard manager to handle things that we don't want
                Class<?> IClipBoard = getClass().getClassLoader().loadClass("android.content.IClipboard$Stub");
                Method asInterfaceMethod = IClipBoard.getDeclaredMethod("asInterface", IBinder.class);
                manager =  asInterfaceMethod.invoke(null, binderProxy);
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // intercept
            if ("hasPrimaryClip".equals(method.getName())) {
                return true;
            }
            // intercept
            if ("getPrimaryClip".equals(method.getName())) {
                return ClipData.newPlainText(null, "you are hooked");
            }

            return method.invoke(manager, args);
        }
    }

    /**
     * hook binder proxy got from ServiceManager
     */
    private class BinderProxyHookedHandler implements InvocationHandler {

        // real
        private IBinder binderProxy;
        private Class<?> iClipBoardClass;

        private BinderProxyHookedHandler(IBinder binderProxy) {
            this.binderProxy = binderProxy;

            try {
                iClipBoardClass = Class.forName("android.content.IClipboard");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // intercept
            if ("queryLocalInterface".equals(method.getName())) {
                //  Log.d("MainActivity", "return hooked binder");
                return Proxy.newProxyInstance(getClassLoader(), new Class[]{IInterface.class, iClipBoardClass}
                        , new ClipboardHookedHandler(binderProxy));
            }

            return method.invoke(binderProxy, args);
        }

    }
}

