package com.claylin.algorithm.sorting;

public class SortUtils {

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void show(Comparable[] comparables) {
        for (int i = 0; i < comparables.length; i++) {
            System.out.print(comparables[i] + ",");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] comparables) {
        for (int i = 1; i < comparables.length; i++) {
            if (less(comparables[i], comparables[i - 1])) return false;
        }
        return true;
    }
}
