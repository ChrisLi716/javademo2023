package com.chris.thread.future.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class DeadLockTest {

    @Test
    public void test01() throws InterruptedException {
        MyThread1 myThread = new MyThread1();
        Thread thread1 = new Thread(myThread::incr);
        Thread thread2 = new Thread(myThread::decr);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    static class MyThread1 {
        private Integer count = 0;


        public synchronized void incr() {
            System.out.println("begin incr");
            ++count;
            System.out.println(Thread.currentThread().getName() + ", count+1=" + count);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            decr();
            System.out.println("end incr");
        }

        public synchronized void decr() {
            System.out.println("begin decr");
            --count;
            System.out.println(Thread.currentThread().getName() + ", count-1=" + count);
            System.out.println("end decr");
        }
    }


    @Test
    public void test02() throws InterruptedException {
        MyThrad02 myThrad02 = new MyThrad02();
        Thread thread1 = new Thread(myThrad02::o1);
        Thread thread2 = new Thread(myThrad02::o2);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }


    static class MyThrad02 {
        private final Object o1 = new Object();
        private final Object o2 = new Object();

        public void o1() {
            synchronized (o1) {
                System.out.println("begin o1");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o2) {
                    System.out.println("begin o2 in o1");
                }
            }
        }


        public void o2() {
            synchronized (o2) {
                System.out.println("begin o2");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o1) {
                    System.out.println("begin o1 in o2");
                }
            }
        }
    }

}
