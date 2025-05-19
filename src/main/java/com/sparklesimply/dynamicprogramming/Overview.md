#### Problem: Climb Stairs
###### Brute force approach:
- for each step, we'll try both possibilities to climb one step or two step
- recursively compute both the combination until we reach the top
- this will lead to re-computation of sub-problems multiple times
- also for each recursive step, we'll be branching to two-way (n-1) (n-2) and this will lead the time complexity of O(2^n)

###### Optimized approach:
- as we know we were recomputing the subproblems multiple times in recursive approach
- here we will store number of ways to reach at each step in dp array
- each step can be reched from number of ways to reach previous step and one before previous step
- we will iteratively compute dp[i] which is number of ways to reach ith step using dp[i-1] and dp[i-2]
- we will be touching each step exactly once, time complexity: O(n)
***
#### Problem: House Robber
###### Brute force approach:
- for each house, to get the maximum amount robbed, we'll try two possibilities to skip the current house and rob the next or vice versa
- recursilvely approach both the possibilities for getting the max amount robbed without considering two adjascednt houses
- here, while we try two possibilities for each house, the combination woll lead to time complexity of O(2^n) and subproblems are computed multiple times
###### Optimized approach:
- we'll store the maximum amount robbed for each house in dp array
- for each house dp[i], either we'll skip the current house and max amount will be the the previous best robbed amount dp[i-1] or we'll rob the current one and add it to the best amount we got 2 house back as nums[i] + dp[i-2]
- here we'll check each house exactly once, time complexity O(n)
- this can be further space optmized where we'll track for previous two values
***
#### Problem: Coin change to get min coins required to make up to amount
###### Brute force approach:
- for each amount, we'll recursively check for the coins by either including the coin (may happen multiple times) or not including for making the target amount
- in this approach we'll try all possible combinations for amount with provide coins, time complexity O(amount^n), n is coins length
  
###### Optimized approach:
- we'll store the min number of coins to make each amount in dp array
- here dp[i] represents number of coins required to make up the ith amount which will be calculated by either taking the coin or not taking the coin at each step iteratively using subproblems solutions for making up the amount
- time complexity is O(amount*n), n is coins length
***
#### Problem: Longest Increasing subsequence
###### Brute force approach:
- for all elements we'll check for all previous elements to calculate longest subsequence for current position
- we'll store longest subsequence for each element in array
- here dp[i] will represent the longest subsequence until i, at each i position, ww'll checkk all previous j, j: 0 to i -> this will be updated as max of dp[i] or d[j]+1 and maxLen will be tracked for max of maxLen and dp[i]
- time complexity: O(n)

###### Optimized approach:
- we'll maintain sorted list of increasing subsequence
- if curr element is greater than the last element of sorted list, then we'll append it to the list
- or else we'll binary search for the smallest ending in sorted list where the curr element is greater and is to be updated
- iteratively we'll track all elements and size of sorted list will be longest increasing subsequence
- time complexity: O(n log n)
***
#### Problem: Decode ways
###### Brute force approach:
- recursively compute all the combinations that are valid in range of (1-25) using current and next character
- for each step, we'll try both possibilities to count the number of combinations
- this will lead to re-computation of sub-problems multiple times

###### Optimized approach:
- as we know we were recomputing the sub-problems multiple times in recursive approach
- here we will store count of valid combinations in dp array
- each step we'll check if curr character is non-zero and next character combined with current is valid combination
- we will iteratively compute dp[i] in reverse order to track curr and next character which count of valid combinations
- we will be touching each step exactly once, time complexity: O(n)
***