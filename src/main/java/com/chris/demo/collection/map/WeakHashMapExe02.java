package com.chris.demo.collection.map;

import java.util.*;

/**
 * @Auther Chris Lee
 * @Date 2/1/2019 12:45
 * @Description
 */
public class WeakHashMapExe02 {
	
	public static void main(String[] args) {
		Map<Integer, Byte[]> map = null;
		
		map = new WeakHashMap<>();
		for (int i = 0; i < 100000; i++) {
			Integer integer = new Integer(i);
			map.put(integer, new Byte[i]);
		}
		// -Xmx5M 这个时候发现没有OOM
		
		// -Xmx5M java.lang.OutOfMemoryError: Java heap space
		map = new HashMap<>(10);
		for (int i = 0; i < 100000; i++) {
			Integer integer = new Integer(i);
			map.put(integer, new Byte[i]);
		}
		
		// 如果存放在WeakHashMap中的key都存在强引用，那么WeakHashMap就会退化为HashMap。
		// -Xmx5M java.lang.OutOfMemoryError: Java heap space
		// at cn.intsmaze.collection.MapCase.testWeakHash(MapCase.java:119)
		map = new WeakHashMap<>();
		List list = new ArrayList();
		for (int i = 0; i < 100000; i++) {
			Integer integer = new Integer(i);
			map.put(integer, new Byte[i]);// 如果你看不起我，你可以把这行注释，你将会发现姜还是老的辣，内存溢出是WeakHashMap而不是List导致.
			list.add(integer);
		}
	}
}
