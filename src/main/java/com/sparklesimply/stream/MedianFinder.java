package com.sparklesimply.stream;

import java.util.PriorityQueue;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 * Reference: <a href="https://leetcode.com/problems/find-median-from-data-stream/?envType=study-plan-v2&envId=top-interview-150">Problem Statement</a>
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 */
public class MedianFinder {
    PriorityQueue<Integer> leftHalf;
    PriorityQueue<Integer> rightHalf;
    double med;

    /**
     * Initializes the MedianFinder object
     */
    public MedianFinder() {
        this.leftHalf = new PriorityQueue<Integer>((x, y) -> y-x);
        this.rightHalf = new PriorityQueue<Integer>();
        this.med = 1.0;
    }

    /**
     * Adds the integer num from the data stream to the data structure
     * @param num
     */
    public void addNum(int num) {
        int leftHalfSize = leftHalf.size();
        int rightHalfSize = rightHalf.size();

        if(leftHalfSize > rightHalfSize) {
            if(num < med) {
                this.rightHalf.offer(this.leftHalf.poll());
                this.leftHalf.offer(num);
            } else {
                this.rightHalf.offer(num);
            }
            med = (this.leftHalf.peek() + this.rightHalf.peek())/2.0;
        } else if(leftHalfSize < rightHalfSize) {
            if(num > med) {
                this.leftHalf.offer(this.rightHalf.poll());
                this.rightHalf.offer(num);
            } else {
                this.leftHalf.offer(num);
            }
            med = (this.leftHalf.peek() + this.rightHalf.peek())/2.0;
        } else {
            if(num < med) {
                this.leftHalf.offer(num);
                med = (double) this.leftHalf.peek();
            } else {
                this.rightHalf.offer(num);
                med = (double) this.rightHalf.peek();
            }
        }

    }

    /**
     * Returns the median of all elements so far
     * @return
     */
    public double findMedian() {
        return med;
    }
}



