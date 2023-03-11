package com.chris.demo.collection.map;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Auther Chris Lee
 * @Date 2/1/2019 12:24
 * @Description
 */
public class WeakHashMapExe {

    public static void main(String[] args)
            throws Exception {
        Map<String, Person> map = new WeakHashMap<>();
        String name1 = "Chris"; // 强引用
        String name2 = "John";
        Person person1 = new Person(name1, "CN", 23);
        Person person2 = new Person(name2, "US", 23);
        map.put(name1, person1);
        map.put(name2, person2);
        System.out.println(map.containsKey(name1));
        System.out.println(map.size());
        name1 = null; // map中的values对象成为弱引用对象
        map.get(name2);
        System.gc(); // 主动触发一次GC
        Thread.sleep(10000);
        System.out.println(map.size());
    }
}
