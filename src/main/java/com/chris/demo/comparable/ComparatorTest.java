package com.chris.demo.comparable;

import cn.hutool.json.JSONUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 14:46
 * @Description
 */
public class ComparatorTest {

    public static void main(String[] args) {
        List<Player> footballTeam = new ArrayList<>();
        Player player_1 = new Player(59, "John", 20);
        Player player2 = new Player(67, "Roger", 19);
        Player player_3 = new Player(30, "John", 15);
        Player player_2 = new Player(59, "John", 23);
        Player player_5 = new Player(20, "John", 23);
        Player player3 = new Player(45, "Steven", 24);
        Player player_4 = new Player(30, "John", 24);

        footballTeam.add(player_1);
        footballTeam.add(player2);
        footballTeam.add(player_2);
        footballTeam.add(player_3);
        footballTeam.add(player3);
        footballTeam.add(player_4);
        footballTeam.add(player_5);
        System.out.println("Before Sorting : " + footballTeam);
        /*PlayerRankingComparator playerRankingComparator = new PlayerRankingComparator();
        comparator(footballTeam, playerRankingComparator);

        PlayerAgeComparator ageComparator = new PlayerAgeComparator();
        comparator(footballTeam, ageComparator);

        footballTeam.sort((o1, o2) -> o2.getAge() - o1.getAge());

        // footballTeam.sort(Comparator.comparingInt(Player::getAge));
        System.out.println("After Sorting : " + footballTeam);*/

        Map<String, List<Player>> nameGroup = footballTeam.stream().collect(Collectors.groupingBy(Player::getName));
        nameGroup.forEach((k, ps) -> ps.sort(new PlayerComplexComparator()));

        List<Player> sorted1Playes = nameGroup.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("1: sortedPlayes:" + JSONUtil.toJsonStr(sorted1Playes));

        Map<String, List<Player>> nameGroup2 = footballTeam.stream().collect(Collectors.groupingBy(Player::getName,
                Collectors.collectingAndThen(Collectors.toList(), (lst) -> {
                    lst.sort(new PlayerComplexComparator());
                    return lst;
                })));
        List<Player> sorted2Playes = nameGroup2.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("2: sortedPlayes:" + JSONUtil.toJsonStr(sorted2Playes));

    }

    private static <T extends Player> void comparator(List<T> footballTeam, Comparator<T> comparator) {
        footballTeam.sort(comparator);
        System.out.println("After Sorting : " + footballTeam);
        // Collections.sort(footballTeam, comparator);
    }
}
