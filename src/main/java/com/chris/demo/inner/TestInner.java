package com.chris.demo.inner;

public class TestInner {

    public static void main(String[] args) {
        Outer outer = new Outer();
        // the first way to create inner instance
        // Outer.Inner inner = outer.new Inner();

        // the second way to create inner instance
        Outer.Inner inner = outer.getInnerInstance();
        System.out.println(outer.getName());
        System.out.println(inner.getName());

    }
}
