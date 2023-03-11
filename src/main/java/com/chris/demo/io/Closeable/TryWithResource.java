package com.chris.demo.io.Closeable;

import org.junit.Test;

/**
 * @Author Lilun
 * @Date 2021-03-24 10:36
 * @Description 关于带资源的try语句的3个关键点：
 * 1. 由带资源的try语句管理的资源必须是实现了AutoCloseable接口的类的对象。
 * 2. 在try代码中声明的资源被隐式声明为fianl。
 * 3. 通过使用分号分隔每个声明可以管理多个资源。
 **/
public class TryWithResource {

    /**
     * AutoCloseable接口，表示一种不再使用时需要关闭的资源。这个接口下只有一个方法，close()。这个方法在try-with-
     * resource语法下会被自动调用，支持抛出Exception，当然它也鼓励抛出更详细的异常。close()建议不要抛出线程中断的
     * InterruptedException。对这个接口的实现，规范强烈建议close()是幂等的，也就是说多次调用close()方法和一次调用的结
     * 果是一样的。
     * JDK1.7 新特性
     */
    @Test
    public void TestTryWithResource() {
        try (MyResource1 resource1 = new MyResource1(); MyResource2 resource2 = new MyResource2()) {
            resource1.readResource();
            resource2.readResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
