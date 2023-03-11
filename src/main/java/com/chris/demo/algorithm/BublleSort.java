package com.chris.demo.algorithm;

import java.util.Arrays;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 15:24
 * @Description
 */
public class BublleSort {
	
	private static void bublleSort(int amount) {
		int[] intArray = CommonUtils.generateRandomIntArrays(amount);
		System.out.println(Arrays.toString(intArray));
		int temp;
		int length = intArray.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = 0; j < length - 1 - i; j++) {
				if (intArray[j] > intArray[j + 1]) {
					temp = intArray[j];
					intArray[j] = intArray[j + 1];
					intArray[j + 1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(intArray));
	}
	
	public static void main(String[] args) {
		bublleSort(20);
	}
	
}
