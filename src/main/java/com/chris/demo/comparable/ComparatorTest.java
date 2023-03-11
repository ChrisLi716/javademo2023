package com.chris.demo.comparable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 14:46
 * @Description
 */
public class ComparatorTest {
	
	public static void main(String[] args) {
		List<Player> footballTeam = new ArrayList<>();
		Player player1 = new Player(59, "John", 20);
		Player player2 = new Player(67, "Roger", 19);
		Player player3 = new Player(45, "Steven", 24);
		footballTeam.add(player1);
		footballTeam.add(player2);
		footballTeam.add(player3);
        System.out.println("Before Sorting : " + footballTeam);
		/*PlayerRankingComparator playerRankingComparator = new PlayerRankingComparator();
		comparator(footballTeam, playerRankingComparator);
		
		PlayerAgeComparator ageComparator = new PlayerAgeComparator();
		comparator(footballTeam, ageComparator);*/

		footballTeam.sort((o1, o2) -> o2.getAge() - o1.getAge());
		
		// footballTeam.sort(Comparator.comparingInt(Player::getAge));
        System.out.println("After Sorting : " + footballTeam);
	}
	
	private static <T extends Player> void comparator(List<T> footballTeam, Comparator<T> comparator) {
		footballTeam.sort(comparator);
		System.out.println("After Sorting : " + footballTeam);
		// Collections.sort(footballTeam, comparator);
	}
}
