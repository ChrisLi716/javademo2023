package com.chris.demo.inner;

public class TestStaticInner {

    public static void main(String[] args) {
        Outer2.Inner inner  = new Outer2.Inner();
        System.out.println(inner.getName());
        Outer2.Inner.descInner();
    }
}
