package com.chris.demo.tester;

public class Test {


    @org.junit.Test
    public void test() {
        int layer = 5;
        int stars = 1;
        int whitespace = 0;

        for (int i = 0; i < layer; i++) {
            whitespace = layer - (i + 1);
            stars = i + 1;
            print(stars, whitespace);
        }
    }


    public void print(int startnum, int whitespace) {
        StringBuilder starts = new StringBuilder();
        for (int i = 0; i < startnum; i++) {
            starts.append("* ");
        }
        StringBuilder whtespaces = new StringBuilder();
        for (int i = 0; i < whitespace; i++) {
            whtespaces.append(" ");
        }

        System.out.println(whtespaces.toString() + starts.toString());
    }

}
