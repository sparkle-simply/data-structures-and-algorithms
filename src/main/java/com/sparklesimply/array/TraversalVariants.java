package com.sparklesimply.array;

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

}
