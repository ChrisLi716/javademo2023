package com.chris.demo.lambda;

import com.chris.demo.entities.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @Author Lilun
 * @Date 2021-03-25 16:12
 * @Description
 **/
public class OptionalTest {

    @Test
    public void testCreateOptional() {
        Employee employee = new Employee();

        //创建一个Optional实例，t为非空对象
        Optional<Employee> optional = Optional.of(employee);
        System.out.println(optional);

        employee = null;

        //创建一个Optional实例，t可以为空对象
        Optional<Employee> optional2 = Optional.ofNullable(employee);
        System.out.println(optional2);

        //创建一个空的Optional实例
        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);
    }


    @Test
    public void testGetValueOfOptional() {
        Employee employee = new Employee();
        employee = null;
        //创建一个Optional实例，t为非空对象
        Optional<Employee> optional = Optional.ofNullable(employee);
        System.out.println(optional);

        Employee employee1 = optional.orElse(new Employee(8, "Kevin"));
        System.out.println(employee1);
    }
}
