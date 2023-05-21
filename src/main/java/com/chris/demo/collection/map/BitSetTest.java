package com.chris.demo.collection.map;

import cn.hutool.core.lang.hash.Hash;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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


    @Test
    public void test02() {
        BitSet bitSet = new BitSet(2);
        int size = bitSet.size();
        int length = bitSet.length();
        // size:64, length:0
        log.info("size:{}, length:{}", size, length);

    }

    /**
     * 将所有手机号存存来，方便
     */
    @Test
    public void test03() {
        // 136 22314539
        Map<Integer, BitSet> map = new HashMap<>();
        // 因为BitSet只能存储int值最大为2,147,483,648，所以要根据号段分桶
        map.computeIfAbsent(136, k -> new BitSet()).set(22314539);

        BitSet bitSet = map.get(136);
        int length = bitSet.length();
        int size = bitSet.size();
        // length:22314540, size:22314560
        log.info("length:{}, size:{}", length, size);

    }
}
