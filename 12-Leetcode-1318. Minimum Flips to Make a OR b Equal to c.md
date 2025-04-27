## 1318. Minimum Flips to Make a OR b Equal to c

Here is the link to the problem on LeetCode: [1318. Minimum Flips to Make a OR b Equal to c](https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/)

### Problem Description

Given 3 integers `a`, `b`, and `c`. The goal is to make `a OR b` equal to `c`. You are allowed to flip any bit (change 0 to 1 or 1 to 0) in `a` and `b` to achieve the goal.

Return the minimum number of flips required.

### Example 1:

- **Input**: `a = 2`, `b = 6`, `c = 5`
- **Output**: `3`
- **Explanation**: 
  - Original: `a = 010`, `b = 110`, `c = 101`
  - Steps:
    - Flip the second bit of `a` (0 to 1) → `a = 110`
    - Flip the third bit of `b` (0 to 1) → `b = 111`
    - Flip the third bit of `a` (1 to 0) → `a = 100`
  - Total 3 flips needed.

### Example 2:

- **Input**: `a = 4`, `b = 2`, `c = 7`
- **Output**: `1`
- **Explanation**: 
  - Original: `a = 100`, `b = 010`, `c = 111`
  - Only the third bit of `b` needs to be flipped to 1.

### Solution

Here is a Java solution for the problem:

```java
class Solution {
    public int minFlips(int a, int b, int c) {
        int res = 0;
        while (a != 0 || b != 0 || c != 0) {
            if ((c & 1) == 1) {
                if ((a & 1) == 0 && (b & 1) == 0) res++;
            } else res += (a & 1) + (b & 1);
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return res;
    }
}
```

### Explanation

1. **Bitwise Operations**:
   - Check the least significant bits (LSB) of `a`, `b`, and `c`.
   - If the LSB of `c` is 1, and both `a` and `b` have 0 in that position, one flip is required.
   - If the LSB of `c` is 0, any 1s in `a` or `b` must be flipped to 0, so add the count of set bits to the result.

2. **Shifting**:
   - Right shift `a`, `b`, and `c` by one bit to move to the next position and repeat the process until all bits are processed.

This efficient approach ensures we calculate the minimum number of flips needed using bitwise checks and shifts.
