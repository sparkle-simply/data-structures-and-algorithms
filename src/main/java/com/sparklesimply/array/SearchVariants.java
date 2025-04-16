package com.sparklesimply.array;

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

}
