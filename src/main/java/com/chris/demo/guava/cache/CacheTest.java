package com.chris.demo.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheTest {

    /**
     * 通过CacheBuilder类构建一个缓存对象，CacheBuilder类采用builder设计模式，它的每个方法都返回CacheBuilder本身，直到build方法被调用
     */
    @Test
    public void test1() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("word", "Hello Guava Cache");
        System.out.println(cache.getIfPresent("word"));
    }

    /**
     * 设置最大存储
     * Guava Cache可以在构建缓存对象时指定缓存所能够存储的最大记录数量。
     * 当Cache中的记录数量达到最大值后，再调用put方法向其中添加对象，Guava会先从当前缓存的对象记录中选择一条删除掉，腾出空间后，再将新的对象存储到Cache中。
     * 第一个值：null
     * 第二个值：value2
     * 第三个值：value3
     */
    @Test
    public void test2() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("第一个值：" + cache.getIfPresent("key1"));
        System.out.println("第二个值：" + cache.getIfPresent("key2"));
        System.out.println("第三个值：" + cache.getIfPresent("key3"));
    }

    /**
     * 设置过期时间
     * <p>
     * 可以通过CacheBuilder类的expireAfterAccess和expireAfterWrite两个方法为缓存中的对象指定过期时间
     * 使用CacheBuilder构建的缓存不会“自动”执行清理和逐出值，也不会在值到期后立即执行或逐出任何类型。
     * 相反，它在写入操作期间执行少量维护，或者在写入很少的情况下偶尔执行读取操作。
     * 其中，expireAfterWrite方法指定对象被写入到缓存后多久过期，expireAfterAccess方法指定对象多久没有被访问后过期。
     * <p>
     * 第1次取到key1的值为：value1
     * 第2次取到key1的值为：value1
     * 第3次取到key1的值为：value1
     * 第4次取到key1的值为：null
     * 第5次取到key1的值为：null
     */
    @Test
    public void test3() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1", "value1");
        int time = 1;
        while (time <= 5) {
            System.out.println("第" + time++ + "次取到key1的值为：" + cache.getIfPresent("key1"));
            Thread.sleep(1000);
        }
    }

    /**
     * 通过CacheBuilder的expireAfterAccess方法，指定如果Cache中存储的对象超过3秒没有被访问，就会过期。
     * while中的代码每sleep一段时间，就会访问一次Cache中存储的对象key1；每次访问key1之后，下次sleep的时间会加长一秒。
     * 也可以同时用expireAfterAccess和expireAfterWrite方法指定过期时间，这时只要对象满足两者中的一个条件，就会被自动过期删除。
     * <p>
     * 睡眠1秒后取到key1的值为：value1
     * 睡眠2秒后取到key1的值为：value1
     * 睡眠3秒后取到key1的值为：null
     * 睡眠4秒后取到key1的值为：null
     * 睡眠5秒后取到key1的值为：null
     */
    @Test
    public void test4() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1", "value1");
        int time = 1;
        while (time <= 5) {
            Thread.sleep(time * 1000L);
            System.out.println("睡眠" + time++ + "秒后取到key1的值为：" + cache.getIfPresent("key1"));
        }
    }


    /**
     * 显示清除缓存
     * <p>
     * 调用Cache的invalidateAll或invalidate方法，可以显示删除Cache中的记录。
     * invalidate方法一次只能删除Cache中一个记录，接收的参数是要删除记录的key。
     * invalidateAll方法可以批量删除Cache中的记录；当没有传任何参数时，invalidateAll方法将清除Cache中的全部记录。
     * <p>
     * cache size:4
     * invalidate key1 cache size:3
     * invalidate key2 key3 cache size:1
     * invalidate all cache size:0
     * null
     * null
     * null
     */
    @Test
    public void test5() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        System.out.println("cache size:" + cache.size());
        cache.invalidate("key1");
        System.out.println("invalidate key1 cache size:" + cache.size());
        List<String> list = new ArrayList<>();
        list.add("key2");
        list.add("key3");
        cache.invalidateAll(list);  //批量清除list中全部key对应的记录
        System.out.println("invalidate key2 key3 cache size:" + cache.size());
        cache.invalidateAll();
        System.out.println("invalidate all cache size:" + cache.size());
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key3"));
    }

    /**
     * 移除动作监听器
     * <p>
     * 可以为Cache对象添加一个移除监听器。这样，当有记录被删除时，可以感知到这个事件
     * <p>
     * 这个存在问题,当内存数据清理掉时候,RemovalListener不会触发
     * 因为
     * Guava并不保证在过期时间到了之后立刻删除该key，如果你此时去访问这这个key，它会检测是否过期，过期则移除.
     * 所以过期时间到了之后你去访问这个key会显示这个key已经移除，但是你如果不做任何操作，那么这个key还在内存中。
     * <p>
     * [key1:value1] is removed!
     * [key2:value2] is removed!
     * [key3:value3] is removed!
     * [key4:value4] is removed!
     * [key5:value5] is removed!
     */
    @Test
    public void test6() {
        RemovalListener<String, String> listener = new RemovalListener<String, String>() {
            public void onRemoval(RemovalNotification<String, String> notification) {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        cache.put("key5", "value5");
        cache.put("key6", "value6");
        cache.put("key7", "value7");
        cache.put("key8", "value8");
    }


    private static final Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .build();

    /**
     * 自动加载
     * <p>
     * Cache的get方法有两个参数，第一个参数是从Cache中获取记录的key，第二个记录是一个Callable对象。
     * 当缓存中已经存在key对应的记录时，get方法直接返回key对应的记录。如果缓存中不包含key对应的记录，Guava会启动一个线程执行Callable对象中的call方法.
     * call方法的返回值会作为“key对应的值”被存储到缓存中，并且被get方法返回。
     * <p>
     * 有两个线程共享同一个Cache对象，两个线程同时调用get方法获取同一个key对应的值。由于key对应的值不存在，所以两个线程都在get方法处阻塞。
     * 此处在call方法中调用Thread.sleep(1000)，模拟程序从外存加载数据的时间消耗。
     * 虽然是两个线程同时调用get方法，但只有一个get方法中的Callable会被执行(只打印出load1或者load2)。
     * Guava可以保证当有多个线程同时访问Cache中的一个key时，如果key对应的记录不存在，Guava只会启动一个线程执行get方法中Callable参数对应的任务，加载数据存到缓存。
     * 当加载完数据后，任何线程中的get方法都会获取到key对应的值。
     * <p>
     * thread1
     * thread2
     * load2
     * thread1 auto load by Callable 2
     * thread2 auto load by Callable 2
     */
    @Test
    public void test7() throws InterruptedException {
        new Thread(() -> {
            System.out.println("thread1");
            try {
                String value = cache.get("key", () -> {
                    System.out.println("load1"); //加载数据线程执行标志
                    Thread.sleep(1000); //模拟加载时间
                    return "auto load by Callable 1";
                });
                System.out.println("thread1 " + value);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("thread2");
            try {
                String value = cache.get("key", () -> {
                    System.out.println("load2"); //加载数据线程执行标志
                    Thread.sleep(1000); //模拟加载时间
                    return "auto load by Callable 2";
                });
                System.out.println("thread2 " + value);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * 统计信息
     * <p>
     * 可以对Cache的命中率、加载数据时间等信息进行统计。
     * 在构建Cache对象时，通过CacheBuilder的recordStats方法，可以开启统计信息的开关。
     * 开关开启后，Cache会自动对缓存的各种操作进行统计，调用Cache的stats方法可以查看统计后的信息。
     */
    @Test
    public void test8() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .recordStats() //开启统计信息开关
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");
        System.out.println(cache.stats()); //获取统计信息
    }

}
