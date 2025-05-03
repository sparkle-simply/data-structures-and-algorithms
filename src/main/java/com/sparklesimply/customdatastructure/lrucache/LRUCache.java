package com.sparklesimply.customdatastructure.lrucache;


import java.util.*;

public class LRUCache {

    private Map<Integer, Integer> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.cache = new LinkedHashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = this.cache.get(key);
        if(value != null) {
            this.cache.remove(key);
            this.cache.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(this.cache.get(key) != null) {
            this.cache.remove(key);
            this.cache.put(key, value);
            return;
        }

        int cacheSize = this.cache.size();
        if(cacheSize == this.capacity) {
            int leastRecentlyAccessedKey = this.cache.keySet().iterator().next();
            this.cache.remove(leastRecentlyAccessedKey);
        }
        this.cache.put(key, value);
    }
}
