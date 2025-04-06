package com.sparklesimply.array;

public class SlidingWindowVariants {

    /**
     * This method return the minimum length of sub array having sum >=target
     * Each element is added to sum once when i is incremented and each element is removed at most once when j is incremented
     * Time complexity: O(n)
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0, j = 0;
        while(i<n) {
            sum += nums[i++];
            while(sum >= target) {
                minLen = Math.min(minLen, i-j);
                sum -= nums[j++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}
