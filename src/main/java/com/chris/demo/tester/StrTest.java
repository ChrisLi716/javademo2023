package com.chris.demo.tester;


import cn.hutool.core.util.StrUtil;
import org.junit.Test;

public class StrTest {


    @Test
    public void test01() {
        String str = "1-2-3-4-5";
        String[] splits = StrUtil.split(str, 5);
        for (String split : splits) {
            System.out.println(split);
        }
    }
}
