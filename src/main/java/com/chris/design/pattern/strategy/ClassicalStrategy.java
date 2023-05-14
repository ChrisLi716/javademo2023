package com.chris.design.pattern.strategy;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClassicalStrategy {

    /**
     * 高价榜
     */
    public class HighPriceRank implements Strategy {
        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream().sorted(Comparator.comparing(Stock::getPrice).reversed()).collect(Collectors.toList());
        }
    }

    /**
     * 低价榜
     */
    public class LowPriceRank implements Strategy {
        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream().sorted(Comparator.comparing(Stock::getPrice)).collect(Collectors.toList());
        }
    }

    /**
     * 高涨幅榜
     */
    public class HighRiseRank implements Strategy {
        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream().sorted(Comparator.comparing(Stock::getRise).reversed()).collect(Collectors.toList());
        }
    }


    public class Context {
        private Strategy strategy;

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }

        public List<Stock> getRank(List<Stock> source) {
            return strategy.sort(source);
        }
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
         * @param rankType 榜单类型
         * @return 榜单数据
         */
        public List<Stock> getRank(String rankType) {
            // 创建上下文
            Context context = new Context();
            // 这里选择策略
            switch (rankType) {
                case "HighPrice":
                    context.setStrategy(new HighPriceRank());
                    break;
                case "LowPrice":
                    context.setStrategy(new LowPriceRank());
                    break;
                case "HighRise":
                    context.setStrategy(new HighRiseRank());
                    break;
                default:
                    throw new IllegalArgumentException("rankType not found");
            }
            // 然后执行策略
            return context.getRank(data);
        }
    }


}
