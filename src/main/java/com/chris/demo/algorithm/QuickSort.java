package com.chris.demo.algorithm;

/**
 * @Auther Chris Lee
 * @Date 12/26/2018 15:19
 * @Description
 */
public class QuickSort {
	
	public static void main(String[] args) {
		QuickSort quickSort = new QuickSort();
		int[] array = {11, 19, 16, 8, 3, 8, 18, 16, 0, 1, 18, 19, 13, 15, 6, 12, 11, 8, 7, 3};
		quickSort.quickSort(array, 0, array.length - 1);
		CommonUtils.printArray2Str(array);
	}
	
	private void quickSort(int[] array, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
		int j = higherIndex;
		
		// calculate pivot number, I am taking pivot as middle index number
		int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
		// Divide into two arrays
		while (i <= j) {
			/*
			 * In each iteration, we will identify a number from left side which is greater then the pivot value, and also we will
			 * identify a number from right side which is less then the pivot value. Once the search is done, then we exchange both
			 * numbers.
			 */
			
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			
			if (i <= j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				// move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		if (lowerIndex < j) {
			quickSort(array, lowerIndex, j);
		}
		if (i < higherIndex) {
			quickSort(array, i, higherIndex);
		}
		
	}
	
}
