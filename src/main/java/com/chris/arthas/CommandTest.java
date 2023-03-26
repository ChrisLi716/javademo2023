package com.chris.arthas;

import cn.hutool.json.JSONUtil;
import com.chris.demo.entities.Student;
import com.chris.demo.entities.TestData;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CommandTest {


    @Test
    public void test01() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                TestData.getStudents().forEach(s -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    String print = print(s);
                    System.out.println(print);
                });
            }

        });
        thread.setName("chris-001");
        thread.start();
        thread.join();
    }


    public String print(Student s) {
        return "result:" + JSONUtil.toJsonStr(s);

    }
}
