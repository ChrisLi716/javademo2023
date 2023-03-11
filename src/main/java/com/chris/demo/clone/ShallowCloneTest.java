package com.chris.demo.clone;


import com.chris.demo.clone.beans.City;
import com.chris.demo.clone.beans.Person;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 11:00
 * @Description
 */
public class ShallowCloneTest {
	
	public static void main(String[] args)
		throws Exception {
		test1();
	}
	
	private static void test1()
		throws CloneNotSupportedException {
		City city = new City("Dehradun");
		Person person_1 = new Person("Chris", 1111, city);
		System.out.println(person_1.toString());
		
		Person person_2 = person_1.shallowClone();
		
		CommonUtils.judgeObjects(person_1, person_2);
	}
	
}
