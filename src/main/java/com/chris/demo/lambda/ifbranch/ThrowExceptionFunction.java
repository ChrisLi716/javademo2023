package com.chris.demo.lambda.ifbranch;

@FunctionalInterface
public interface ThrowExceptionFunction {

    /**
     * 抛出异常信息
     *
     * @param message 异常信息
     **/
    void throwMessage(String message);
}
