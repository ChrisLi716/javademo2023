package com.chris.thread;

import java.util.stream.Stream;

public class ThreadUtil {

    public static void join(Thread... threads) {
        Stream.of(threads).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
