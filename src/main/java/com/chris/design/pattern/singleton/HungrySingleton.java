package com.chris.design.pattern.singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HungrySingleton {

	private static final HungrySingleton hungrySingleton = new HungrySingleton();

	public static HungrySingleton getHungrySingleton() {
		return hungrySingleton;
	}

}
