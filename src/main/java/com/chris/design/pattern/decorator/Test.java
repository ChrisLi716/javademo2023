package com.chris.design.pattern.decorator;

public class Test {

	public static void main(String[] args) {
		Component phone = new ScreenProtecterDecorator(new ShellDecorator(new Phone()));
		phone.desc();
		System.out.println(phone.cost());
	}
}
