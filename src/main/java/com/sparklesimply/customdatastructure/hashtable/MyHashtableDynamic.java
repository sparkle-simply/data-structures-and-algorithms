package com.sparklesimply.customdatastructure.hashtable;

import java.util.LinkedList;
import java.util.List;

/**
 * HashTable in Java is collection class that implements Map interface, provides a way to store <key, value> pairs where key is unique.
 * It's synchronized, thread safe and can be used efficiently in concurrent applications.
 * Internally, HashTable uses hash table to store key value pairs. A hash table uses hash function to compute the index (pointing to the corresponding bucket) based on key's hasCode to determine the placement of key-value pairs
 * The better the distribution, better the performance
 */
public class MyHashtableDynamic {
    private static final int INITIAL_CAPACITY = 20;
    private static final float LOAD_FACTOR_THRESHOLD = 0.75f;
    private LinkedList<Entry>[] table;
    private int size;

    public MyHashtableDynamic() {
        this.table = new LinkedList[INITIAL_CAPACITY];
        for(int i=0; i< table.length; i++) {
            this.table[i] = new LinkedList<Entry>();
        }
        this.size = 0;
    }

    private int hash(Object key) {
        return (Math.abs(key.hashCode()) % this.table.length);
    }

    private void resize() {
        int newCapacity = this.table.length * 2;
        LinkedList<Entry>[] newTable = new LinkedList[newCapacity];

        for(LinkedList<Entry>  bucket : this.table) {
            for(Entry entry : bucket) {
                int newBuncketIndex = Math.abs(entry.key.hashCode()) % newCapacity;
                newTable[newBuncketIndex].add(entry);
            }
        }

        this.table = newTable;

    }

    public void put(Object key, Object value) {
        if ((float) size / this.table.length > LOAD_FACTOR_THRESHOLD) {
            this.resize();
        }
        int bucketIndex = this.hash(key);
        List<Entry> bucket = this.table[bucketIndex];

        for(Entry entry : bucket) {
            if(entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry(key, value));
        this.size++;
    }

    public Object get(Object key) {
        int bucketIndex = hash(key);
        List<Entry> bucket = this.table[bucketIndex];
        for(Entry entry : bucket) {
            if(entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove (Object key) {
        int bucketIndex = hash(key);
        List<Entry> bucket = this.table[bucketIndex];
        for(Entry entry : bucket) {
            if(entry.key.equals(key)) {
                bucket.remove(entry);
                this.size--;
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
