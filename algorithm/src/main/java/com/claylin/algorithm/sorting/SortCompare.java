package com.claylin.algorithm.sorting;

import org.springframework.util.StopWatch;

public class SortCompare {
    public static double time(String alg, Double[] a) {
        StopWatch timer = new StopWatch();
        timer.start("sortCompare");
        if (alg.equals("insertion")) {
            InsertionSort.sort(a);
        }
        if (alg.equals("selection")) {
            SelectionSort.sort(a);
        }
        timer.stop();
        return timer.getTotalTimeMillis();
    }


}
