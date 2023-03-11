package com.chris.thread.cas;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chris
 * @date 2022-03-19 2:33 PM
 */
public class CAS_ABA {

    private static AtomicInteger count = new AtomicInteger(1);


    public static void main(String[] args) {
        Thread mainThread = new Thread(() -> {
            System.out.println("thread name:" + Thread.currentThread().getName() + ", initValue:" + count.get());
            int expectValue = count.get();
            int newValue = expectValue + 1;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isSucceed = count.compareAndSet(expectValue, newValue);
            System.out.println("thread name:" + Thread.currentThread().getName() + ", CAS result:" + isSucceed);
        }, "main-thread");


        Thread otherThread = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
                int incrementValue = count.incrementAndGet();
                System.out.println("thread name:" + Thread.currentThread().getName() + ", increment value:" + incrementValue);
                int decrementValue = count.decrementAndGet();
                System.out.println("thread name:" + Thread.currentThread().getName() + ", decrement value:" + decrementValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "other-thread");

        mainThread.start();
        otherThread.start();
    }


}
