package com.chris.demo.lambda;

import com.chris.demo.entities.Employee;
import com.chris.demo.entities.TestData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Lilun
 * @Date 2021-03-24 18:56
 * @Description
 **/
public class StreamAPI_Action {

    @Test
    public void testAllMatch() {
        List<Employee> employees = TestData.getEmployees();
        //判断是否都为成年人
        boolean isAdults = employees.stream().allMatch(e -> e.getAge() >= 18);
        if (isAdults) {
            System.out.println("都是成年人");
        } else {
            System.out.println("存在未成年人");
        }
    }

    @Test
    public void testAnyMatch() {
        List<Employee> employees = TestData.getEmployees();
        boolean isHighSalary = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        if (isHighSalary) {
            System.out.println("有人拿了高工资");
        }
    }

    /**
     * noneMatch
     * 全部不匹配返回true，有一个匹配则返回false
     */
    @Test
    public void testNoneMatch() {
        List<Employee> employees = TestData.getEmployees();
        boolean hasNotEthan = employees.stream().noneMatch(e -> e.getName().equals("Ethan"));
        if (hasNotEthan) {
            System.out.println("没有Ethan");
        } else {
            System.out.println("有Ethen");
        }
    }

    /**
     * findFirst
     * 返回第一个元素
     */
    @Test
    public void testFindFirst() {
        List<Employee> employees = TestData.getEmployees();
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);
    }

    /**
     * findAny
     * 返回任意一个元素
     */
    @Test
    public void testFindAny() {
        List<Employee> employees = TestData.getEmployees();
        Optional<Employee> any = employees.stream().findAny();
        Optional<Employee> any2 = employees.parallelStream().findAny();
        System.out.println(any);
        System.out.println(any2);
    }


    @Test
    public void testStatistics() {
        List<Employee> employees = TestData.getEmployees();

        //返回流中元素的总个数
        long count = employees.stream().filter(e -> e.getSalary() > 15000).count();
        System.out.println("工资大于15000的人有" + count + "个");

        //返回最高的工资额度
        Optional<Double> max = employees.stream().map(Employee::getSalary).max(Double::compareTo);
        System.out.println("最高工资为：" + max);

        //返回最低工资的员工信息
        Optional<Employee> min = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(min);

        //内部迭代
        employees.stream().forEach(System.out::println);

        //外部迭代
        employees.forEach(System.out::println);
    }


    /**
     * 归约
     */
    @Test
    public void testReduce() {
        //计算1到10自然数之和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        //计算公司中所有员工的工资总和
        List<Employee> employees = TestData.getEmployees();
        Stream<Double> doubleStream = employees.stream().map(Employee::getSalary);
        // Optional<Double> reduce= doubleStream.reduce((a, b) -> a + b);
        Optional<Double> reduce = doubleStream.reduce(Double::sum);
        Double totalSalary = reduce.orElse((double) 0);
        System.out.println(totalSalary);
    }


    /**
     * 收集
     */
    @Test
    public void testCollector() {
        List<Employee> employees = TestData.getEmployees();

        List<Employee> list = employees.stream().filter(e -> e.getSalary() > 10000).collect(Collectors.toList());
        Set<Employee> set = employees.stream().filter(e -> e.getSalary() > 10000).collect(Collectors.toSet());
        List<Employee> list2 =
                employees.stream().filter(e -> e.getSalary() > 10000).collect(Collectors.toCollection(ArrayList::new));


        LinkedHashMap<Integer, List<Employee>> ageGrouping =
                employees.stream().collect(Collectors.groupingBy(Employee::getAge, LinkedHashMap::new, Collectors.toList()));

        Map<Integer, Employee> ageMaping = employees.stream().collect(Collectors.toMap(Employee::getAge, e -> e,
                (oldKey, newKey) -> newKey));

    }


}
