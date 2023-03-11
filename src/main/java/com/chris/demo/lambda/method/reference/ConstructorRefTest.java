package com.chris.demo.lambda.method.reference;

import com.chris.demo.entities.Employee;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 * 和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致，抽象方法的返回值类型即为构造器所属的类的类型
 **/
public class ConstructorRefTest {

    /**
     * Supplier的 T get();
     * Employee的 new Employee()
     */
    @Test
    public void test1() {
        Supplier<Employee> supplier = () -> new Employee();
        Employee emp = supplier.get();
        System.out.println(emp);

        Supplier<Employee> supplier1 = Employee::new;
        System.out.println(supplier1.get());
    }

    /**
     * Function的 R apply(T t);;
     * Employee的 new Employee(int id)
     */
    @Test
    public void test2() {
        Function<Integer, Employee> function = id -> new Employee(id);
        System.out.println(function.apply(121));

        Function<Integer, Employee> function1 = Employee::new;
        System.out.println(function1.apply(141));
    }

    /**
     * BiFunction 的 R apply(T t, U u);;
     * Employee的 new Employee(int id)
     */
    @Test
    public void test3() {
        BiFunction<Integer, String, Employee> function = (id, name) -> new Employee(id);
        System.out.println(function.apply(124, "Chris Li"));

        BiFunction<Integer, String, Employee> function1 = Employee::new;
        System.out.println(function1.apply(141, "James Harden"));
    }
}
