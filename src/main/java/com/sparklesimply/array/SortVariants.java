package com.sparklesimply.array;

import java.util.Arrays;

public class SortVariants {

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
}
