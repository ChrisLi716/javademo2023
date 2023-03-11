package com.chris.demo.enums;

/**
 * @Auther Chris Lee
 * @Date 1/3/2019 12:29
 * @Description
 */
public enum Operation_V2 {
    PLUS {
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        double apply(double x, double y) {
            return x / y;
        }
    };

    abstract double apply(double x, double y);
}
