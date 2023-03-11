package com.chris.demo.algorithm;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Chris
 * @date 2022-07-30 10:49 PM
 */
public class Pyramid {

    @Test
    public void PrintPyramid() {
        int layerNum = 10;
        int whitespaceNum;

        for (int i = 1; i <= layerNum; i++) {
            whitespaceNum = layerNum - i;
            print(i, whitespaceNum);
        }

    }

    private void print(int starNum, int whitespaceNum) {
        String starCollect = Stream.generate(() -> "* ").limit(starNum).collect(Collectors.joining());
        String whitesapceCollect = Stream.generate(() -> " ").limit(whitespaceNum).collect(Collectors.joining());
        System.out.println(whitesapceCollect + starCollect);
    }
}
