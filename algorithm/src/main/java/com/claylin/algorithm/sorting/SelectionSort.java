package com.claylin.algorithm.sorting;

public class SelectionSort {
    public static void sort(Comparable[] a) {
        int N;
        if (a == null || (N = a.length) < 3) {
            return;
        }
        int min;
        for (int i = 0; i < N; i++) {
            min = i;
            for (int j = i + 1; j < N; j++) {
                if (a[min].compareTo(a[j]) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                SortUtils.exch(a, i, min);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{2, 13, 4, 1, 451, 131, 45};
        sort(a);
        SortUtils.show(a);
    }
}
