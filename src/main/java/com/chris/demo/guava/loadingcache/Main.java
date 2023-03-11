package com.chris.demo.guava.loadingcache;

import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther Chris Lee
 * @Date 1/3/2019 10:52
 * @Description https://blog.csdn.net/qq_38567039/article/details/126488902
 */
public class Main {

    /**
     * 与构建Cache类型的对象类似，LoadingCache类型的对象也是通过CacheBuilder进行构建。
     * 不同的是，在调用CacheBuilder的build方法时，必须传递一个CacheLoader类型的参数，CacheLoader的load方法需要我们提供具体实现。
     * 当调用LoadingCache的get方法时，如果缓存不存在对应key的记录，则CacheLoader中的load方法会被自动调用从外存加载数据，
     * “load方法的返回值”会作为“key对应的value”存储到LoadingCache中，并从get方法返回。
     */
    @Test
    public void test1() {
        LoadingCache<Integer, Customer> cutomerCache = CustomerCacheProvider.INSTANCE.getCustomerCache();
        /*
         * V get(K key) : This will either return an already cached value, or else use the cache's CacheLoader to atomically
         * load a new value into the cache
         */
        try {
            System.out.println("Cache size : " + cutomerCache.size());
            System.out.println("Get Customer 1 : " + cutomerCache.get(1).getFirstName());
            System.out.println("Cache size : " + cutomerCache.size());
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Get Customer 1 : " + cutomerCache.get(1).getFirstName());
            System.out.println("Get Customer 3 : " + cutomerCache.get(3).getFirstName());
            System.out.println("Cache size : " + cutomerCache.size());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
