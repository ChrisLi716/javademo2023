package com.chris.thread.future.lock;

import com.chris.thread.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {


    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    @Test
    public void test01() {
        Producer producer = new Producer();
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        Consumer consumer3 = new Consumer();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        producer.start();

        ThreadUtil.join(consumer1, consumer2, consumer3, producer);
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {

            try {
                lock.lock();
                System.out.println("我在等一个新信号" + currentThread().getName());
                condition.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("拿到一个信号" + currentThread().getName());
                lock.unlock();
            }

        }
    }

    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            try {
                lock.lock();
                System.out.println("我拿到锁" + currentThread().getName());
                condition.signal();
                System.out.println("我发出了一个信号：" + currentThread().getName());
            } finally {
                lock.unlock();
            }
        }
    }

}
