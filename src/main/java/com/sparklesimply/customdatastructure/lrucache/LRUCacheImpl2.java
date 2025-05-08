package com.sparklesimply.customdatastructure.lrucache;


import java.util.*;

public class LRUCacheImpl2 {

    // Using doubly linked list to maintain order of cache elements
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private Map<Integer, Node> cache;
    private int capacity;
    Node head;
    Node tail;

    public LRUCacheImpl2(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
       Node node = this.cache.get(key);
       if(node == null)
           return -1;
       // moving recently used element to tail
       this.moveToTail(node);
       return node.value;
    }

    public void put(int key, int value) {
        Node node = this.cache.get(key);
        if(node != null) {
            // removing, updating value and moving recently used element to tail
            this.removeNode(node);
            node.value = value;
            this.moveToTail(node);
        } else {
            Node newNode = new Node(key, value);
            if(this.cache.size() >= this.capacity) {
                // removing least recently used element from head
                Node lruNode = head.next;
                this.removeNode(lruNode);
                this.cache.remove(lruNode.key);
            }
            // moving new node to tail marking as recently used
            this.moveToTail(newNode);
            this.cache.put(key, newNode);
        }
    }

    public void moveToTail(Node node) {
        node.prev = this.tail.prev;
        node.next = this.tail;
        this.tail.prev.next = node;
        this.tail.prev = node;
    }

    public void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
}
