# **📌 Unique Paths II (LeetCode 63): Revision Notes**

### ✅ **Problem Link**: [Unique Paths II - LeetCode](https://leetcode.com/problems/unique-paths-ii/)
---

* Grid size: **m × n**
* Move only **right** or **down**
* Some cells contain **obstacles (1 = blocked, 0 = free)**
* Count total distinct paths from **top-left `(0,0)`** to **bottom-right `(m-1,n-1)`**

---

# **✅ 1. Memoization (Top-Down DP)**

👉 *Use recursion + cache results in a DP table*  

**State**:  
`dp[i][j]` = number of ways to reach cell `(i,j)`  

**Base Case**:
- If `(i < 0 || j < 0)` → out of bounds → return 0  
- If `(obstacleGrid[i][j] == 1)` → blocked → return 0  
- If `(i == 0 && j == 0)` → starting cell → return 1  

**Transition**:

$$
dp[i][j] = dp[i-1][j] + dp[i][j-1]
$$

**Java Memoization Code:**

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        return help(m-1, n-1, obstacleGrid, dp);
    }

    private int help(int i, int j, int[][] grid, int[][] dp) {
        if (i < 0 || j < 0 || grid[i][j] == 1) return 0;
        if (i == 0 && j == 0) return 1;
        if (dp[i][j] != -1) return dp[i][j];

        int up = help(i-1, j, grid, dp);
        int left = help(i, j-1, grid, dp);

        return dp[i][j] = up + left;
    }
}
````

✅ **Time Complexity**: `O(m × n)`
✅ **Space Complexity**: `O(m × n)` + recursion stack

---

# **✅ 2. Tabulation (Bottom-Up DP)**

👉 *Iteratively fill DP table from `(0,0)` to `(m-1,n-1)`*

**State**:
`dp[i][j]` = number of ways to reach `(i,j)`

**Initialization**:

* If start or end is blocked → return 0
* First row & column depend on obstacles:

  * If obstacle encountered, mark all further cells in that row/col as 0

**Transition**:

$$
dp[i][j] = dp[i-1][j] + dp[i][j-1] \quad \text{if grid[i][j] == 0}
$$

---

### 🔎 Side-by-Side Example

Input Grid (`0 = free`, `1 = obstacle`) vs DP Table after filling:

```
Grid:                DP Table:
0 0 0                1 1 1
0 1 0                1 0 1
0 0 0                1 1 2
```

➡ The obstacle blocks paths (`dp = 0`)
➡ Final Answer = `2`

---

**Java Tabulation Code:**

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;
        
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
       

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                 if (obstacleGrid[i][j] == 1) {dp[i][j]=0;}
                else if(i==0 && j==0) continue;
                    else{  
                    int pathsFromTop = (i > 0) ? dp[i - 1][j] : 0;
                    int pathsFromLeft = (j > 0) ? dp[i][j - 1] : 0;
                    dp[i][j] = pathsFromTop + pathsFromLeft;
                            }
                    }
            }
        

        return dp[n-1][m-1];
       

    }
}
```

✅ **Time Complexity**: `O(m × n)`
✅ **Space Complexity**: `O(m × n)`

---

# **✅ 3. Space Optimized DP**

👉 Only the **previous row** is needed at any time

**Optimization**:

* Use **1D array** (`dp[j]`)
* Transition depends on `dp[j]` (up) and `curr[j-1]` (left)

**Java Space Optimized Code:**

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];

        // Start cell
        dp[0] = (grid[0][0] == 0) ? 1 : 0;

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    curr[j] = 0; // obstacle
                } else {
                    if (j > 0) curr[j] += curr[j-1]; // from left
                    curr[j] += dp[j];               // from top
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

* Paths come only from **above** or **left**
* Obstacles block paths (set dp = 0)
* **Memoization** → recursion + cache
* **Tabulation** → iterative 2D DP
* **Space Optimization** → compress to 1D DP

---

✨ Save this note — covers all DP approaches for **Unique Paths II** ✨

