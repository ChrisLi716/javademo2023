package com.chris.demo.collection.stream;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {

	public static void main(String[] args) {

		System.out.println(ThreadLocalRandom.current().nextInt(1000000));
	}
}
