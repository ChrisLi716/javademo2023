package com.chris.demo.immutable;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Auther Chris Lee
 * @Date 12/24/2018 12:07
 * @Description
 */
class FinalReferenceBean {
	
	/*
	 * A final field containing a reference to a mutable object has all the disadvantages of a nonfinal field. While the
	 * reference cannot be modified, the referenced object can be modified with disastrous results
	 */
	
	static final String[] PRIVATE_VALUES = {"1", "2", "3"};
	
	private static final List<String> UNMODIFIED_PRIVATE_VALUES =
		Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
	
	private static final List<String> CLONE_PRIVATE_VALUES = Arrays.asList(PRIVATE_VALUES.clone());
	
	static final String FIRST_NAME = "Chris";
	
	static final String LAST_NAME = "Li";
	
	public static void main(String[] args) {
		 // modifiedContentOfFinalObject();
		 // unmodifiedPrivateValuesTest(CLONE_PRIVATE_VALUES, "newvalue", 2);
		 unmodifiedPrivateValuesTest(UNMODIFIED_PRIVATE_VALUES, "newvalue", 2);
	}
	
	private static void unmodifiedPrivateValuesTest(List<String> array, String newValue, int index) {
		for (String one : array) {
			System.out.println(one);
		}
		if (index >= 0 && StringUtils.isNotEmpty(newValue)) {
			array.set(index, newValue);
		}
		System.out.println("after modifing.");
		for (String one : array) {
			System.out.println(one);
		}
		
	}
	
	@SuppressWarnings("unused")
	private static void modifiedContentOfFinalObject() {
		String firstName = FinalReferenceBean.FIRST_NAME;
		String lastName = FinalReferenceBean.LAST_NAME;
		System.out.println(firstName + " " + lastName);
		System.out.println("====================");
		firstName = "John";
		lastName = " P";
		System.out.println(firstName + " " + lastName);
		PRIVATE_VALUES[2] = "modified 2";
		for (String privateValue : PRIVATE_VALUES) {
			System.out.println(privateValue);
		}
	}
	
}
