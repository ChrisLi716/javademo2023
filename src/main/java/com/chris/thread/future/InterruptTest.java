package com.chris.thread.future;

import java.io.IOException;

public class InterruptTest {
	
	public static void main(String[] args)
		throws IOException {
		InterruptTest test = new InterruptTest();
		test.interruptBlockedThread();
		
	}
	
	private void interruptBlockedThread() {
		MyThread thread = new MyThread();
		thread.start();
		doInterruptThread(thread);
	}
	
	class MyThread extends Thread {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 10000; i++) {
					System.out.println(getName() + "进入睡眠状态");
					sleep(1000);
					System.out.println("睡眠完毕");
				}
			}
			catch (InterruptedException e) {
				System.out.println("得到中断异常");
			}
			System.out.println("run方法执行完毕");
		}
	}
	
	private void interruptNonBlockedThread() {
		MyThread2 thread2 = new MyThread2();
		
		thread2.start();
		doInterruptThread(thread2);
	}
	
	private void doInterruptThread(Thread thread) {
		try {
			System.out.println(Thread.currentThread().getName() + "进入睡眠状态");
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + "睡眠完毕");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
	
	class MyThread2 extends Thread {
		@Override
		public void run() {
			int i = 0;
			while (!isInterrupted()) {
				System.out.println(i++);
			}
			System.out.println("run方法执行完毕");
		}
	}
}
