package com.chris.demo.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;


/**
 * Lambda表达式的本质就是作为函数式接口的实例
 * -> Lambda 操作符，
 * 左边为形参列表，其实就是是原来方法中的形参列表;
 * 1. 类型推断，参数的类型可以省略
 * 2. 无参无返回值,小括号不可以省略
 * 3. 如果只有一个参数，小括号可以省略
 * 4. 有两个及以上参数, 小括号不可以省略
 * <p>
 * 右边为Labmda体，其实就是重写的方法的方法体
 * 1. 多条执行语句，并且有返回值要有return和{}
 * 2. 只有一条语句, return与{}若有都可以省略
 */
public class LambdaTest {

    /**
     * 无参无返回值
     */
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hellow runnable r1.");
            }
        };
        r1.run();

        //Lambda表达式做为左边接口的一个实例
        Runnable r2 = () -> System.out.println("hellow runnable r2.");
        r2.run();
    }

    /**
     * 有一个参数无返回值
     */
    @Test
    public void test2() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("谎言和誓言的区别是什么?");

        Consumer<String> consumer1 = (String s) -> System.out.println(s);
        consumer1.accept("一个是听的人当真了，一个是说的人当真了");

        //类型推断，参数的类型可以省略
        Consumer<String> consumer2 = (s) -> System.out.println(s);
        consumer2.accept("一个是听的人当真了，一个是说的人当真了");

        //如果只有一个参数，小括号可以省略
        Consumer<String> consumer3 = s -> System.out.println(s);
        consumer3.accept("一个是听的人当真了，一个是说的人当真了");

    }


    @Test
    public void test3() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        int result1 = comparator.compare(12, 34);
        System.out.println(result1);


        //Lambda表达式
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
        int result2 = comparator2.compare(12, 2);
        System.out.println(result2);

        //方法引用
        Comparator<Integer> comparator3 = Integer::compare;
        int result3 = comparator3.compare(12, 12);
        System.out.println(result3);

    }

    @Test
    public void test4() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        int result1 = comparator.compare(12, 34);
        System.out.println(result1);


        //当有两个及以上参数，多条执行语句，并且有返回值
        Comparator<Integer> comparator2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1, o2);
        };
        int result2 = comparator2.compare(12, 2);
        System.out.println(result2);

        //Lambda体只有一条语句，return与{}若有都可以省略
        Comparator<Integer> comparator3 = (o1, o2) -> o1.compareTo(o2);
        int result3 = comparator3.compare(12, 12);
        System.out.println(result3);

    }


}
