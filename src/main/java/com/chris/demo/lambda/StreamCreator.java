package com.chris.demo.lambda;

import com.chris.demo.entities.Employee;
import com.chris.demo.entities.TestData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreator {

    /**
     * 通过集合创建Stream
     */
    public void createStreamByCollection() {
        List<Employee> employees = TestData.getEmployees();
        // 返回一个顺序流
        Stream<Employee> stream = employees.stream();

        // 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }


    /**
     * 通过数组创建Stream
     */
    public void createStreamByArrays() {
        int[] intArr = new int[]{1, 2, 3, 4, 5, 6};
        IntStream stream = Arrays.stream(intArr);

        Employee chris = new Employee(1, "Chris", 23, 23000.43);
        Employee john = new Employee(2, "John", 14, 3000.43);
        Employee[] empArr = new Employee[]{chris, john};

        Stream<Employee> empStream = Arrays.stream(empArr);

    }

    /**
     * 通过Stream的of()方法创建Steam
     */
    public void createStreamByStream() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    /**
     * 创建无限流
     */
    @Test
    public void createStreamByStream2() {
        /*
         迭代
         创建并遍历前10个偶数
         */
        Stream.iterate(0, seed -> seed + 2).limit(10).forEach(System.out::println);

        /*
         生成
         创建并遍历前10个数
         */
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
