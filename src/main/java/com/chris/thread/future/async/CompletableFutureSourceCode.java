package com.chris.thread.future.async;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @Author Lilun
 * @Date 2021-06-09 21:33
 * @Description
 **/
@Slf4j
public class CompletableFutureSourceCode {


    @SneakyThrows
    @Test
    public void asyn1() {
        CompletableFuture<String> cf = new CompletableFuture<>();

        //complete方法完成该Future,否则在调用cf.get()阻塞主线程，等待返回结果
        cf.complete("hello future!");

        //调用者阻塞，等待返回结果
        String s = cf.get();
        System.out.println(s);
    }

    @SneakyThrows
    @Test
    public void runAsync() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("task is running!");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> System.out.println("call back method after getting result!"));

        //主线程阻塞，等待任务执行完成
        cf.get();
    }

    @SneakyThrows
    @Test
    public void supplyAsync() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task has done!";
        }).thenApplyAsync(s -> s + ", call back method after getting result!");

        //主线程阻塞，等待任务执行完成
        String s = cf.get();
        System.out.println(s);
    }

    @SneakyThrows
    @Test
    public void supplyAsync2() {
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("task is running!");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task has done!";
        }).thenAccept(System.out::println);

        //主线程阻塞，等待任务执行完成
        cf.get();
    }

    @SneakyThrows
    @Test
    public void supplyAsync3() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //创建异步执行任务
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1, time:" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " exit job1, time:" + System.currentTimeMillis());
            return 1.2;
        });

        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        //thenApply这里实际创建了一个新的CompletableFuture实例
        /*
        thenApplyAsync与thenApply的区别在于
        前者是将job2提交到线程池中异步执行，实际执行job2的线程可能是另外一个线程
        后者是由执行job1的线程立即执行job2，即两个job都是同一个线程执行的
         */
        CompletableFuture<String> cf2 = cf.thenApplyAsync(result -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return "test:" + result;
        });

        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @SneakyThrows
    @Test
    public void testExceptionally() {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start, time->" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (false) {
                System.out.println("throw exception!");
                throw new RuntimeException("test exceptionally");
            }
            return 1.1;
        });

        CompletableFuture<Double> cf2 = cf.exceptionally((exception) -> {
            System.out.println(Thread.currentThread() + " start, time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("error stack trace->");
            exception.printStackTrace();
            System.out.println(Thread.currentThread() + " exit, time->" + System.currentTimeMillis());
            return 1.2;
        });
        System.out.println(cf2.get());
    }

    /**
     * 当某个任务执行完成后执行的回调方法，会将执行结果或者执行期间抛出的异常传递给回调方法，
     * 如果是正常执行则异常为null，回调方法对应的CompletableFuture的result和该任务一致，如果该任务正常执行，
     * 则get方法返回执行结果，如果是执行异常，则get方法抛出异常
     */
    @SneakyThrows
    @Test
    public void testWhenComplete() {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start, time->" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                System.out.println("throw exception!");
                throw new RuntimeException("test exceptionally");
            }
            return 1.1;
        });

        CompletableFuture<Double> cf2 = cf.whenComplete((result, exception) -> {
            System.out.println(Thread.currentThread() + " start, time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("error stack trace->");
            if (Objects.nonNull(exception)) {
                exception.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " exit, time->" + System.currentTimeMillis());
            System.out.println("result:" + result);
        });
        System.out.println(cf2.get());
    }

    /**
     * 跟whenComplete基本一致，区别在于handle的回调方法有返回值，
     * 且handle方法返回的CompletableFuture的result是回调方法的执行结果或者回调方法执行期间抛出的异常，
     * 与原始CompletableFuture的result无关了
     */
    @SneakyThrows
    @Test
    public void testHandle() {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start, time->" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                System.out.println("throw exception!");
                throw new RuntimeException("test exceptionally");
            }
            return 1.1;
        });

        CompletableFuture<String> handle = cf.handle((result, exception) -> {
            System.out.println(Thread.currentThread() + " start, time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("error stack trace->");
            if (Objects.nonNull(exception)) {
                exception.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " exit, time->" + System.currentTimeMillis());
            if (Objects.isNull(exception)) {
                return "run error";
            } else {
                return "run success";
            }
        });
        System.out.println(handle.get());
    }


    @Test
    public void testThenCompose() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1, time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " exit job1, time->" + System.currentTimeMillis());
            return 1.2;
        });

        CompletableFuture<String> cf2 = cf.thenCompose(param -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
                return "job3 test";
            });
        });
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("cf run result->" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        System.out.println("cf2 run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }
}
