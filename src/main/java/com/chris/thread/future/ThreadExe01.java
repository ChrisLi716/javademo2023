package com.chris.thread.future;

/**
 * @Auther Chris Lee
 * @Date 12/10/2018 11:36
 * @Description
 */
public class ThreadExe01 {
	
	public static void main(String[] args) {
		final ThreadExe01 main = new ThreadExe01();
		final String name = "Chris";
		
		new Thread(() -> {
			try {
				main.writeName(name);
				System.out.println("My name :");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}).start();
		
	}
	
	private void writeName(String name) {
		final String userName = name;
		new Thread(() -> {
			try {
				Thread.sleep(1000);
				System.out.println(userName);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}).start();
	}
}
