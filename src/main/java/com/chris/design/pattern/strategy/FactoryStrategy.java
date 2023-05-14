package com.chris.design.pattern.strategy;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FactoryStrategy {

    /**
     * 高价榜   * 注意申明 Service.value = HighPrice,他是我们的key,下同
     */
    // @Service("HighPrice")
    public class HighPriceRank implements Strategy {
        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream().sorted(Comparator.comparing(Stock::getPrice).reversed()).collect(Collectors.toList());
        }
    }

    /**
     * 低价榜
     */
    // @Service("LowPrice")
    public class LowPriceRank implements Strategy {
        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream().sorted(Comparator.comparing(Stock::getPrice)).collect(Collectors.toList());
        }
    }

    /**
     * 高涨幅榜
     */
    // @Service("HighRise")
    public class HighRiseRank implements Strategy {
        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream().sorted(Comparator.comparing(Stock::getRise).reversed()).collect(Collectors.toList());
        }
    }

    // @Service
    public class RankServiceImpl {
        /**
         * dataService.getSource() 提供原始的股票数据
         */
        @Resource
        private List<Stock> data;
        /**
         * 利用注解@Resource和@Autowired特性,直接获取所有策略类       * key = @Service的value
         */
        @Resource
        private Map<String, Strategy> rankMap;

        /**
         * 前端传入榜单类型, 返回排序完的榜单
         *
         * @param rankType 榜单类型 和Service注解的value属性一致
         * @return 榜单数据
         */
        public List<Stock> getRank(String rankType) {          // 判断策略是否存在
            if (!rankMap.containsKey(rankType)) {
                throw new IllegalArgumentException("rankType not found");
            }
            // 获得策略实例
            Strategy rank = rankMap.get(rankType);
            // 执行策略
            return rank.sort(data);
        }
    }


}
