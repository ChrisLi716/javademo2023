package com.chris.demo.collection.stream;

import cn.hutool.core.collection.CollUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {


    /**
     * 坑一：使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常。
     * Arrays.asList返回的ArrayList却是Arrays类的内部类，并没有重写add方法
     * 坑二：数组中元素的更改会影响到statusList中的元素
     */
    @Test
    public void testArrays_asList() {
        Integer[] intArray = {1, 2};
        List<Integer> statusList = Arrays.asList(intArray);
        System.out.println(statusList);
        System.out.println(statusList.contains(1));
        System.out.println(statusList.contains(3));

        /*Collection<Integer> unmodifiable = Collections.unmodifiableCollection(statusList);
        unmodifiable.add(3);  //抛出java.lang.UnsupportedOperationException异常*/

        intArray[1] = 3;
        System.out.println(statusList);
    }


    private List<Integer> userList = CollUtil.newArrayList();

    @Before
    public void buildList() {
        System.out.println("begin build userList");
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            userList.add(rand.nextInt(1000));
        }
    }

    /**
     * stream.sort耗时：34ms
     * List.sort耗时：5ms
     */
    @Test
    public void testPerformance() {
        List<Integer> userList2 = new ArrayList<>(userList);

        long startTime1 = System.currentTimeMillis();
        userList2.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        System.out.println("stream.sort耗时：" + (System.currentTimeMillis() - startTime1) + "ms");

        long startTime = System.currentTimeMillis();
        userList.sort(Comparator.comparing(Integer::intValue));
        System.out.println("List.sort耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * List.sort()耗时：31ms
     * stream.sort耗时：8ms
     */
    @Test
    public void testPerformance2() {
        List<Integer> userList2 = new ArrayList<>(userList);

        long startTime = System.currentTimeMillis();
        userList.sort(Comparator.comparing(Integer::intValue));
        System.out.println("List.sort()耗时：" + (System.currentTimeMillis() - startTime) + "ms");

        long startTime1 = System.currentTimeMillis();
        userList2.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        System.out.println("stream.sort耗时：" + (System.currentTimeMillis() - startTime1) + "ms");
    }


}
