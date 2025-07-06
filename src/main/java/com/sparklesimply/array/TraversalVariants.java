package com.sparklesimply.array;

import java.util.*;

public class TraversalVariants {

    /**
     * Problem statement: Given an array nums of size n, return the majority element.
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
     * Approach: evaluating the candidate with count>=0 way
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0, candidate = nums[0];
        for(int i=0; i<nums.length; i++) {
            if(count == 0)
                candidate = nums[i];
            if(nums[i] == candidate)
                count++;
            else
                count--;
        }
        return candidate;
    }

    /**
     * Problem statement: Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] trackForward = new int[n];
        int[] trackBackward = new int[n];
        int[] trackResult = new int[n];
        trackForward[0] = 1;
        trackBackward[n-1] = 1;
        for(int i=1; i<n; i++) {
            trackForward[i] = trackForward[i-1] * nums[i-1];
        }
        for(int i=n-2; i>=0; i--) {
            trackBackward[i] = trackBackward[i+1] * nums[i+1];
        }
        for(int i=0; i<n; i++)
            trackResult[i] = trackForward[i]*trackBackward[i];
        return trackResult;
    }

    /**
     * Problem statement: Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
     * Time complexity: O(n)
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        reverseNums(nums, 0, n-1);
        reverseNums(nums, 0, k-1);
        reverseNums(nums, k, n-1);
    }
    private void reverseNums(int[] nums, int start, int end) {
        while(start <= end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Problem statement: Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
     * Time complexity: O(n)
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int i=0, j=0;
        int ns = s.length();
        if(ns == 0)
            return true;
        int nt = t.length();
        while (i<ns && j<nt) {
            if(s.charAt(i) == t.charAt(j)) {
                i++; j++;
            }
            else
                j++;
            if(i == ns)
                return true;
        }
        return false;
    }
}
