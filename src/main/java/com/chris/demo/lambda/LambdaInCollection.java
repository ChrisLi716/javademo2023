package com.chris.demo.lambda;

import java.util.Arrays;

public class LambdaInCollection {

    public static void main(String[] args) {
        lambdaForList();
    }

    private static void lambdaForList() {
        Arrays.asList("1", "2", "3").forEach(System.out::println);
        Arrays.asList("a", "b", "d").forEach(e -> {
            String newstr = e + Math.random();
            System.out.println(newstr);
        });

        String[] datas = new String[]{"pen", "zhao", "li"};
        Arrays.sort(datas, (v1, v2) -> {
            return Integer.compare(v1.length(), v2.length());
        });

        Arrays.asList(datas).forEach(e -> System.out.println(e));
    }
}
