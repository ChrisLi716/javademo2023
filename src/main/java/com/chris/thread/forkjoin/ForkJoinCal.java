package com.chris.thread.forkjoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author Lilun
 * @Date 2021-06-07 17:52
 * @Description
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class ForkJoinCal extends RecursiveTask<Long> {

    private static final long THRESHOLD = 10000L;//临界值
    private long start;
    private long end;


    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinCal left = new ForkJoinCal(start, middle);
            left.fork();
            ForkJoinCal right = new ForkJoinCal(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(2);
        ForkJoinCal task = new ForkJoinCal(0, 1000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
    }
}
