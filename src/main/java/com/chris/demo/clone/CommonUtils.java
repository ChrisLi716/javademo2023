package com.chris.demo.clone;

import com.chris.demo.clone.beans.Person;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 11:31
 * @Description
 */
public class CommonUtils {
	
	public static void judgeObjects(Person person_1, Person person_2) {
		if (person_1 == person_2) {
			
			System.out.println("Both person1 and person2 holds same object");
		}
		else {
			/*
			 * Evaluate false, because person1 and person2 holds different objects, person1 and person2 are the copy of each other
			 * but both are different objects and holds different heap memory
			 */
			System.out.println("Both person1 and person2 holds different objects");
		}
		
		if (person_1.equals(person_2)) {
			// Evaluate true, person1 and person2 are equal and have same content
			System.out.println("But both person1 and person2 are equal and have same content");
		}
		else {
			System.out.println("Both person1 and person2 aren't equal to each other");
		}
		
		if (person_1.getCity() == person_2.getCity()) {
			System.out.println("Both person1 and person2 have same city object");
		}
		else {
			System.out.println("Both person1 and person2 have different city objects");
		}
		
		if (person_1.getCity().equals(person_2.getCity())) {
			System.out.println("Both person1 and person2 have citys that are equal and have same content");
		}
		else {
			System.out.println("Both person1 and person2 have citys that aren't equal to each other");
		}
	}
}
