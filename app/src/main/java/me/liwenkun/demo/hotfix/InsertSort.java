package me.liwenkun.demo.hotfix;

import android.os.Binder;

public class InsertSort implements Sort {

    private Binder binder = new Binder() {
        public void cake() {}
    };

    @Override
    public String getName() {
        return "插入排序";

    }

    @Override
    public void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int pos = i;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] < key) break;
                a[j + 1] = a[j];
                pos = j;
            }
            a[pos] = key;
        }
        System.out.println("I'm InsertSort");
    }

}