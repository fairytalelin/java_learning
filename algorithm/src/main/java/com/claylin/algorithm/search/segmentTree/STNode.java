package com.claylin.algorithm.search.segmentTree;

public class STNode {
    private int left;
    private int right;

    private int sum;

    public int getLeft() {
        return left;
    }

    public STNode setLeft(int left) {
        this.left = left;
        return this;
    }

    public int getRight() {
        return right;
    }

    public STNode setRight(int right) {
        this.right = right;
        return this;
    }

    public int getSum() {
        return sum;
    }

    public STNode setSum(int sum) {
        this.sum = sum;
        return this;
    }

    @Override
    public String toString() {
        return "STNode{" +
                "left=" + left +
                ", right=" + right +
                ", sum=" + sum +
                '}';
    }
}
