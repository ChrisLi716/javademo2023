package com.chris.design.pattern.strategy;

import lombok.Data;

@Data
public class Stock {
    // 股票交易代码
    private String code;        // 现价
    private Double price;        // 涨幅
    private Double rise;
}
