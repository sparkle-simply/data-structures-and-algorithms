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

    /**
     * Problem statement: Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
     * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
     * The tests are generated such that there is exactly one solution. You may not use the same element twice.
     * Your solution must use only constant extra space.
     * Time complexity: O(n)
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0, r = n-1, sum = 0;
        while(l<r) {
            sum = numbers[l] + numbers[r];
            if(sum == target)
                return new int[] {l+1, r+1};
            if(sum < target)
                l++;
            else
                r--;
        }
        return new int[] {-1, -1};

    }

    /**
     * Problem statement: You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     * Approach: starting at ending index, utilizing extra space of nums1 for merge
     * Another basic approach to use temp array and then merge using temp and nums2 elements in nums1
     * Time complexity: O(n)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, k = m+n-1;
        while(j>=0) {
            if(i>=0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

    }

    /**
     * Problem statement: Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
     * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
     * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
     * Return k.
     * Approach: we'll use two pointers, whenever we'll encounter new value while iterating nums array with i -> update j and update nums[j] with new encountered value nums[i]
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        while(i<n) {
            if(nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
            i++;
        }
        return j+1;
    }

    /**
     * Problem statement: Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
     * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
     * Return k after placing the final result in the first k slots of nums.
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public int removeWithAtmostTwoDuplicates(int[] nums) {
        int n = nums.length;
        if(n<=2)
            return n;
        int i = 1, j = 0, k = 1;
        while(i<n) {
            // if current number is same as previous
            if(nums[i] == nums[i-1]) {
                if(k<2) { // checking if we have seen less than 2 occurrance
                    j++;
                    nums[j] = nums[i];
                    k++;
                }
            } else {
                // if current number is different from previous, updating the first occurance
                j++;
                nums[j] = nums[i];
                k = 1;
            }
            i++;
        }
        return j+1;
    }

}
