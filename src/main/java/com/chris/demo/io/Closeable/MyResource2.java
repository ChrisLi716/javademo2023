package com.chris.demo.io.Closeable;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author Lilun
 * @Date 2021-03-24 10:36
 * @Description
 **/
public class MyResource2 implements Closeable {
    @Override
    public void close() throws IOException {
        System.out.println("close resource2");
    }

    public void readResource() {
        System.out.println("read resource2");
    }

}
