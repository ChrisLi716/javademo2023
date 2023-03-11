package com.chris.demo.lambda.method.reference;

import com.chris.demo.entities.Employee;
import com.chris.demo.entities.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 什么时候使用方法引用
 * 1. 当传递给lambda体的操作已经有实现的方法了，就可以使用方法引用
 * 2. 方法引用本质上就是lambda表达式，lambda表达示作为函数式接口的实例，所以方法引用也是函数式接口的实例
 * 格式：
 * 类或对象::方法名称
 * <p>
 * 具体分为如下三种情况
 * 情况1. 对象::实例方法
 * 情况2. 类::类方法
 * 情况3. 类::实例方法
 * <p>
 * 方法引用使用的要求
 * 要求接口中的抽象方法，它的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型一致,但是只针对情况1和情况2
 **/
public class MethodRefTest {

    /**
     * 类实例::实例方法
     */
    @Test
    public void test1() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("beijin");

        //method reference
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("xian'an");
    }

    /**
     * 类实例::实例方法
     */
    @Test
    public void test2() {
        Employee emp = new Employee(1001, "Tom", 54, 4525.15);
        Supplier<String> supplier = () -> emp.getName();
        System.out.println(supplier.get());

        //method reference
        Supplier<String> supplier1 = emp::getName;
        System.out.println(supplier1.get());

    }

    /**
     * 类::静态方法
     */
    @Test
    public void test3() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        int result = comparator.compare(12, 11);
        System.out.println(result);


        Comparator<Integer> comparator1 = Integer::compare;
        int result1 = comparator1.compare(12, 11);
        System.out.println(result1);

    }


    /**
     * 类::静态方法
     * Function中的 R apply(T t);
     * Long Math.round(Double);
     */
    @Test
    public void test4() {
        Function<Double, Long> func = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };

        long result = func.apply(12.6);
        System.out.println(result);

        Function<Double, Long> func1 = d -> Math.round(d);
        long result1 = func1.apply(12.3);
        System.out.println(result1);


        Function<Double, Long> func2 = Math::round;
        long result2 = func2.apply(12.5);
        System.out.println(result2);

    }

    /**
     * 类::实例方法
     * Comparator中的 int compare(T t1, T t2)
     * t1奕成了下面方法中的调用者
     * String中的 int t1.compareTo(t2)
     */
    @Test
    public void test5() {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(comparator.compare("abc", "acb"));

        Comparator<String> comparator1 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator.compare("abc", "acb"));

        Comparator<String> comparator2 = String::compareTo;
        System.out.println(comparator2.compare("abc", "acb"));

    }

    /**
     * BiPredicate中的 boolean test(T t, U u);
     * String中的boolean t1.equals(t2)
     */
    @Test
    public void test6() {
        BiPredicate<String, String> biPredicate = new BiPredicate<String, String>() {
            @Override
            public boolean test(String s, String s2) {
                return s.equals(s2);
            }
        };
        System.out.println(biPredicate.test("abc", "abc"));

        BiPredicate<String, String> biPredicate1 = (s1, s2) -> s1.equals(s2);
        System.out.println(biPredicate1.test("abc", "acd"));

        BiPredicate<String, String> biPredicate2 = String::equals;
        System.out.println(biPredicate2.test("add", "add"));
    }

    /**
     * Function中的 R apply(T t);
     * t作为下面方法中的调用者
     * Empoloyee中的String getName()
     */
    @Test
    public void test7() {
        Employee emp = new Employee(2, "John", 48, 13000.85);

        Function<Employee, String> function = e -> e.getName();
        String name = function.apply(emp);
        System.out.println(name);

        Function<Employee, String> function1 = Employee::getName;
        System.out.println(function1.apply(emp));


    }

}
