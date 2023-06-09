package com.chris.demo.collection.map;

import com.chris.demo.entities.TestData;
import com.chris.demo.entities.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chris
 * @date 2022-09-04 10:36 AM
 * <p>
 * compute（计算)
 * <p>
 * computeIfAbsent(不存在时计算)
 * <p>
 * computeIfPresent(存在时计算)
 * <p>
 * 相同：返回值是返回最新的值
 * 不相同：compute是有则覆盖，没则添加;computeIfAbsent是有则不操作，没则添加;computeIfPresent是有则覆盖，没值的时候不操作
 */
public class MapDemo2 {


    @Test
    public void testMerge1() throws JsonProcessingException {

        List<Student> students = TestData.getStudents();
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Integer> studentScoreMap = new HashMap<>();
        students.forEach(student -> {
            if (studentScoreMap.containsKey(student.getName())) {
                studentScoreMap.put(student.getName(), studentScoreMap.get(student.getName()) + student.getScore());
            } else {
                studentScoreMap.put(student.getName(), student.getScore());
            }
        });

        System.out.println(objectMapper.writeValueAsString(studentScoreMap));
    }


    @Test
    public void testMerge2() throws JsonProcessingException {
        List<Student> students = TestData.getStudents();
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Integer> studentScoreMap = new HashMap<>();
        students.forEach(student -> {
            studentScoreMap.merge(student.getName(), student.getScore(), Integer::sum);
        });

        System.out.println(objectMapper.writeValueAsString(studentScoreMap));
    }

    @Test
    public void mapComputeTest() {
        String k = "key";
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put(k, 1);
        }};
        // 2
        System.out.println(map.compute(k, (key, oldVal) -> oldVal + 1));
        //{key=2}
        System.out.println(map);
    }

    @Test
    public void mapCountComputeTest() {
        List<String> words = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
            add("A");
            add("C");
            add("E");
            add("E");
            add("E");
            add("E");
            add("A");
            add("E");
        }};

        // the word appear times
        Map<String, Integer> wordCountMap = new HashMap<>();
        words.forEach(word -> wordCountMap.compute(word, (key, count) -> {
            if (Objects.isNull(count)) {
                return 1;
            }
            return count + 1;
        }));

        //{A=3, B=1, C=2, E=5}
        System.out.println(wordCountMap);


    }


    @Test
    public void test() {
        System.out.println(1 << 30);
    }

}
