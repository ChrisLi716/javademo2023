package com.chris.demo.clone;

import com.chris.demo.clone.beans.City;
import com.chris.demo.clone.beans.Person;
import org.apache.commons.lang3.SerializationUtils;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 11:25
 * @Description
 */
public class DeepCloneTest {
	public static void main(String[] args)
		throws Exception {
		// test1();
		test2();
	}
	
	private static void test1()
		throws CloneNotSupportedException {
		City city = new City("Dehradun");
		Person person_1 = new Person("Chris", 1111, city);
		System.out.println(person_1.toString());
		
		Person person_2 = person_1.deepClone();
		
		CommonUtils.judgeObjects(person_1, person_2);
	}
	
	private static void test2() {
		City city = new City("Shannxi");
		Person person_3 = new Person("Chris", 1111, city);
		
		/*
		 * creates a deep clone. (i.e. the whole properties graph is cloned, not only the first level), but all classes must
		 * implement Serializable.
		 */
		Person person4 = SerializationUtils.clone(person_3);
		CommonUtils.judgeObjects(person_3, person4);
	}
}
