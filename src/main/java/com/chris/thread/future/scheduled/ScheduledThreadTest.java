package com.chris.thread.future.scheduled;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

public class ScheduledThreadTest {

    private ScheduledExecutorService scheduledExecutorService;

    @Before
    public void initScheduedExecutorService() {
        scheduledExecutorService = Executors.newScheduledThreadPool(2);
    }

    /**
     * 在指定延迟之后运行task。这个方法有个问题，就是没有办法获知task的执行结果
     * <p>
     * schedule with runnable
     * result:null
     */
    @Test
    public void scheduledWithRunnable() throws ExecutionException, InterruptedException {
        ScheduledFuture<?> scheduleWithRunnable = scheduledExecutorService.schedule(() ->
                System.out.println("schedule with runnable"), 2, TimeUnit.SECONDS);
        System.out.println("result:" + scheduleWithRunnable.get());
        scheduledExecutorService.shutdown();
    }

    /**
     * 与schedule (Runnable task)类似，也是在指定延迟之后运行task，不过它接收的是一个Callable实例
     * 此方法会返回一个ScheduleFuture对象，通过ScheduleFuture我们可以取消一个未执行的task，也可以获得这个task的执行结果
     * <p>
     * schedule with callable
     * result:callable success
     */
    @Test
    public void scheduledWithCallable() throws ExecutionException, InterruptedException {
        ScheduledFuture<?> scheduleWithRunnable = scheduledExecutorService.schedule((Callable<Object>) () -> {
            System.out.println("schedule with callable");
            return "callable success";
        }, 5, TimeUnit.SECONDS);
        System.out.println("result:" + scheduleWithRunnable.get());
        scheduledExecutorService.shutdown();
    }


    /**
     * 周期性的调度task执行。task第一次执行的延迟根据initialDelay参数确定，以后每一次执行都间隔period时长。
     * 在scheduleAtFixedRate中period指的两个任务开始执行的时间间隔，也就是当前任务的开始执行时间和下个任务的开始执行时间之间的间隔
     * 如果task的执行时间大于定义的period，那么下一个线程将在当前线程完成之后再执行。整个调度保证不会出现一个以上任务同时执行。
     * 因为是runnable所以没有返回结果
     */
    @Test
    public void scheudledAtFixedRate() throws ExecutionException, InterruptedException {
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() ->
                System.out.println("schedule at fixed rate"), 5, 1, TimeUnit.SECONDS);

        System.out.println("result:" + scheduledFuture.get());
        scheduledExecutorService.shutdown();
    }

    /**
     * scheduleWithFixedDelay的参数和scheduleAtFixedRate参数完全一致，它们的不同之处在于对period调度周期的解释。
     * 在scheduleAtFixedRate中period指的当前任务的结束执行时间到下个任务的开始执行时间。
     * 因为是runnable所以没有返回结果
     */
    @Test
    public void scheudledWithFixedRate() throws ExecutionException, InterruptedException {
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() ->
                System.out.println("schedule with fixed rate"), 5, 1, TimeUnit.SECONDS);

        System.out.println("result:" + scheduledFuture.get());
        scheduledExecutorService.shutdown();
    }

}
