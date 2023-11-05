package com.chris.jvm;

import org.junit.Test;

public class StackTest {

    private static int i = 10;

    /**
     * 没有设置栈大小时：11344
     * 11344
     * java.lang.StackOverflowError
     * at sun.nio.cs.UTF_8$Encoder.encodeLoop(UTF_8.java:691)
     * 设置栈大小时：-Xss256k
     * 2263
     * java.lang.StackOverflowError
     * at sun.nio.cs.UTF_8.updatePositions(UTF_8.java:77)
     */
    @Test
    public void testStack() {
        System.out.println(i);
        i++;
        testStack();
    }

}
