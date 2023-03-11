package com.chris.demo.enums;

import java.util.Scanner;

/**
 * @Auther Chris Lee
 * @Date 1/3/2019 12:18
 * @Description
 */
public class WeightTable {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double earthWeight = Double.parseDouble(scan.nextLine());
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values())
            System.out.printf("Weight on %s is %f%n", p.name(), p.surfaceWeight(mass));
    }
}
