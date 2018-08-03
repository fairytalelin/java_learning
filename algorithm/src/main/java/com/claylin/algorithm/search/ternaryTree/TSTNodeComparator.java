package com.claylin.algorithm.search.ternaryTree;

import java.util.Comparator;
import java.util.Objects;

public class TSTNodeComparator implements Comparator<TSTNode> {
    @Override
    public int compare(TSTNode o1, TSTNode o2) {
        Objects.requireNonNull(o1);
        Objects.requireNonNull(o2);

        char ch1 = o1.getData();
        char ch2 = o2.getData();

        return ch1 - ch2;

    }
}
