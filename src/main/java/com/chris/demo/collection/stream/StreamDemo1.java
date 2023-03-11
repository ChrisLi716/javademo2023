package com.chris.demo.collection.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo1 {

	public static void main(String[] args) {
		StreamDemo1 instance = new StreamDemo1();
		instance.stream2();
	}

	private void generateStream() {
		//第一种方式，通过of方法
		Stream stream1 = Stream.of("hello", "world");

		//第二种方式，通过数组方式
		String[] strings = new String[] {"hello", "world"};
		Stream stream2 = Arrays.stream(strings);
		//of的底层就是 通过Arrays.stream()来实现的.
		Stream stream3 = Stream.of(strings);

		//第三种方式,通过集合.stream
		List<String> list = Arrays.asList("hello", "world");
		list.stream();
	}

	private void stream() {
		IntStream.of(5, 6, 7).forEach(System.out::println);
		System.out.println("----");

		IntStream.range(3, 8).forEach(System.out::println);
		System.out.println("----");

		IntStream.rangeClosed(3, 8).forEach(System.out::println);
		System.out.println("----");
	}

	private void stream2() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
		System.out.println(list.stream().map(integer -> integer * 2).reduce(0, Integer::sum));
	}
}
