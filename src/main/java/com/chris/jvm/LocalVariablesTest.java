package com.chris.jvm;

import java.util.Date;

public class LocalVariablesTest {

    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        test.test1();
    }

    public void test1() {
        Date date = new Date();
        String name1 = "my name is chris";
        String info = test2(date, name1);
        System.out.println(date + name1);
    }


    public String test2(Date date, String name) {
        date = null;
        name = "my name is John";
        double weight = 130.5;
        char gender = 'M';
        return date + name;
    }

}