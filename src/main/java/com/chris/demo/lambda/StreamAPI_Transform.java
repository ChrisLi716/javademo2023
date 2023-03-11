package com.chris.demo.lambda;

import com.chris.demo.entities.Employee;
import com.chris.demo.entities.TestData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPI_Transform {

    /**
     * 筛选与切片
     */
    @Test
    public void filter() {
        List<Employee> employees = TestData.getEmployees();
        employees.stream().filter(e -> e.getSalary() >= 20000).forEach(System.out::println);
        System.out.println("=====================");
        //返回前n个元素
        employees.stream().limit(3).forEach(System.out::println);
        System.out.println("=====================");
        //跳过前n个元素
        employees.stream().skip(3).forEach(System.out::println);
        System.out.println("=====================");
        //通过流生成的hashCode()和equals()方法去掉重复元素
        employees.add(new Employee(4, "Allan", 34, 15800.83));
        employees.stream().distinct().forEach(System.out::println);
    }


    /**
     * map
     * 接收一个函数作为参数，将这个函数应用于每一个元素并将其映射成一个新的元素
     */
    @Test
    public void mapTest() {
        List<Employee> employeeList = TestData.getEmployees();
        employeeList.stream().map(e -> e.getName().toUpperCase()).forEach(System.out::println);
        System.out.println("=====================");
        //获取员工姓名大于5的员工的姓名
        employeeList.stream().map(Employee::getName).filter(name -> name.length() > 5).forEach(System.out::println);
    }

    /**
     * flatMap
     * 接收一个函数作为参数，将流中的每一个值都换成一个流，将后将所有的流都连成一个流
     */
    @Test
    public void flatMapTest() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPI_Transform::forStr2Stream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });

        System.out.println("=====================");
        list.stream().flatMap(StreamAPI_Transform::forStr2Stream).forEach(System.out::println);

    }

    public static Stream<Character> forStr2Stream(String str) {
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }


    /**
     * sorted
     */
    @Test
    public void sortedTest() {
        int[] intArr = new int[]{};
        List<Integer> integers = Arrays.asList(1, 22, 13, 43, 5, 6, 12);
        integers.stream().sorted().forEach(System.out::println);

        System.out.println("=====================");
        List<Employee> employeeList = TestData.getEmployees();
        employeeList.stream().sorted((e1, e2) -> {
            int ageCompared = Integer.compare(e1.getAge(), e2.getAge());
            if (0 != ageCompared) {
                return ageCompared;
            } else {
                //从大到小
                // return -Double.compare(e1.getSalary(), e2.getSalary());
                //从小到大一生气你就输了
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);

    }

}
