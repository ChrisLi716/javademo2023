package com.chris.demo.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 14:10
 * @Description
 */
public class ComparableTest {

    public static void main(String[] args) {
        List<Player> footballTeam = new ArrayList<>();

        Player player1 = new Player(59, "John", 20);
        Player player2 = new Player(67, "Roger", 22);
        Player player3 = new Player(45, "Steven", 24);

        footballTeam.add(player1);
        footballTeam.add(player2);
        footballTeam.add(player3);

        comparable(footballTeam);
    }


    private static void comparable(List<Player> footballTeam) {
        System.out.println("Before Sorting : " + footballTeam);
        Collections.sort(footballTeam);
        System.out.println("After Sorting : " + footballTeam);
    }
}
