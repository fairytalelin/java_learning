package com.claylin.algorithm.collections;

import java.util.NavigableMap;

/**
 * Created by lwc on 2018/7/21.
 */
public interface IndexedNavigableMap<K, V> extends NavigableMap<K, V> {
    /**
     * Returns the key located at the index offset from the beginning
     * of the sorted map
     *
     * @param index index of the key
     * @return the key {@code key} located at the index (@code index) offset
     * from the beginning of the sorted map
     * @throws ArrayIndexOutOfBoundsException if the specified index is less than 0 or greater than size-1
     */
    K exactKey(int index);

    /**
     * Returns a key-value mapping associated with the key located at the index offset from the beginning
     * of the sorted map
     *
     * @param index index of the key
     * @return the entry with the key {@code key} located at the index (@code index) offset
     * from the beginning of the sorted map
     * @throws ArrayIndexOutOfBoundsException if the specified index is less than 0 or greater than size-1
     */
    Entry<K, V> exactEntry(int index);

    /**
     * Searches the specified tree map for the specified key using the
     * put algorithm. Calculates its offset from the beginning of the sorted map using weights.
     *
     * @param k the key
     * @return index of the search key, if it is contained in the tree map;
     * otherwise a NullPointerException is thrown
     * @throws NullPointerException if the specified key is null or does not exist
     */
    int keyIndex(K k);

}
