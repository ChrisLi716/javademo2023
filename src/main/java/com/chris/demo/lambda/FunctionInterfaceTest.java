package com.chris.demo.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther Chris Lee
 * @Date 12/21/2018 12:45
 * @Description
 */
public class FunctionInterfaceTest {
	
	public static void main(String[] args) {
		FunctionInterfaceTest test = new FunctionInterfaceTest();
		
		LambdaListTesting();
		// lambdaTest(test);
	}
	
	private static void LambdaListTesting() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		long count = list.stream().filter(str -> 0 == (Integer.parseInt(str) % 2)).count();
		System.out.println(count);
		
	}
	
	private static void lambdaTest(FunctionInterfaceTest test) {
		test.func(() -> test.outputStr("Hello Lambda!"));

		test.funcParams(i -> test.outputStr("Hello Lambda!" + i));
		
		boolean result = test.funcParams2((Integer i) -> true);
		System.out.println("result:" + result);
		
		boolean result2 = test.funcParams2((Integer i) -> {
			test.outputStr("Hello Lambda!" + i);
			return i == 1;
		});
		System.out.println("result2:" + result2);
	}
	
	private void func(FunctionInterface fi) {
		fi.testLambda();
	}
	
	private void funcParams(FunctionInterfaceParams<Integer> fi) {
		int i = 2;
		fi.testLambda(i);
	}
	
	private boolean funcParams2(FunctionInterfaceParams2<Integer> fi) {
		int i = 2;
		return fi.testLambda(i);
	}
	
	private void outputStr(String str) {
		System.out.println(str);
	}
}
