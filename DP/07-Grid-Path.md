
 # **📌Unique Paths Problem: Revision Notes**

### ✅ **Problem link**: (from GeeksforGeeks) [Number of Paths (GFG)](https://www.geeksforgeeks.org/problems/number-of-paths0926/1)
---

* Grid size: **m × n**
* Move only **right** or **down**
* Count total distinct paths from top-left `(0,0)` to bottom-right `(m-1,n-1)`

---

# **✅ Memoization (Top-Down DP)**

👉 *Use recursion + cache results in a DP table*

**State**: `dp[i][j]` = number of ways to reach cell `(i,j)`
**Base Case**:

* if `i==0 && j==0` → 1 way to stand on the starting cell
* if `i<0 || j<0` → 0 ways (out of grid)

**Transition**:

$$
dp[i][j] = dp[i-1][j] + dp[i][j-1]
$$

(coming from above or left)

**Java Memoization Skeleton:**

```java
public int numberOfPaths(int m, int n) {
    int[][] dp = new int[m][n];
    for(int i=0;i<m;i++) Arrays.fill(dp[i], -1);
    return help(m-1, n-1, dp);
}

private int help(int i, int j, int[][] dp) {
    if(i==0 && j==0) return 1;
    if(i<0 || j<0) return 0;
    if(dp[i][j] != -1) return dp[i][j];
    
    int up = help(i-1, j, dp);
    int left = help(i, j-1, dp);
    return dp[i][j] = up + left;
}
```

✅ **Time Complexity**: O(m × n)
✅ **Space Complexity**: O(m × n) + recursion stack

---

# **✅ Tabulation (Bottom-Up DP)**

👉 *Use iterative DP to build up answer from small subproblems*

**State**: `dp[i][j]` = number of ways to reach `(i,j)`
**Base Initialization**:

* `dp[0][j] = 1` for all `j` (first row)
* `dp[i][0] = 1` for all `i` (first column)

**Transition**:

$$
dp[i][j] = dp[i-1][j] + dp[i][j-1]
$$

**Java Tabulation Skeleton:**

```java
public int numberOfPaths(int m, int n) {
    int[][] dp = new int[m][n];
    for(int i=0;i<m;i++) dp[i][0] = 1;
    for(int j=0;j<n;j++) dp[0][j] = 1;
    
    for(int i=1;i<m;i++) {
        for(int j=1;j<n;j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
    }
    return dp[m-1][n-1];
}
```

✅ **Time Complexity**: O(m × n)
✅ **Space Complexity**: O(m × n)

---

# **✅ Space Optimized**

Since you only ever need the previous row, you can compress:

```java
public int numberOfPaths(int m, int n) {
    int[] prev = new int[n];
    Arrays.fill(prev, 1);
    
    for(int i=1;i<m;i++) {
        int[] curr = new int[n];
        curr[0] = 1;
        for(int j=1;j<n;j++) {
            curr[j] = prev[j] + curr[j-1];
        }
        prev = curr;
    }
    return prev[n-1];
}
```

✅ **Time Complexity**: O(m × n)
✅ **Space Complexity**: O(n)

---

# **🚀 Key Takeaway**

✅ Paths to `(i,j)` come from above and left
✅ Base case is top-left = 1
✅ Use memoization to avoid recomputation in recursion
✅ Use tabulation to fill a table iteratively
✅ Space optimize by only storing one row at a time

---

**Save this note** — and you’ll instantly revise everything next time you see a **Unique Paths** question! 🌟
