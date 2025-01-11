package com.chris.demo.comparable;

import java.util.Comparator;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 14:23
 * @Description
 */
public class PlayerComplexComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        int i = o1.getRanking() - o2.getRanking();
        if (i == 0) {
            return o1.getAge() - o2.getAge();
        } else {
            return i;
        }
    }
}
