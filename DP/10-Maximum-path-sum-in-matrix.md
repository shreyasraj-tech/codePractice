# **📌 Maximum Path Sum in Matrix (GFG)**

### ✅ **Problem Link**: [Maximum Path Sum in Matrix - GFG](https://www.geeksforgeeks.org/problems/path-in-matrix3805/1)
---

* Grid size: **m × n**
* Start from **any cell in the first row**
* Move only to the **down**, **down-left**, or **down-right**
* Goal: find the **maximum path sum** from top to bottom

---

# **✅ 1. Memoization (Top-Down DP)**

👉 *Recursive solution with caching*  

**State**:  
`dp[i][j]` = max path sum ending at cell `(i,j)`

**Base Case**:
- If `(j < 0 || j >= n)` → out of bounds → return `Integer.MIN_VALUE`
- If `(i == 0)` → return `mat[0][j]`

**Transition**:

$$
dp[i][j] = mat[i][j] + \max(dp[i-1][j], \ dp[i-1][j-1], \ dp[i-1][j+1])
$$

**Java Memoization Code:**

```java
import java.util.*;

class Solution {
    public int maximumPath(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        int ans = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.max(ans, helper(m-1, j, mat, dp));
        }
        return ans;
    }

    private int helper(int i, int j, int[][] mat, int[][] dp) {
        if (j < 0 || j >= mat[0].length) return Integer.MIN_VALUE;
        if (i == 0) return mat[0][j];
        if (dp[i][j] != -1) return dp[i][j];

        int up = helper(i-1, j, mat, dp);
        int leftDiag = helper(i-1, j-1, mat, dp);
        int rightDiag = helper(i-1, j+1, mat, dp);

        return dp[i][j] = mat[i][j] + Math.max(up, Math.max(leftDiag, rightDiag));
    }
}
````

⚠️ **Note**: This approach may cause **TLE** on large inputs because of recursion overhead.

✅ **Time Complexity**: `O(m × n)`
✅ **Space Complexity**: `O(m × n)` + recursion stack

---

# **✅ 2. Tabulation (Bottom-Up DP)**

👉 *Iterative filling row by row*

**Initialization**:

* First row is same as matrix first row

**Transition**:

$$
dp[i][j] = mat[i][j] + \max(dp[i-1][j], \ dp[i-1][j-1], \ dp[i-1][j+1])
$$

---

### 🔎 Example Dry Run

Matrix:

```
mat = [
   [10, 10,  2,  0, 20,  4],
   [ 1,  0,  0, 30,  2,  5],
   [ 0, 10,  4,  0,  2,  0],
   [ 1,  0,  2, 20,  0,  4]
]
```

---

**Step 1: Base (row 0)**

```
dp[0] = [10, 10, 2, 0, 20, 4]
```

---

**Step 2: Fill row 1**

```
dp[1] = [11, 10, 10, 50, 22, 25]
```

---

**Step 3: Fill row 2**

```
dp[2] = [10, 60, 54, 50, 52, 25]
```

---

**Step 4: Fill row 3**

```
dp[3] = [61, 62, 62, 74, 54, 56]
```

---

**Final Answer = max(dp\[last row]) = 74**

---

**Java Tabulation Code:**

```java
import java.util.*;

class Solution {
    public int maximumPath(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];

        // base case: first row
        for (int j = 0; j < n; j++) {
            dp[0][j] = mat[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int up = dp[i-1][j];
                int leftDiag = (j > 0) ? dp[i-1][j-1] : Integer.MIN_VALUE;
                int rightDiag = (j < n-1) ? dp[i-1][j+1] : Integer.MIN_VALUE;

                dp[i][j] = mat[i][j] + Math.max(up, Math.max(leftDiag, rightDiag));
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.max(ans, dp[m-1][j]);
        }
        return ans;
    }
}
```

✅ **Time Complexity**: `O(m × n)`
✅ **Space Complexity**: `O(m × n)`

---

# **✅ 3. Space Optimized DP**

👉 We only need the **previous row** at any time.

**Optimization**:

* Use **1D array `prev`** for previous row
* Build **`curr` row** using `prev`, then replace

---

**Java Space Optimized Code:**

```java
class Solution {
    public int maximumPath(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] prev = new int[n];

        // base case: first row
        for (int j = 0; j < n; j++) {
            prev[j] = mat[0][j];
        }

        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                int up = prev[j];
                int leftDiag = (j > 0) ? prev[j-1] : Integer.MIN_VALUE;
                int rightDiag = (j < n-1) ? prev[j+1] : Integer.MIN_VALUE;

                curr[j] = mat[i][j] + Math.max(up, Math.max(leftDiag, rightDiag));
            }
            prev = curr;
        }

        int ans = Integer.MIN_VALUE;
        for (int val : prev) ans = Math.max(ans, val);
        return ans;
    }
}
```

✅ **Time Complexity**: `O(m × n)`
✅ **Space Complexity**: `O(n)`

---

# **🚀 Key Takeaways**

* You can move **down**, **down-left**, or **down-right**
* **Memoization** may cause TLE for large inputs
* **Tabulation** is safe and efficient
* **Space Optimization** reduces memory to `O(n)`
* Final answer = **max of last row** in DP

---

✨ Save this note — covers all DP approaches for **Maximum Path Sum in Matrix (GFG)** ✨

```

