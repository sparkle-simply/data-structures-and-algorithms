package com.sparklesimply.array;

import java.util.*;

public class SearchVariants {

    /**
     * Problem statement: There is an integer array nums sorted in ascending order (with distinct values).
     * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
     * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
     * You must write an algorithm with O(log n) runtime complexity.
     * @param nums array
     * @param target num
     * @return index of the target num
     */
    public int searchDistinct(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n-1;
        return searchDistinctUtil(nums, low, high, target);
    }
    private int searchDistinctUtil(int[] nums, int low, int high, int target) {
        if(low <= high) {
            int mid = (low+high)/2;
            if(nums[mid] == target)
                return mid;
            if(nums[low] <= nums[mid]) {
                if(target >= nums[low] && target < nums[mid])
                    return searchDistinctUtil(nums, low, mid-1, target);
                return searchDistinctUtil(nums, mid+1, high, target);

            }
            if(target > nums[mid] && target <= nums[high])
                return searchDistinctUtil(nums, mid+1, high, target);
            return searchDistinctUtil(nums, low, mid-1, target);
        }
        return -1;
    }

    /**
     * Problem statement: There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
     * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
     * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
     * You must decrease the overall operation steps as much as possible.
     * @param nums array
     * @param target num
     * @return true if target is present in nums
     */
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n-1;
        return searchUtil(nums, low, high, target);
    }
    private boolean searchUtil(int[] nums, int low, int high, int target) {
        if(low <= high) {
            int mid = (low+high)/2;
            if(nums[mid] == target)
                return true;
            // handling when numbers are same
            if(nums[low] == nums[mid] && nums[mid] == nums[high])
                return searchUtil(nums, low+1, high-1, target);
            if(nums[low] <= nums[mid]) {
                if(target >= nums[low] && target < nums[mid])
                    return searchUtil(nums, low, mid-1, target);
                return searchUtil(nums, mid+1, high, target);

            }
            if(target > nums[mid] && target <= nums[high])
                return searchUtil(nums, mid+1, high, target);
            return searchUtil(nums, low, mid-1, target);
        }
        return false;
    }

    /**
     * Problem statement: Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
     * If target is not found in the array, return [-1, -1].
     * You must write an algorithm with O(log n) runtime complexity.
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int[] result = new int[]{-1, -1};
        if(n == 0)
            return result;
        result[0] = findFirst(n, nums, target);
        if(result[0] == -1)
            return result;
        result[1] = findLast(n, nums, target);
        return result;
    }
    private int findFirst(int n, int[] nums, int target) {
        int l = 0, r = n - 1;
        int result = -1;
        while(l <= r) {
            int mid = (l+r)/2;
            if(nums[mid] == target) {
                result = mid;
                r = mid - 1;
            } else if(nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }
    private int findLast(int n, int[] nums, int target) {
        int l = 0, r = n - 1;
        int result = -1;
        while(l <= r) {
            int mid = (l+r)/2;
            if(nums[mid] == target) {
                result = mid;
                l = mid + 1;
            } else if(nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }

    /**
     * Problem statement: Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if(n < 3)
            return false;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for(int x : nums) {
            if(min1 >= x)
                min1 = x;
            else if(min2 >= x)
                min2 = x;
            else
                return true;
        }
        return false;
    }

    /**
     * Problem statement: Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
     * Return true if there is a 132 pattern in nums, otherwise, return false.
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if(n < 3)
            return false;
        int[] min = new int[n];
        min[0] = nums[0];
        for(int i=1; i<n; i++)
            min[i] = Math.min(min[i-1], nums[i]);
        Stack<Integer> st = new Stack<>();
        for(int j=n-1; j>=0; j--) {
            if(nums[j] > min[j]) {
                while(!st.isEmpty() && st.peek() <= min[j])
                    st.pop();
                if(!st.isEmpty() && st.peek() < nums[j])
                    return true;
                st.push(nums[j]);
            }
        }
        return false;
    }

    /**
     * Problem statement: You are given a 0-indexed integer array nums and an integer k. Your task is to perform the following operation exactly k times in order to maximize your score:
     * Select an element m from nums.
     * Remove the selected element m from the array.
     * Add a new element with a value of m + 1 to the array.
     * Increase your score by m.
     * Return the maximum score you can achieve after performing the operation exactly k times.
     * Time complexity: O(n log n)
     * Note: we can also use another approach to find maximum value in nums and then compute sum with (in place of nums[n-1]) maximum value with Time complexity: O(n)
     * @param nums
     * @param k
     * @return
     */
    public int maximizeSum(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int sum  = 0;
        while(k-->0) {
            sum += nums[n-1];
            nums[n-1]++;
        }
        return sum;
    }

}
