package com.chris.demo.algorithm;

/**
 * @author Chris
 * @date 2022-07-19 10:20 PM
 * <p>
 * 6个数字最小组合为122345
 * 最大组合为543221
 * 去除这中间规则不允许的即可
 * <p>
 * https://blog.csdn.net/m0_67391377/article/details/123756444
 */

public class PaiLie {
    private static String[] notExistNumber = new String[]{"0", "6", "7", "8", "9"};
    private static String[] mustExistNumber = new String[]{"1", "2", "2", "3", "4", "5"};

    private static boolean isValidNumber(String str) {
        // 检查是否有非法数字
        for (String number : notExistNumber) {
            if (str.contains(number)) {
                return false;
            }
        }

        // 检查是否是这六个数字的组合，不能有222222或者334422等
        for (String number : mustExistNumber) {
            int temp = str.indexOf(number);
            if (temp < 0) {
                return false;
            } else if ((str.indexOf(number, temp + 1) > temp) && str.charAt(temp) != '2') {
                return false;
            }
        }
        return true;

      /*  // 检查4在不在第三位，是返回false
        if (str.charAt(2) == '4') {
            return false;
        }
        // 检查是否存在’3’与’5’相连，有返回false
        return !str.contains("35") && !str.contains("53");*/
    }

    public static void main(String[] args) {
        for (int i = 122345; i <= 543221; i++) {
            if (isValidNumber(String.valueOf(i))) {
                System.out.println(i);
            }
        }
    }


}
