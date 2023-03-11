package com.chris.thread.future.sync;

import java.util.concurrent.TimeUnit;

/**
 * @Auther Chris Lee
 * @Date 4/2/2019 14:45
 * @Description
 */
public class Resource1 {
	
	private static int TIMES = 6;
	
	public static void b() {
		// other operations should not be locked...
		System.out.println(Thread.currentThread().getName() + ":not synchronized in b()");
		synchronized (Resource1.class) {
			for (int i = 0; i < TIMES; i++) {
				System.out.println(Thread.currentThread().getName() + ":synchronized in b()");
				try {
					TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void f() {
		// other operations should not be locked...
		System.out.println(Thread.currentThread().getName() + ":not synchronized in f()");
		synchronized (this) {
			for (int i = 0; i < TIMES; i++) {
				System.out.println(Thread.currentThread().getName() + ":synchronized in f()");
				try {
					TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void g() {
		// other operations should not be locked...
		System.out.println(Thread.currentThread().getName() + ":not synchronized in g()");
		synchronized (this) {
			for (int i = 0; i < TIMES; i++) {
				System.out.println(Thread.currentThread().getName() + ":synchronized in g()");
				try {
					TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void h() {
		// other operations should not be locked...
		System.out.println(Thread.currentThread().getName() + ":not synchronized in h()");
		synchronized (this) {
			for (int i = 0; i < TIMES; i++) {
				System.out.println(Thread.currentThread().getName() + ":synchronized in h()");
				try {
					TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		final Resource1 rs = new Resource1();
		final Resource1 rs2 = new Resource1();
		new Thread(() -> {
			//Resource1.b();
			rs.f();
		}).start();
		//new Thread(Resource1::b).start();
		new Thread(rs2::g).start();

		
	}
}
