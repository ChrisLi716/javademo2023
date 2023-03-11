package com.chris.thread.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDemo2 {
    volatile static int count = 0;

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
        System.out.println(Thread.currentThread().getName() + ",cost:" + (endTime - startTime) + ",count:" + count);
    }

    /**
     * main,cost:6165,count:1000
     * main,cost:6213,count:1000
     * main,cost:5982,count:1000
     * <p>
     * Q：耗时太长的原因?
     * A：程序中的request()方法使用了Synchronozed修饰，保证并发情况下，request()只允许一个线程进入,
     * request()方法加锁相当于串行执行，count的结果和我们预期的一致，只是耗时太长
     * <p>
     * Q: 如何解决耗时长的问题?
     * A: count++ 实际上是由三步组成
     * 1. 获取count的值，记做A，A=count
     * 2. 将A值+1，得到B，B=A+1
     * 3. 将B值赋值给count
     * 升级第3步的实现
     * 1.获取锁
     * 2.获取count的最新值记做LV
     * 3.判断LV是否等于A，如果相等则将B赋值给count，并返回true，否则返回false
     * 4.如果返回true则释放锁，如果返回false刚从第1步 获取count的值重新开始
     *
     * main,cost:154,count:1000
     * main,cost:165,count:1000
     * main,cost:161,count:1000
     */
    private static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        while (!compareAndSwap(count, count + 1)) {
        }
    }


    /**
     * @param expectCount 期望值
     * @param newCount    需要给count赋予的新值
     * @return 成功返回true，失败返回false
     */
    public static synchronized boolean compareAndSwap(int expectCount, int newCount) {
        if (count == expectCount) {
            count = newCount;
            return true;
        }
        return false;
    }


}
