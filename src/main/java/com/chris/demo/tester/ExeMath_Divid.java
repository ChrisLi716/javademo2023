package com.chris.demo.tester;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther Chris Lee
 * @Date 12/19/2018 18:18
 * @Description
 */
public class ExeMath_Divid {
	
	public static void main(String[] args) {
		System.out.println(Math.ceil(133.43434));
		
		List<String> allList = new ArrayList<>();
		int totalAmount = 34;
		int group = 3;
		
		BigDecimal v1 = new BigDecimal(totalAmount);
		BigDecimal v2 = new BigDecimal(group);
		BigDecimal v3 = (v1.divide(v2, 3, RoundingMode.HALF_DOWN));
		int amountPerGroup = (int)Math.ceil(v3.doubleValue());
		System.out.println("v3:" + v3 + ",amountPerGroup:" + amountPerGroup);
		
		for (int i = 0; i < totalAmount; i++) {
			allList.add(i + "");
		}
		
		for (int i = 0; i < group; i++) {
			int beginIndex = i * amountPerGroup;
			int endIndex = Math.min((i + 1) * amountPerGroup, totalAmount);
			if (beginIndex < endIndex) {
				System.out.println("beginIndex:" + beginIndex + ",endIndex:" + (endIndex - 1));
				System.out.println(allList.subList(beginIndex, endIndex));
				System.out.println("=========================================");
			}
			
		}
		
	}
}
