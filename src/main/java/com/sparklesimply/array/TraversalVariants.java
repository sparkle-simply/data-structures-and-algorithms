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

}
