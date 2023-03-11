package com.chris.thread.future.sync;

public class VolatileTest {
    boolean status = false;

    /**
     * 状态切换为true
     */
    public void changeStatus() {
        status = true;
    }

    /**
     * 若状态为true，则running。
     */
    public void run() {
        if (status) {
            System.out.println("running....");
        } else {
            System.out.println("die....");
        }
    }


    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();

        Thread t1 = new Thread(volatileTest::changeStatus);
        t1.setName("T1");
        Thread t2 = new Thread(volatileTest::run);
        t2.setName("T2");

    }
}
