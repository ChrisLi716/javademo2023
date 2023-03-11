package com.chris.demo.collection.stream;

@FunctionalInterface
public interface IConvert<F, T> {
	T convert(F from);
}
