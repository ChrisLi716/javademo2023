package com.chris.thread.queue;

import com.chris.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Slf4j
public class BlockingQueueTest {

    private final static int CAPACITY = 10;
    private final BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(CAPACITY);

    private volatile boolean flag = true;


    @Test
    public void test01() {
        Thread thread1 = new Thread(() -> {
            try {
                while (flag) {
                    log.info("begin put data, avaiable space:{}", blockingQueue.remainingCapacity());
                    double random = Math.random();
                    blockingQueue.put(random);
                    log.info("put data {} success, avaiable space:{}", random, blockingQueue.remainingCapacity());
                }
            } catch (InterruptedException e) {
                flag = false;
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                while (flag) {
                    log.info("begin take data, avaiable space:{}", blockingQueue.remainingCapacity());
                    Object take = blockingQueue.take();
                    log.info("end take data {}, avaiable space:{}", take, blockingQueue.remainingCapacity());
                }
            } catch (InterruptedException e) {
                flag = false;
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        ThreadUtil.join(thread2, thread1);
    }


}
