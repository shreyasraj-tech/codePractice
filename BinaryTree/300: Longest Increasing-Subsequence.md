## Leetcode-300: Longest Increasing Subsequence

Here is the link to the problem on [LeetCode - Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)

### Problem Description

Given an integer array `nums`, return the length of the longest strictly increasing subsequence.

A subsequence is derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, `[3,6,2,7]` is a subsequence of `[0,3,1,6,2,2,7]`.

### Constraints

- `1 <= nums.length <= 2500`
- `-10^4 <= nums[i] <= 10^4`

### Example 1:

- **Input**: `nums = [10,9,2,5,3,7,101,18]`
- **Output**: `4`
- **Explanation**: The longest increasing subsequence is `[2,3,7,101]`, therefore the length is 4.

### Example 2:

- **Input**: `nums = [0,1,0,3,2,3]`
- **Output**: `4`

### Example 3:

- **Input**: `nums = [7,7,7,7,7,7,7]`
- **Output**: `1`

### Solution

Here is a Java solution for the problem:

````java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        int n = nums.length;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) { // we can use j <= i - 1; both are valid
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += 1;
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}
