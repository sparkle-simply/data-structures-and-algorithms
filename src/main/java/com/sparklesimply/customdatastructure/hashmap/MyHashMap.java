package com.sparklesimply.customdatastructure.hashmap;

import java.util.LinkedList;
import java.util.List;

/**
 * HashMap in Java is collection class that implements Map interface, provides a way to store <key, value> pairs where key is unique and maybe null.
 * It's non-synchronized, not thread safe and can be used efficiently single threaded applications.
 * Internally, HashMap uses hash table to store key value pairs. A hash table uses hash function to compute the index (pointing to the corresponding bucket) based on key's hashCode to determine the placement of key-value pairs
 * The better the distribution, better the performance
 * Time complexity: O(n) with LinkedList
 */
public class MyHashMap {
    private static final int INITIAL_CAPACITY = 20;
    private LinkedList<Entry>[] table;

    public MyHashMap() {
        this.table = new LinkedList[INITIAL_CAPACITY];
        for(int i=0; i< table.length; i++) {
            this.table[i] = new LinkedList<Entry>();
        }
    }

    private int hash(Object key) {
        // hashing the key to 0 for null value
        if(key == null)
            return 0;
        return (Math.abs(key.hashCode()) % this.table.length);
    }

    public void put(Object key, Object value) {
        int bucketIndex = this.hash(key);
        List<Entry> bucket = this.table[bucketIndex];

        for(Entry entry : bucket) {
            if(key == null ? entry.key == null : entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry(key, value));
    }

    public Object get(Object key) {
        int bucketIndex = hash(key);
        List<Entry> bucket = this.table[bucketIndex];
        for(Entry entry : bucket) {
            if(key == null ? entry.key ==  null : entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove (Object key) {
        int bucketIndex = hash(key);
        List<Entry> bucket = this.table[bucketIndex];
        for(Entry entry : bucket) {
            if(key == null ? entry.key == null : entry.key.equals(key)) {
                bucket.remove(entry);
                return;
            }
        }
    }

    public void displayHashTable() {
        for(int i=0; i<this.table.length; i++) {
            System.out.println("Bucket i: "+ i +" values:");
            for(Entry entry : this.table[i]) {
                System.out.println("key: "+entry.key+ " value: "+ entry.value);
            }
        }
    }
}
