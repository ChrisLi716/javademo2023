package com.chris.design.pattern.strategy;

import java.util.List;

public interface Strategy {
    /**
     * 将股票列表排序
     *
     * @param source 源数据
     * @return 排序后的榜单
     */
    List<Stock> sort(List<Stock> source);
}
