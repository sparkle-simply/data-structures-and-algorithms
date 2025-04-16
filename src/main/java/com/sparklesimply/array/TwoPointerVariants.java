package com.sparklesimply.array;

import java.util.*;

public class TwoPointerVariants {

    /**
     * Problem statement: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * Notice that the solution set must not contain duplicate triplets.
     * Time complexity: O(n^2) considering the constraints
     * @param nums array
     * @return list of triplets that sum to 0
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if(n < 3)
            return null;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int l, r, sum=0;
        for(int i=0; i<n-2; i++) {
            if(i>0 && nums[i-1]==nums[i])
                continue;
            l = i+1;
            r = n-1;
            while(l<r) {
                sum = nums[i] + nums[l] + nums[r];
                if(sum == 0) {
                    result.add(List.of(nums[i], nums[l], nums[r]));
                    while(l<r && nums[l] == nums[l+1])
                        l++;
                    while(l<r && nums[r] == nums[r-1])
                        r--;
                    l++;
                    r--;
                } else if(sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return result;
    }

}
