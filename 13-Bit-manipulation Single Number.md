## Leetcode-137: Single Number II

Here is the link to the problem on  [LeetCode - Single Number II](https://leetcode.com/problems/single-number-ii/)

### Problem Description

Given an integer array `nums` where every element appears **three times** except for one, which appears exactly once. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

### Constraints

- `1 <= nums.length <= 3 * 10^4`
- `-2^31 <= nums[i] <= 2^31 - 1`

### Example 1:

- **Input**: `nums = [2,2,3,2]`
- **Output**: `3`
- **Explanation**: Every element appears three times except for 3.

### Example 2:

- **Input**: `nums = [0,1,0,1,0,1,99]`
- **Output**: `99`
- **Explanation**: Every element appears three times except for 99.

### Solution

Here is a Java solution for the problem:

````java
import java.util.Arrays;

class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);

        for(int i=0;i<nums.length;i+=3){
            if(i+1>=nums.length || nums[i]!=nums[i+1]){
                return nums[i];
            }
        }

        return -1;
    }
}


````
### Method - 2 BitWise manipulation

````java

class Solution {
  public int singleNumber(int[] nums) {
    int ans = 0;

    for (int i = 0; i < 32; ++i) {
      int sum = 0;
      for (final int num : nums)
        sum += num >> i & 1;
        sum %= 3;
        ans |= sum << i;
    }

    return ans;
  }
}

