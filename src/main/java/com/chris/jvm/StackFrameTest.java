package com.chris.jvm;

import org.junit.Test;

public class StackFrameTest {

    /**
     * 从打印结果可以看到进栈和出栈的栈帧的执行顺序
     */
    @Test
    public void method1() {
        System.out.println("method1 begin");
        method2();
        System.out.println("method1 end");
    }

    private int method2() {
        System.out.println("method2 begin");
        int i = 3;
        int m = (int) method3();
        System.out.println("method2 going to end");

        return i + m;
    }

    private double method3() {
        System.out.println("method3 begin");
        double j = 20.0;
        System.out.println("method3 going to end");
        return j;
    }
}
