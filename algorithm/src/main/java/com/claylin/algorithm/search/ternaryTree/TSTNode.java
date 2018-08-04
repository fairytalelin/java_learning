package com.claylin.algorithm.search.ternaryTree;

import com.claylin.algorithm.collections.IndexedTreeSet;

public class TSTNode<T> {
    private char value;
    private TSTNode<T> left;
    private TSTNode<T> eq;
    private TSTNode<T> right;

    private boolean isEndOfString;

    private IndexedTreeSet<T> prefixSet;

    public TSTNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public TSTNode<T> setValue(char value) {
        this.value = value;
        return this;
    }

    public TSTNode<T> getLeft() {
        return left;
    }

    public TSTNode<T> setLeft(TSTNode<T> left) {
        this.left = left;
        return this;
    }

    public TSTNode<T> getEq() {
        return eq;
    }

    public TSTNode<T> setEq(TSTNode<T> eq) {
        this.eq = eq;
        return this;
    }

    public TSTNode<T> getRight() {
        return right;
    }

    public TSTNode<T> setRight(TSTNode<T> right) {
        this.right = right;
        return this;
    }

    public boolean isEndOfString() {
        return isEndOfString;
    }

    public TSTNode<T> setEndOfString(boolean endOfString) {
        isEndOfString = endOfString;
        return this;
    }

    public IndexedTreeSet<T> getPrefixSet() {
        return prefixSet;
    }

    public TSTNode<T> setPrefixSet(IndexedTreeSet<T> prefixSet) {
        this.prefixSet = prefixSet;
        return this;
    }

    @Override
    public String toString() {
        return "TSTNode{" +
                "value=" + value +
                ", left=" + left +
                ", eq=" + eq +
                ", right=" + right +
                ", isEndOfString=" + isEndOfString +
                ", prefixSet=" + prefixSet +
                '}';
    }
}
