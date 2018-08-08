package com.claylin.algorithm.search.segmentTree;

/**
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 */
public class SegmentTreeUil {

    int st[]; // the array that stores segment tree nodes

    public SegmentTreeUil(int arr[], int n) {
        // height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        // maximum size of segment tree
        int maxSize = 2 * (int) Math.pow(2, x) - 1;

        st = new int[maxSize];

        constructSTUtil(arr, 0, n - 1, 0);
    }

    /**
     * @param ss
     * @param se stating and ending indexes of the segment represented by current node, i.e, st[si]
     * @param qs
     * @param qe staring and ending indexes of query range
     * @param si index of current node in segment tree. initially 0 is passed as root is aways at index 0
     * @return
     */

    int getSumUtil(int ss, int se, int qs, int qe, int si) {
        if (qs <= ss && qe >= se) {
            return st[si];
        }

        if (se < qs || ss > qe) {
            return 0;
        }

        int mid = getMid(ss, se);
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    int constructSTUtil(int arr[], int ss, int se, int si) {
        // if there is one element in array, store it in current node of
        // segment
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // if there are more than one elements, then recur for left and
        // right subtree and store the sum of values in this nodes
        int mid = getMid(ss, se);
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
                constructSTUtil(arr, mid + 1, se, si * 2 + 2);
        return st[si];
    }


    void updateValueUtil(int ss, int se, int i, int diff, int si) {
        if (i < ss || i > se) {
            return;
        }

        // if the input index is in range of this node,
        // then update the value of the node and its children
        st[si] = st[si] + diff;
        if (se != ss) {
            int mid = getMid(ss, se);
            updateValueUtil(ss, mid, diff, i, 2 * si + 1);
            updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
        }
    }

    void updateValue(int arr[], int n, int i, int newVal) {
        if (i < 0 || i > n - 1) {
            System.out.println("invalid input");
            return;
        }

        int diff = newVal - arr[i];

        arr[i] = newVal;

        updateValueUtil(0, n - 1, i, diff, 0);
    }


    private int getMid(int start, int end) {
        return start + (end - start) / 2;
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 4, 1, 51, 123, 14, 3};

        int n = arr.length;
        SegmentTreeUil segment = new SegmentTreeUil(arr, n);


    }
}
