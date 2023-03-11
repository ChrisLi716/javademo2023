package com.chris.design.pattern.prototype;

public class Test {

	public static void main(String[] args) {
		AbstractFactory abstractFactory = new BeerFactory();
		Product product = abstractFactory.produce();
		product.show();
	}
}
