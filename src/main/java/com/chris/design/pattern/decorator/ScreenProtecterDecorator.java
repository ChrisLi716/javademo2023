package com.chris.design.pattern.decorator;

public class ScreenProtecterDecorator extends Decorator {

	public ScreenProtecterDecorator(Component component) {
		this.component = component;
	}

	@Override
	public int cost() {
		return this.component.cost() + 10;
	}

	@Override
	public void desc() {
		component.desc();
		System.out.println("add screen protecter!");
	}
}
