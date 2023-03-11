package com.chris.design.pattern.decorator;

public class Phone implements Component {

	@Override
	public int cost() {
		return 2000;
	}

	@Override
	public void desc() {
		System.out.println("bare phone");
	}
}
