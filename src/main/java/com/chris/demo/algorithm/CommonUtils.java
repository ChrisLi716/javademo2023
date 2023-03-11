package com.chris.demo.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 15:25
 * @Description
 */
public class CommonUtils {
	
	static int[] generateRandomIntArrays(int amount) {
		int[] intArray = new int[amount];
		for (int i = 0; i < amount; i++) {
			Random random = new Random();
			int temp = random.nextInt(amount);
			intArray[i] = temp;
		}
		return intArray;
	}
	
	public static void main(String[] args) {
		int[] intArray = generateRandomIntArrays(10);
		System.out.println(Arrays.toString(intArray));
	}
	
	static void printArray2Str(int[] array) {
		System.out.println(Arrays.toString(array));
	}
	
}
