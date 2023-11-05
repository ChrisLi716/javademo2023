package com.chris.jvm;

public class ClInitTest {

    private static int num = 1;

    static {
        num = 2;
        number = 20;
    }

    private static int number = 10;

    public static void main(String[] args) {
        System.out.println(ClInitTest.num);
        System.out.println(ClInitTest.number);
    }
}
