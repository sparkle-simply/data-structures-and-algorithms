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

    /**
     * Problem statement: Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
     * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
     * Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
     * Return k
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        for(int i=0, j=n-1; i<j;) {
            if(nums[i] == val && nums[j] != val) {
                // swap(nums, i, j);
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            } else if(nums[i] != val) {
                i++;
            } else if(nums[j] == val) {
                j--;
            }
        }
        int count = 0;
        for(int num : nums) {
            if(num != val)
                count++;
        }
        return count;
    }

}
