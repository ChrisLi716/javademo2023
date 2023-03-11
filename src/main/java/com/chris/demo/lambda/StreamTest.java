package com.chris.demo.lambda;

import com.chris.SysPathUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther Chris Lee
 * @Date 2/12/2019 12:54
 * @Description
 */
public class StreamTest {

    public static void main(String[] args) throws Exception {
        workcounting();
    }

    private static void testStream() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("求和：" + nums.stream()// 转成Stream
                .filter(team -> team != null)// 过滤
                .distinct()// 去重
                .mapToInt(num -> num * 2)// map操作
                .skip(2)// 跳过前2个元素
                .limit(4)// 限制取前4个元素
                .peek(System.out::println)// 流式处理对象函数
                .sum());//
    }

    private static void testParallel() {
        long[] arrayOfLong = new long[20000];
        // 1.给数组随机赋值
        Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
        // 2.打印出前10个元素
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        // 3.数组排序
        Arrays.parallelSort(arrayOfLong);
        // 4.打印排序后的前10个元素
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }


    private static void streamOf() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 5};
        Integer[] evenIntArray = Stream.of(intArray).filter(i -> i % 2 == 0).toArray(Integer[]::new);
        Stream.of(evenIntArray).forEach(System.out::println);
    }

    private static void workcounting() {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            String path = SysPathUtils.getCurrentClzPath(StreamTest.class);
            if (StringUtils.isNotEmpty(path)) {
                File file = new File(path + File.separator + "README");
                reader = new FileReader(file);
                List<String> lines = new ArrayList<>();
                bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();

                while (null != line) {
                    lines.add(line);
                    line = bufferedReader.readLine();
                }

                System.out.println("the amount of lines : " + lines.size());
                List<String> allwords = lines.stream().filter(StringUtils::isNotEmpty).map(oneLine -> oneLine.split(
                        " ")).flatMap(Arrays::stream).collect(Collectors.toList());
                System.out.println(allwords.size());

                Long amount =
                        lines.stream().filter(StringUtils::isNotEmpty).map(oneLine -> oneLine.split(" ")).flatMap(Arrays::stream).count();
                System.out.println(amount);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                if (null != bufferedReader) {
                    reader.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void mapTest() {
        String[] words = new String[]{"Hello", "World"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        a.forEach(System.out::print);


        List<String> aa = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        aa.forEach(System.out::print);


    }

    private static void mapTest2() {

        List<List<String>> buildInList = new ArrayList<>();
        buildInList.add(Arrays.asList("1"));
        buildInList.add(Arrays.asList("2", "3"));
        buildInList.add(Arrays.asList("4", "5", "6"));
        buildInList.add(Arrays.asList("7", "8", "9", "0"));

//        List<String> strList = buildInList.stream().flatMap(childList -> streamOf(childList)).collect(Collectors
//        .toList());
    }


    @Test
    public void fileterTest() {
//        List<Integer> ints1 = Arrays.asList(1, 2, 3, 54, 56);
        List<Integer> ints1 = new ArrayList<>();
        List<Integer> ints2 = Arrays.asList(1, 2, 3, 4, 1, 56);
        ints2.stream().filter(x -> !ints1.contains(x)).forEach(System.out::println);

    }


}
