package com.chris.thread.future.async;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class CompletableFutrueTest {
    @Test
    public void allOfTest() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        List<CompletableFuture<String>> cfList = CollUtil.newArrayList();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
                String result = null;
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " begin sleep");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(threadName + " end sleep");
                    result = doSomething(threadName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }, forkJoinPool);
            cfList.add(cf);
        }
        CompletableFuture<Void> allCF = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]));
        try {
            allCF.get();
            CompletableFuture<List<String>> resultCF = allCF.thenApply(v -> cfList.stream().map(cf -> {
                try {
                    return cf.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList()));
            List<String> result = resultCF.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String doSomething(String jobNumber) {
        if (StrUtil.equals(jobNumber, "ForkJoinPool.commonPool-worker-2")) {
            throw new RuntimeException("ForkJoinPool.commonPool-worker-2 is exception");
        } else {
            for (int i = 0; i < 10; i++) {
                System.out.println(jobNumber + " is outputing:" + i);
            }
        }
        return jobNumber + " is done!";
    }
}