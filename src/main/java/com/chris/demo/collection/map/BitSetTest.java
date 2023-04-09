package com.chris.demo.collection.map;

import org.junit.Test;

import java.util.BitSet;

public class BitSetTest {

    @Test
    public void test01() {
        BitSet set_1 = new BitSet();
        set_1.set(3);// 将3设置为true
        set_1.set(3, 5, true);
        set_1.set(10, 15, true);
        System.out.println("set_1:" + set_1);//{3, 4, 10, 11, 12, 13, 14}
        System.out.println("set_1 size:" + set_1.size());// 返回bit位数量，为64的倍数，因为使用long类型维护的
        System.out.println(set_1.get(4));// 判断是否存在

        System.out.println(set_1.nextSetBit(0));// 3
        System.out.println(set_1.nextClearBit(3));// 5

        BitSet set_1_tmp = (BitSet) set_1.clone();

        BitSet set_2 = new BitSet();
        set_2.set(4, 8, true);
        System.out.println("set_2:" + set_2);//{4, 5, 6, 7}
        set_1_tmp.and(set_2);// 取位图并集
        System.out.println("set_1_tmp:" + set_1_tmp);//{4}
        System.out.println("set_2:" + set_2);// set_2无变化

        System.out.println(set_1);

        System.out.println("----or----");
        set_1_tmp.clear();
        set_1_tmp = (BitSet) set_1.clone();
        set_1_tmp.or(set_2);
        System.out.println("set_1_tmp:" + set_1_tmp);


    }
}
