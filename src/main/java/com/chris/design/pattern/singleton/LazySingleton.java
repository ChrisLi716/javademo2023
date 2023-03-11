package com.chris.design.pattern.singleton;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class LazySingleton {

	private static volatile LazySingleton lazyInstance = null;

	public static synchronized LazySingleton getInstance() {
		if (null == lazyInstance) {
			lazyInstance = new LazySingleton();
		}
		return lazyInstance;
	}
}
