package com.chris.demo.enums;

import java.util.function.DoubleBinaryOperator;

/**
 * @Auther Chris Lee
 * @Date 1/3/2019 12:29
 * @Description
 */
public enum Operation_Lambda {
    PLUS("+", Double::sum),

    MINUS("-", (x, y) -> x - y),

    TIMES("*", (x, y) -> x * y),

    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;


    Operation_Lambda(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }

}
