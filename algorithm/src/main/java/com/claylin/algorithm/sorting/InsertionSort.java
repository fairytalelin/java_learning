package com.claylin.algorithm.sorting;

public class InsertionSort {
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && SortUtils.less(a[j], a[j - 1]); j--) {
                SortUtils.exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{232, 1231, 123, 123, 11, 1, 1354, 5, 55};
        sort(a);
        SortUtils.show(a);
    }
}
