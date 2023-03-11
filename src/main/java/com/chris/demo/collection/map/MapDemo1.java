package com.chris.demo.collection.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther Chris Lee
 * @Date 2/1/2019 14:31
 * @Description
 */
public class MapDemo1 {
	
	public static void main(String[] args) {
		MapDemo1 mapDemo1 = new MapDemo1();
		mapDemo1.tryHashTable();
	}
	
	private void tryHashMap() {
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("", "");
		hashMap.put(null, null);
		hashMap.put(null, null);
		hashMap.put("1", null);
		System.out.println(hashMap.size());
		for (Map.Entry<String, String> entry : hashMap.entrySet()) {
			System.out.println("key:" + entry.getKey() + ", value:" + entry.getValue());
		}
	}
	
	private void tryTreeMap() {
		Map<String, String> treeMap = new TreeMap<>();
		treeMap.put("", "");
		// the key should be null ->NullPointerException
		/*
		 * treeMap.put(null, null); treeMap.put(null, null);
		 */
		treeMap.put("2", "2");
		treeMap.put("1", null);
		
		System.out.println(treeMap.size());
		for (Map.Entry<String, String> entry : treeMap.entrySet()) {
			System.out.println("key:" + entry.getKey() + ", value:" + entry.getValue());
		}
	}
	
	private void tryHashTable() {
		Map<String, String> hashTable = new Hashtable<>();
		hashTable.put("", "");
		// the key should be null ->NullPointerException
		/*
		 * hashTable.put(null, null); hashTable.put(null, null);
		 */
		hashTable.put("2", "2");
		
		// the value should be null ->NullPointerException
		/* hashTable.put("1", null); */
		
		System.out.println(hashTable.size());
		for (Map.Entry<String, String> entry : hashTable.entrySet()) {
			System.out.println("key:" + entry.getKey() + ", value:" + entry.getValue());
		}
	}
}
