package com.claylin.algorithm.sorting;

public class MergeSort {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        sort(a, low, mid - 1);
        sort(a, mid + 1, high);
        /*merge(a, low, mid, high);*/
    }

    private static void merge(Comparable a, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            /*aux[i] = a[i];*/
        }

        int i = low, j = mid + 1, k = low;

        /*for (; k <= high; k++) {
            if (aux[i] > aux[j]) {
                a[k] = aux[j];
                j++;
            } else if (aux[i] < aux[j]) {
                a[k] = aux[i];
                i++;
            } else if (i > mid) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }*/
    }

    public static void main(String[] args) {
        /*Integer[] a = new Integer[2, 34, -1, 232, 51, 12, 0, 2, 1, 14, 2];
        MergeSort.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%s,", a[i])
        }*/
    }
}
