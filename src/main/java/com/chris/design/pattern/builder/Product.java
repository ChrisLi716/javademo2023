package com.chris.design.pattern.builder;

import lombok.Data;

@Data
public class Product {

	private String name;

	private int id;

	private String type;

	private int price;

	private Product(Builder builder) {
		this.id = builder.id;
		this.type = builder.type;
		this.name = builder.name;
		this.price = builder.price;
	}

	static class Builder {
		private String name;

		private int id;

		private String type;

		private int price;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder price(int price) {
			this.price = price;
			return this;
		}

		public Product build() {
			return new Product(this);
		}

	}

}
