package com.claylin.algorithm.exercise;

import java.util.HashMap;
import java.util.Map;

public class TransfromNum {
    private static Map<String, Long> map = new HashMap<>();

    static {
        map.put("一", 1L);
        map.put("二", 2L);
        map.put("三", 3L);
        map.put("四", 4L);
        map.put("五", 5L);
        map.put("六", 6L);
        map.put("七", 7L);
        map.put("八", 8L);
        map.put("九", 9L);
    }

    public static void main(String[] args) {
        String str1 = "一亿零九十万零七十六";
        String[] array = str1.split("亿");
        long sum = 0;
        if (array.length == 2 || str1.contains("亿")) {
            sum += caculateSix(array[0]);
            if (array.length == 1) {
                str1 = "";
            } else {
                str1 = array[1];
            }
        }
        if (str1.isEmpty()) {
            System.out.println(sum);
            return;
        }
        array = str1.split("万");
        if (array.length == 2 || str1.contains("万")) {
            sum += caculateFive(array[0]);
            if (array.length == 1) {
                str1 = "";
            } else {
                str1 = array[1];
            }
        }
        if (str1.isEmpty()) {
            System.out.println(sum);
            return;
        }
        sum += caculateFour(str1);
        System.out.println(sum);
    }


    // 千
    private static long caculateFour(String str) {
        long num = 0;
        long n = 1;
        str = str.replaceAll("零", "");

        String[] array = str.split("千");

        if (array.length == 2 || str.contains("千")) {
            num += map.get(array[0]) * 1000;
            if (array.length == 1) {
                str = "";
            } else {
                str = array[1];
            }
        }

        if (str.isEmpty()) {
            return num;
        }

        array = str.split("百");

        if (array.length == 2 || str.contains("百")) {
            num += map.get(array[0]) * 100;
            if (array.length == 1) {
                str = "";
            } else {
                str = array[1];
            }
        } else {
            str = array[0];
        }

        if (str.isEmpty()) {
            return num;
        }

        array = str.split("十");

        if (array.length == 2) {
            num += (map.get(array[0]) == null ? 1 : map.get(array[0])) * 10 + map.get(array[1]);
        } else if (array.length == 1 && str.contains("十")) {
            num += map.get(array[0]) * 10;
        } else if (array.length == 0 && str.contains("十")) {
            num += 10;
        } else if (array.length == 1 && !str.contains("十")) {
            num += map.get(array[0]);
        }

        return num;
    }

    // 万
    private static long caculateFive(String str) {
        return caculateFour(str) * 10000;
    }

    // 亿
    private static long caculateSix(String str1) {

        long sum = 0;

        String[] array = str1.split("万");

        if (array.length == 2) {
            sum += caculateFive(array[0]);
            str1 = array[1];
        }

        sum += caculateFour(str1);

        return sum * 100000000;
    }
}
