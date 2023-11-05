package com.chris.demo.lambda.ifbranch;

import java.util.function.Consumer;

@FunctionalInterface
public interface PresentOrElseHandler<T> {
    /**
     * 值不为空时执行消费操作
     * 值为空时执行其他的操作
     *
     * @param action      值不为空时，执行的消费操作
     * @param emptyAction 值为空时，执行的操作
     **/

    void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);
}
