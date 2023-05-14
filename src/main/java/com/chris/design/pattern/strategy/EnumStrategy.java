package com.chris.design.pattern.strategy;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EnumStrategy {
    public enum RankEnum {      // 以下三个为策略实例
        HighPrice {
            @Override
            public List<Stock> sort(List<Stock> source) {
                return source.stream().sorted(Comparator.comparing(Stock::getPrice).reversed()).collect(Collectors.toList());
            }
        }, LowPrice {
            @Override
            public List<Stock> sort(List<Stock> source) {
                return source.stream().sorted(Comparator.comparing(Stock::getPrice)).collect(Collectors.toList());
            }
        }, HighRise {
            @Override
            public List<Stock> sort(List<Stock> source) {
                return source.stream().sorted(Comparator.comparing(Stock::getRise).reversed()).collect(Collectors.toList());
            }
        };

        // 这里定义了策略接口
        public abstract List<Stock> sort(List<Stock> source);
    }

    public class RankServiceImpl {
        /**
         * dataService.getSource() 提供原始的股票数据
         */
        @Resource
        private List<Stock> data;

        /**
         * 前端传入榜单类型, 返回排序完的榜单
         *
         * @param rankType 榜单类型 形似 RankEnum.HighPrice.name()
         * @return 榜单数据
         */
        public List<Stock> getRank(String rankType) {
            // 获取策略，这里如果未匹配会抛 IllegalArgumentException异常
            RankEnum rank = RankEnum.valueOf(rankType);
            // 然后执行策略
            return rank.sort(data);
        }
    }


}
