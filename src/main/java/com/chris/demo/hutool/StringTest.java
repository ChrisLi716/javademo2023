package com.chris.demo.hutool;

import org.junit.Test;

import java.util.StringJoiner;

/**
 * @Author Lilun
 * @Date 2020-12-14 17:03
 * @Description
 **/
public class StringTest {


    @Test
    public void testStringJoin() {
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add("chris");
        stringJoiner.add("heddy");
        stringJoiner.add("Ethan");
        stringJoiner.add("John");
        System.out.println(stringJoiner.toString());

    }

    @Test
    public void testStringJoin2() {
        StringJoiner stringJoiner = new StringJoiner("|", "prefix", "suffix");
        stringJoiner.add("chris");
        stringJoiner.add("heddy");
        stringJoiner.add("Ethan");
        stringJoiner.add("John");

        String s = stringJoiner.toString();
        System.out.println(s);
    }


    @Test
    public void testStringJoin3() {

        StringJoiner stringJoiner1 = new StringJoiner(",");
        stringJoiner1.add("chris");
        stringJoiner1.add("heddy");
        stringJoiner1.add("Ethan");
        stringJoiner1.add("John");

        StringJoiner stringJoiner2 = new StringJoiner("|");
        stringJoiner2.add("1");
        stringJoiner2.add("2");
        stringJoiner2.add("3");
        stringJoiner2.add("4");

        StringJoiner merge = stringJoiner1.merge(stringJoiner2);

        String s = merge.toString();
        System.out.println(s);
    }

    @Test
    public void testStringJoin4() {
        StringJoiner stringJoiner = new StringJoiner("|");
        stringJoiner.setEmptyValue("void");

        String s = stringJoiner.toString();
        System.out.println(s);
    }

}
