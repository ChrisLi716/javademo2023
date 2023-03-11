package com.chris.demo.tester;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lilun
 * @Date 2021-01-06 10:13
 * @Description
 **/
public class ListTest {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list.toString());
        list.add(1, 2);

        System.out.println(list.toString());

        list.remove(0);
        System.out.println(list.toString());
    }
}
