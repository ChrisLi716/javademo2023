package com.chris.demo.io.Closeable;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author Lilun
 * @Date 2021-03-24 10:36
 * @Description
 **/
public class MyResource1 implements Closeable {
    @Override
    public void close() throws IOException {
        System.out.println("close resource1");
    }

    public void readResource() {
        System.out.println("read resource1");
    }
}
