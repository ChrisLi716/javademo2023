package com.chris.thread.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Chris
 * @date 2022-03-19 2:33 PM
 */
public class CAS_ABA2 {

    private static final AtomicStampedReference<Integer> count = new AtomicStampedReference<>(1, 1);


    public static void main(String[] args) {
        Thread mainThread = new Thread(() -> {
            System.out.println("thread name:" + Thread.currentThread().getName() + ", init reference:" + count.getReference() + ", init stamp:" + count.getStamp());
            Integer expectReference = count.getReference();
            int expectStamp = count.getStamp();

            Integer newReference = expectReference + 1;
            int newStamp = expectStamp + 1;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isSucceed = count.compareAndSet(expectReference, newReference, expectStamp, newStamp);
            System.out.println("thread name:" + Thread.currentThread().getName() + ", CAS result:" + isSucceed + ", " + "reference:" + count.getReference() + ", stamp:" + count.getStamp());
        }, "main-thread");


        Thread otherThread = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
                count.compareAndSet(count.getReference(), count.getReference() + 1, count.getStamp(),
                        count.getStamp() + 1);
                System.out.println("thread name:" + Thread.currentThread().getName() + ", increment reference:" + count.getReference() + ", stamp:" + count.getStamp());

                count.compareAndSet(count.getReference(), count.getReference() - 1, count.getStamp(),
                        count.getStamp() + 1);
                System.out.println("thread name:" + Thread.currentThread().getName() + ", decrement reference:" + count.getReference() + ", stamp:" + count.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "other-thread");

        mainThread.start();
        otherThread.start();
    }


}
