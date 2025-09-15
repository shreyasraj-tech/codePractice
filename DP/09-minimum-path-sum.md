
# **📌 Minimum Path Sum (LeetCode 64): Revision Notes**

### ✅ **Problem Link**: [Minimum Path Sum - LeetCode](https://leetcode.com/problems/minimum-path-sum/)
---

* Grid size: **m × n**
* Each cell contains a **non-negative integer** (cost)
* Move only **right** or **down**
* Find a path from **top-left `(0,0)`** to **bottom-right `(m-1,n-1)`** with the **minimum path sum**

---

# ⚠️ **Common Mistake in Base Case**

❌ Wrong base case implementation:
```java
if(i < 0 || j < 0) return 0;
if(i == 0 && j == 0) return 1;
````

**Why Wrong?**

* For out of bounds (`i < 0 || j < 0`): returning `0` makes the algorithm think going outside the grid has no cost.
  ✅ Correct: return a **large number** (`Integer.MAX_VALUE`) so invalid paths are never chosen.

* For start cell `(0,0)`: returning `1` ignores the actual grid value.
  ✅ Correct: return `grid[0][0]`, since the cost at the starting cell must be counted.

---

# **✅ 1. Memoization (Top-Down DP)**

👉 *Use recursion + cache results in a DP table*

**State**:
`dp[i][j]` = minimum cost to reach cell `(i,j)`

**Base Case**:

* If `(i < 0 || j < 0)` → out of bounds → return `Integer.MAX_VALUE`
* If `(i == 0 && j == 0)` → return `grid[0][0]`

**Transition**:

$$
dp[i][j] = grid[i][j] + \min(dp[i-1][j], \ dp[i][j-1])
$$

**Java Memoization Code:**

```java
import java.util.Arrays;

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helper(m-1, n-1, grid, dp);
    }

    private int helper(int i, int j, int[][] grid, int[][] dp) {
        if (i < 0 || j < 0) return Integer.MAX_VALUE;
        if (i == 0 && j == 0) return grid[0][0];
        if (dp[i][j] != -1) return dp[i][j];

        int fromTop = helper(i-1, j, grid, dp);
        int fromLeft = helper(i, j-1, grid, dp);

        return dp[i][j] = grid[i][j] + Math.min(fromTop, fromLeft);
    }
}
```

✅ **Time Complexity**: `O(m × n)`
✅ **Space Complexity**: `O(m × n)` + recursion stack

---

# **✅ 2. Tabulation (Bottom-Up DP)**

👉 *Iteratively fill DP table from `(0,0)` to `(m-1,n-1)`*

**State**:
`dp[i][j]` = minimum cost to reach `(i,j)`

**Initialization**:

* `dp[0][0] = grid[0][0]`
* First row: can only come from left
* First column: can only come from top

**Transition**:

$$
dp[i][j] = grid[i][j] + \min(dp[i-1][j], \ dp[i][j-1])
$$

---

**Java Tabulation Code:**

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        // Fill first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] + dp[0][j-1];
        }

        // Fill first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }

        // Fill rest of DP
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[m-1][n-1];
    }
}
```

✅ **Time Complexity**: `O(m × n)`
✅ **Space Complexity**: `O(m × n)`

---

# **✅ 3. Space Optimized DP**

👉 Only the **previous row** is needed at any time

**Optimization**:

* Use a **1D array (`dp[j]`)**
* Transition depends on:

  * `dp[j]` → value from top
  * `curr[j-1]` → value from left

---

**Java Space Optimized Code:**

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    curr[j] = grid[0][0];
                } else {
                    int fromTop = (i > 0) ? dp[j] : Integer.MAX_VALUE;
                    int fromLeft = (j > 0) ? curr[j-1] : Integer.MAX_VALUE;
                    curr[j] = grid[i][j] + Math.min(fromTop, fromLeft);
                }
            }
            dp = curr;
        }
        return dp[n-1];
    }
}
```

✅ **Time Complexity**: `O(m × n)`
✅ **Space Complexity**: `O(n)`

---

# **🚀 Key Takeaways**

* Always count **grid\[i]\[j]** in the cost
* Out-of-bounds must return **large number**, not `0`
* **Memoization** → recursion + cache
* **Tabulation** → bottom-up DP table
* **Space Optimization** → compress to 1D array

---

✨ Save this note — covers all DP approaches for **Minimum Path Sum (LeetCode 64)** ✨

