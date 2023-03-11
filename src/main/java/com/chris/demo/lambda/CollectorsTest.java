package com.chris.demo.lambda;

import com.chris.demo.entities.Employee;
import com.chris.demo.entities.TestData;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Lilun
 * @Date 2020-11-04 09:42
 * @Description
 **/
public class CollectorsTest {

    @Test
    public void toMap() {
        List<Employee> employeeList = TestData.getEmployees();
        Map<Integer, Employee> collect = employeeList.parallelStream().collect(Collectors.toMap(Employee::getId,
                a -> a, (oldKey, newKey) -> newKey));

        for (Map.Entry<Integer, Employee> employeeEntry : collect.entrySet()) {
            System.out.println(employeeEntry.getKey() + ", " + employeeEntry.getValue());
        }

        Map<Integer, String> collect1 = employeeList.parallelStream().collect(Collectors.toMap(Employee::getId,
                Employee::getName, (oldKey, newKey) -> newKey));
        for (Map.Entry<Integer, String> employeeEntry : collect1.entrySet()) {
            System.out.println(employeeEntry.getKey() + ", " + employeeEntry.getValue());
        }
    }


    @Test
    public void partitonBy() {
        List<Employee> employees = TestData.getEmployees();
        Map<Boolean, List<Employee>> map =
                employees.stream().collect(Collectors.partitioningBy(emp -> emp.getSalary() > 20000));
        System.out.println("True:" + map.get(Boolean.TRUE));
        System.out.println("False:" + map.get(Boolean.FALSE));

    }


    @Test
    public void groupBy() {
        List<Employee> employees = TestData.getEmployees();
        Map<String, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getName,
                LinkedHashMap::new, Collectors.toList()));
        collect.forEach((k, v) -> System.out.println(k + "," + v.toString()));

        Map<String, List<Employee>> collect1_1 = employees.stream().collect(Collectors.groupingBy(Employee::getName));
        collect1_1.forEach((k, v) -> System.out.println(k + "," + v.toString()));

        Map<String, Double> collect1_2 = employees.stream().collect(Collectors.groupingBy(Employee::getName,
                Collectors.summingDouble(Employee::getSalary)));
        collect1_2.forEach((k, v) -> System.out.println(k + "," + v.toString()));

        // group by name, and sum the salary of each name
        Map<String, Double> collect2 = employees.stream().collect(Collectors.groupingByConcurrent(Employee::getName,
                Collectors.summingDouble(Employee::getSalary)));
        collect2.forEach((k, v) -> System.out.println(k + "," + v.toString()));

        // group by name and count each name
        Map<String, Long> collect3 = employees.stream().collect(Collectors.groupingByConcurrent(Employee::getName,
                Collectors.counting()));
        collect3.forEach((k, v) -> System.out.println(k + "," + v.toString()));

    }


}
