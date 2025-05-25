package com.sparklesimply.customdatastructure.hashtable;

import java.util.TreeMap;

/**
 * HashTable in Java is collection class that implements Map interface, provides a way to store <key, value> pairs where key is unique.
 * It's synchronized, thread safe and can be used efficiently in concurrent applications.
 * Internally, HashTable uses hash table to store key value pairs. A hash table uses hash function to compute the index (pointing to the corresponding bucket) based on key's hashCode to determine the placement of key-value pairs
 * The better the distribution, better the performance
 * Time complexity: O(log n) with TreeMap
 */
public class MyTreeMapHashtable {
    private static final int INITIAL_CAPACITY = 20;
    private TreeMap<Object, Object>[] table;

    public MyTreeMapHashtable() {
        this.table = new TreeMap[INITIAL_CAPACITY];
        for(int i=0; i< table.length; i++) {
            this.table[i] = new TreeMap<>();
        }
    }

    private int hash(Object key) {
        return (Math.abs(key.hashCode()) % this.table.length);
    }

    public void put(Object key, Object value) {
        int bucketIndex = this.hash(key);
        TreeMap<Object, Object> bucket = this.table[bucketIndex];

        bucket.put(key, new Entry(key, value));
    }

    public Object get(Object key) {
        int bucketIndex = hash(key);
        TreeMap<Object, Object> bucket = this.table[bucketIndex];
        return bucket.get(key);
    }

    public void remove (Object key) {
        int bucketIndex = hash(key);
        TreeMap<Object, Object> bucket = this.table[bucketIndex];
        bucket.remove(key);
    }

    public void displayHashTable() {
        for(int i=0; i<this.table.length; i++) {
            System.out.println("Bucket i: "+ i +" values:");
            for(Object key : this.table[i].keySet()) {
                System.out.println("key: "+key+ " value: "+ this.table[i].get(key));
            }
        }
    }
}
