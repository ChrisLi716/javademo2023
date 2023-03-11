package com.chris.demo.lambda.method.reference;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

/**
 * 数组引用
 * 和方法引用类似，可以把数组看成一个特殊的类，则写法与构造器引用类似。
 **/
public class ArrayRefTest {

    /**
     * Function的 R apply(T t);
     * Employee的 new Employee(int id)
     */
    @Test
    public void test2() {
        Function<Integer, String[]> function = length -> new String[length];
        String[] arr = function.apply(10);
        System.out.println(Arrays.toString(arr));

        Function<Integer, String[]> function1 = String[]::new;
        String[] arr1 = function1.apply(10);
        System.out.println(Arrays.toString(arr1));
    }
}
