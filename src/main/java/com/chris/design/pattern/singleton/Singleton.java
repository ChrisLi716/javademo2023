package com.chris.design.pattern.singleton;

public class Singleton {
	public static void main(String[] args) {
		testSingleTone();
		testHungrySingleton();

	}

	private static LazySingleton testSingleTone() {
		LazySingleton lazySingleton = LazySingleton.getInstance();
		return lazySingleton;
	}

	private static HungrySingleton testHungrySingleton() {
		HungrySingleton hungrySingleton = HungrySingleton.getHungrySingleton();
		return hungrySingleton;
	}

}


