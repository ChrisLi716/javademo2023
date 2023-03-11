package com.chris.demo.lambda.ifbranch;

import org.junit.Test;

public class ConditionUtil {

    /**
     * 如果参数为true抛出异常
     *
     * @param b boolean
     * @return com.example.demo.func.ThrowExceptionFunction
     **/
    public static ThrowExceptionFunction isTure(boolean b) {

        return (errorMessage) -> {
            if (b) {
                throw new RuntimeException(errorMessage);
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param b boolean
     * @return BranchHandler
     **/
    public static BranchHandler isTureOrFalse(boolean b) {

        return (trueHandle, falseHandle) -> {
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param str string
     * @return PresentOrElseHandler
     **/
    public static PresentOrElseHandler<?> isBlankOrNoBlank(String str) {

        return (consumer, runnable) -> {
            if (str == null || str.length() == 0) {
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }


    @Test
    public void testTrue() {
        ConditionUtil.isTure(true).throwMessage("throw exception when it's true");
    }

    @Test
    public void testFalse() {
        ConditionUtil.isTure(false).throwMessage("throw exception when it's false");
    }


    @Test
    public void testIsTureOrFalse() {
        ConditionUtil.isTureOrFalse(false).trueOrFalseHandle(() -> System.out.println("true, I begin to do sth."),
                () -> System.out.println("false, I do nothing."));
    }

    @Test
    public void testIsBlankOrNoBlank() {
        ConditionUtil.isBlankOrNoBlank("").presentOrElseHandle(System.out::println, () -> System.out.println("empty string"));
    }
}
