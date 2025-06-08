## Leetcode-1143: Longest Common Subsequence

Here is the link to the problem on [Leetcode](https://leetcode.com/problems/longest-common-subsequence/)

### Problem Description

Given two strings `text1` and `text2`, return the length of their **longest common subsequence**.  
A **subsequence** of a string is a new string generated from the original string with some characters (can be none) deleted **without changing the relative order** of the remaining characters.

A **common subsequence** of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return `0`.

### Constraints

- `1 <= text1.length, text2.length <= 1000`
- `text1` and `text2` consist of only lowercase English characters.

---

### Example 1:

- **Input**: `text1 = "abcde"`, `text2 = "ace"`
- **Output**: `3`
- **Explanation**: The LCS is `"ace"`, which has a length of 3.

### Example 2:

- **Input**: `text1 = "abc"`, `text2 = "abc"`
- **Output**: `3`
- **Explanation**: The LCS is `"abc"` itself.

### Example 3:

- **Input**: `text1 = "abc"`, `text2 = "def"`
- **Output**: `0`
- **Explanation**: There is no common subsequence.

---

### Tabular Visualization (Dynamic Programming Table)

Let’s understand the process with `text1 = "abcde"` and `text2 = "ace"`

|   dp[i][j]   | '' | a | c | e |
|-------------|----|---|---|---|
| **''**      |  0 | 0 | 0 | 0 |
| **a**       |  0 | 1 | 1 | 1 |
| **b**       |  0 | 1 | 1 | 1 |
| **c**       |  0 | 1 | 2 | 2 |
| **d**       |  0 | 1 | 2 | 2 |
| **e**       |  0 | 1 | 2 | 3 |

- Each `dp[i][j]` stores the length of LCS of `text1[0...i-1]` and `text2[0...j-1]`.
- When `text1.charAt(i-1) == text2.charAt(j-1)`, we take diagonal value `dp[i-1][j-1] + 1`.
- Otherwise, take `max(dp[i-1][j], dp[i][j-1])`.

---

### Solution

Here is a Java solution for the problem:

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];         
    }
}
