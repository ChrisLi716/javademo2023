package com.chris.design.pattern.builder;

public class Test {
	public static void main(String[] args) {
		Product product = new Product.Builder().id(111).name("bottle").type("middle").price(123).build();
		System.out.println(product.toString());

		Integer i = 10;
		System.out.println(String.valueOf(i));

	}
}
