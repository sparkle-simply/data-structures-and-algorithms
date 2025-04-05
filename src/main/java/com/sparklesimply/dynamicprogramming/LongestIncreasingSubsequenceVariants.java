package com.sparklesimply.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequenceVariants {

    /**
     * This method returns the max length of longest increasing subsequence using dp tabulation approach
     * @param nums
     * @return max length of longest increasing subsequence
     */
    public int LISLength(int[] nums) {
        int n = nums.length;
        if(n <= 1)
            return n;
        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);
        for(int i=0; i<n; i++) {
            if(nums[i] > dp.get(dp.size()-1)) {
                dp.add(nums[i]);
            } else {
                int lb=0, ub=dp.size()-1, mid=0;
                while (lb <= ub) {
                    mid = (lb+ub)/2;
                    if(nums[i] == dp.get(mid))
                        break;
                    else if(nums[i] < dp.get(mid))
                        ub = mid-1;
                    else
                        lb = mid+1;
                }
                if(mid>=0 && mid<dp.size() && nums[i] == dp.get(mid))
                    continue;
                dp.add(ub+1, nums[i]);
            }
        }
        return dp.size();
    }
}
