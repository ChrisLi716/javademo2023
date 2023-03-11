package com.chris.demo.guava.loadingcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @Auther Chris Lee
 * @Date 1/3/2019 10:50
 * @Description LoadingCache是Cache的子接口，相比较于Cache，当从LoadingCache中读取一个指定key的记录时，如果该记录不存在，则LoadingCache可以自动执行加载数据到缓存的操作.
 * 在调用CacheBuilder的build方法时必须传递一个CacheLoader类型的参数，CacheLoader的load方法需要我们提供具体实现。
 * 当调用LoadingCache的get方法时，如果缓存不存在对应key的记录，则CacheLoader中的load方法会被自动调用从外存加载数据，“load方法的返回值”会作为“key对应的value”存储到LoadingCache中，并从get方法返回。
 */
public enum CustomerCacheProvider {
    INSTANCE;

    private final LoadingCache<Integer, Customer> customerCache;

    CustomerCacheProvider() {
        CacheLoader<Integer, Customer> cacheLoader = new CacheLoader<Integer, Customer>() {
            @Override
            public Customer load(Integer id) {
                return CustomerRepo.findById(id);
            }
        };
        customerCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(2, TimeUnit.SECONDS).build(cacheLoader);
    }

    public LoadingCache<Integer, Customer> getCustomerCache() {
        return customerCache;
    }
}
