package com.chris.thread.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCount {
    static AtomicInteger atomicCount = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 0; i < threadSize; i++) {
            new Thread(() -> {
                // 模拟每个用户访问10次网站
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();

        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ",cost:" + (endTime - startTime) + ",count:" + atomicCount.get());
    }

    /**
     * Q:问题出在哪里?
     * A:count++ 实际上是由三步组成
     * 1. 获取count的值，记做A，A=count
     * 2. 将A值+1，得到B，B=A+1
     * 3. 将B值赋值给count
     * 如果A,B两个线程同步执行到步骤1得到的count是一样的，到第3步执行完成后，count都只加1，导致赋值给count结果不正确
     * <p>
     * Q:怎么解决这个问题
     * A: 方案1 ：见CountDemo
     * 对count++操作的时候，我们让多个线程排队处理，多个线程同时到达request()方法时，只能允许一个线程可以进去操作，
     * 其它线程在外面等候，等里面的处理完毕出来之后，外面等候的线程再进去一个，这样操作count++就是排队串行执行，结果一定是正确的
     * A: 方案2 ：见AtomicCount
     * <p>
     * main,cost:146,count:1000
     * main,cost:144,count:1000
     * main,cost:140,count:1000
     */
    private static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        atomicCount.incrementAndGet();
    }


}
