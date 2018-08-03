package com.claylin.algorithm.search.ternaryTree;

import java.util.List;

public class TSTNode {
    private char data;
    private int isEndOfString;
    private TSTNode left;
    private TSTNode eq;
    private TSTNode right;
    private List<String> list;


    public TSTNode(char data) {
        this.data = data;
    }

    public char getData() {
        return data;
    }

    public TSTNode setData(char data) {
        this.data = data;
        return this;
    }

    public int getIsEndOfString() {
        return isEndOfString;
    }

    public TSTNode setIsEndOfString(int isEndOfString) {
        this.isEndOfString = isEndOfString;
        return this;
    }

    public TSTNode getLeft() {
        return left;
    }

    public TSTNode setLeft(TSTNode left) {
        this.left = left;
        return this;
    }

    public TSTNode getEq() {
        return eq;
    }

    public TSTNode setEq(TSTNode eq) {
        this.eq = eq;
        return this;
    }

    public TSTNode getRight() {
        return right;
    }

    public TSTNode setRight(TSTNode right) {
        this.right = right;
        return this;
    }

    public List<String> getList() {
        return list;
    }

    public TSTNode setList(List<String> list) {
        this.list = list;
        return this;
    }
}
