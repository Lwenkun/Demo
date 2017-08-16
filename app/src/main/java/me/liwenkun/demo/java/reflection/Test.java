package me.liwenkun.demo.java.reflection;

/**
 * Created by lwenkun on 2017/7/5.
 */

public class Test extends AbsTest {

    int a = 20;

    @Override
    public void test1() {

        System.out.println(((AbsTest)this).a);

        System.out.println("test1 has been invoked");

        System.out.println("this a:" + this.a + "; super a:" + super.a);
    }
}
