package com.chris.demo.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Java内置四大核心函数式接口
 * 消费型 Consumer<T>      void accept(T t)
 * 供给型 Supplier<T>      T get()
 * 函数型 Function<T, R>   R apply(T t)
 * 断定型 Predicate<T>     boolean test(T t)
 **/
public class LambdaTest2 {

    public void happTime(int money, Consumer<Integer> cost) {
        cost.accept(money);
    }

    @Test
    public void test1() {
        happTime(500, new Consumer<Integer>() {
            @Override
            public void accept(Integer cost) {
                System.out.println("the cost is :" + cost);
            }
        });

        //Lambda表达式
        happTime(400, money -> System.out.println("the cost is :" + money));
    }


    /**
     * 根据给定的规则过滤集合中的字符串，此规则由predicate的方法决定
     */
    public List<String> fileterStr(List<String> list, Predicate<String> pre) {
        List<String> afterFiltering = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                afterFiltering.add(s);
            }
        }
        return afterFiltering;
    }


    @Test
    public void test2() {
        List<String> list = Arrays.asList("Chris", "John", "Stephone", "Hedy");
        List<String> fileteredStr = fileterStr(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("s");
            }
        });
        System.out.println(fileteredStr);

        //Lambda表达式
        List<String> fileteredStr2 = fileterStr(list, s -> s.contains("s"));
        System.out.println(fileteredStr2);

    }
}
