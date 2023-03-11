package com.chris.design.pattern.decorator;

public class ShellDecorator extends Decorator {

	public ShellDecorator(Component component) {
		this.component = component;
	}

	@Override
	public int cost() {
		return this.component.cost()+20;
	}

	@Override
	public void desc() {
		component.desc();
		System.out.println("add shell");
	}
}
