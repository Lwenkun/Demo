// BinderReceiver.aidl
package me.liwenkun.demo;

// Declare any non-default types here with import statements

interface BinderReceiver {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void receiveBinder(in IBinder binder);
}
