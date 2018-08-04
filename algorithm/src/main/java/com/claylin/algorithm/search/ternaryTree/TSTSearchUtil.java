package com.claylin.algorithm.search.ternaryTree;

import com.claylin.algorithm.collections.IndexedTreeSet;
import org.springframework.util.StringUtils;

import java.util.Comparator;

/**
 * Created by lwc on 2018/8/4.
 */
public class TSTSearchUtil {

    public static <T> TSTNode<T> insert(TSTNode<T> root, T store, String data, int startIndex, Comparator<T> comparator) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }

        if (root == null) {
            root = new TSTNode<>(data.charAt(startIndex));
        }

        int comparedResult = root.getValue() - data.charAt(startIndex);

        if (comparedResult > 0) {
            root.setLeft(insert(root.getLeft(), store, data, startIndex, comparator));
        } else if (comparedResult < 0) {
            root.setRight(insert(root.getRight(), store, data, startIndex, comparator));
        } else {
            IndexedTreeSet<T> prefixSet = root.getPrefixSet();
            if (prefixSet == null) {
                prefixSet = new IndexedTreeSet<>(comparator);
                root.setPrefixSet(prefixSet);
            }
            prefixSet.add(store);

            if (startIndex + 1 < data.length()) {
                root.setEq(insert(root.getEq(), store, data, startIndex + 1, comparator));
            } else {
                root.setEndOfString(true);
            }
        }

        return root;
    }

    public static <T> TSTNode<T> searchTST(TSTNode<T> root, String data, int startIndex) {
        if (root == null) {
            return null;
        }

        int comparedResult = root.getValue() - data.charAt(startIndex);

        if (comparedResult > 0) {
            return searchTST(root.getLeft(), data, startIndex);
        } else if (comparedResult < 0) {
            return searchTST(root.getRight(), data, startIndex);
        } else {
            if (startIndex + 1 == data.length()) {
                return root;
            } else {
                return searchTST(root.getEq(), data, startIndex + 1);
            }
        }
    }

    public static <T> void delete(TSTNode<T> root, T store, String data, int startIndex) {
        if (root == null) {
            return;
        }

        if (StringUtils.isEmpty(data)) {
            return;
        }

        int comparedResult = root.getValue() - data.charAt(startIndex);
        if (comparedResult > 0) {
            delete(root.getLeft(), store, data, startIndex);
        } else if (comparedResult < 0) {
            delete(root.getRight(), store, data, startIndex);
        } else {
            IndexedTreeSet<T> prefixSet = root.getPrefixSet();
            if (prefixSet != null) {
                prefixSet.remove(store);
            }
            if (startIndex + 1 < data.length()) {
                delete(root.getEq(), store, data, startIndex + 1);
            }
        }
    }

    public static void main(String[] args) {

    }
}
