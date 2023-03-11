package com.chris.demo.inner;

import lombok.Getter;

@Getter
public class Outer2 {

    private String name = "outer";
    private static String outer="I'm outer";

    @Getter
    static class Inner {

        private String name = "inner";

        public static void descInner() {
            System.out.println("outer:" + outer);
        }

    }

}
