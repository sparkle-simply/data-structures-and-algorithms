package com.sparklesimply.array;

import java.util.Arrays;

public class SortVariants {

    /**
     * You are given a 0-indexed array ‘NUMS’ consisting of ‘N’ integers.
     * Sort the array ‘NUMS’ in such a way that the first half of the array contains only odd numbers sorted in non-increasing order
     * and the second half contains only even numbers sorted in non-decreasing order.
     * Time complexity: O(n log n)
     */
    public static void sortOddEven(int[] nums) {
        if(nums.length < 2)
            return;

        int oddCount = 0;
        // getting all odd numbers to first half
        for(int i=0; i<nums.length; i++) {
            if((nums[i] % 2) != 0) {
                int temp = nums[oddCount];
                nums[oddCount] = nums[i];
                nums[i] = temp;
                oddCount++;
            }
        }
        // negating odd numbers to sort in non-increasing order
        for(int i=0; i<oddCount; i++)
            nums[i] = -nums[i];
        Arrays.sort(nums, 0, oddCount);
        // negating again to restore the actual odd numbers
        for(int i=0; i<oddCount; i++)
            nums[i] = -nums[i];
        // sorting remaining even numbers in non-decreasing order
        Arrays.sort(nums, oddCount, nums.length);
    }

    public static void sort012(int[] arr)
    {
        int n = arr.length;
        int[] count = new int[3];

        for(int i=0; i<n; i++) {
            count[arr[i]]++;
        }
        int k = 0;
        for(int i=0; i<3; i++) {
            while(count[i]-->0) {
                arr[k] = i;
                k++;
            }
        }
    }
}
