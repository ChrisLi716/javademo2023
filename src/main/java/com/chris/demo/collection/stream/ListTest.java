package com.chris.demo.collection.stream;

import cn.hutool.core.collection.CollectionUtil;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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


}
