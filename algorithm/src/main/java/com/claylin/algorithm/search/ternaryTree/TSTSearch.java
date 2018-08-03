package com.claylin.algorithm.search.ternaryTree;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TSTSearch {
    private static TSTNode root;
    private TSTNodeComparator comparator;

    public TSTSearch() {
        this.comparator = new TSTNodeComparator();
    }

    public static TSTNode insert(String data, int fromIndex, TSTNode root) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }

        if (root == null) {
            root = new TSTNode(data.charAt(fromIndex));
        }

        if (root.getData() > data.charAt(fromIndex)) {
            root.setLeft(insert(data, fromIndex, root.getLeft()));
        } else if (root.getData() < data.charAt(fromIndex)) {
            root.setRight(insert(data, fromIndex, root.getRight()));
        } else {
            List<String> list = root.getList();
            if (list == null) {
                list = new ArrayList<>();
                root.setList(list);
            }
            list.add(data);
            if (fromIndex < data.length() - 1) {
                root.setEq(insert(data, fromIndex + 1, root.getEq()));
            } else {
                root.setIsEndOfString(1);
            }
        }
        return root;
    }

    public static void traverseTSTUtil(TSTNode root, StringBuilder data, int depth) {
        if (root == null) {
            return;
        }
        traverseTSTUtil(root.getLeft(), data, depth);
        data.append(root.getData());
        if (root.getIsEndOfString() == 1) {
            System.out.println(data.toString());
        }

        traverseTSTUtil(root.getEq(), data, depth + 1);

        traverseTSTUtil(root.getRight(), data, depth);

    }

    public static void traverseTST(TSTNode root) {
        StringBuilder sb = new StringBuilder();
        traverseTSTUtil(root, sb, 0);
    }


    public static int searchTST(TSTNode root, String data, int fromIndex) {
        if (root == null) {
            return 0;
        }

        if (root.getData() > data.charAt(fromIndex)) {
            return searchTST(root.getLeft(), data, fromIndex);
        } else if (root.getData() < data.charAt(fromIndex)) {
            return searchTST(root.getRight(), data, fromIndex);
        } else {
            if (fromIndex + 1 == data.length()) {
                return root.getIsEndOfString();
            }
            return searchTST(root.getEq(), data, fromIndex + 1);
        }
    }

    public static List<String> findprefix(TSTNode root, String data, int index) {
        if (root == null) {
            return null;
        }

        if (root.getData() > data.charAt(index)) {
            return findprefix(root.getLeft(), data, index);
        } else if (root.getData() < data.charAt(index)) {
            return findprefix(root.getRight(), data, index);
        } else {
            if (index + 1 == data.length()) {
                return root.getList();
            } else {
                return findprefix(root.getEq(), data, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        root = insert("cat", 0, root);
        root = insert("cats", 0, root);
        root = insert("bugs", 0, root);

        String word = "cat";

        int r = searchTST(root, word, 0);
        System.out.println(r);

        List<String> ret = findprefix(root, "cats", 0);
        System.out.println(ret);
    }

}
