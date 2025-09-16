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


###  **Maximum Path Sum in Matrix visualization**

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

### Step 1: Base case (last row = same as matrix last row)

```
dp[3] = [1, 0, 2, 20, 0, 4]
```

---

### Step 2: Fill row 2 (using dp\[3])

Formula:

$$
dp[2][j] = mat[2][j] + \max(dp[3][j-1],\ dp[3][j],\ dp[3][j+1])
$$

* `dp[2][0] = 0 + max(1, 0) = 1`
* `dp[2][1] = 10 + max(1, 0, 2) = 10 + 2 = 12`
* `dp[2][2] = 4 + max(0, 2, 20) = 4 + 20 = 24`
* `dp[2][3] = 0 + max(2, 20, 0) = 20`
* `dp[2][4] = 2 + max(20, 0, 4) = 2 + 20 = 22`
* `dp[2][5] = 0 + max(0, 4) = 4`

✅ Row 2 becomes:

```
dp[2] = [1, 12, 24, 20, 22, 4]
```

---

### Step 3: Fill row 1 (using dp\[2])

* `dp[1][0] = 1 + max(1, 12) = 13`
* `dp[1][1] = 0 + max(1, 12, 24) = 24`
* `dp[1][2] = 0 + max(12, 24, 20) = 24`
* `dp[1][3] = 30 + max(24, 20, 22) = 30 + 24 = 54`
* `dp[1][4] = 2 + max(20, 22, 4) = 2 + 22 = 24`
* `dp[1][5] = 5 + max(22, 4) = 5 + 22 = 27`

✅ Row 1 becomes:

```
dp[1] = [13, 24, 24, 54, 24, 27]
```

---

### Step 4: Fill row 0 (using dp\[1])

* `dp[0][0] = 10 + max(13, 24) = 34`
* `dp[0][1] = 10 + max(13, 24, 24) = 34`
* `dp[0][2] = 2 + max(24, 24, 54) = 56`
* `dp[0][3] = 0 + max(24, 54, 24) = 54`
* `dp[0][4] = 20 + max(54, 24, 27) = 74`
* `dp[0][5] = 4 + max(24, 27) = 31`

✅ Row 0 becomes:

```
dp[0] = [34, 34, 56, 54, 74, 31]
```

---

### Final Answer

Take max from top row:

$$
\text{Max Path Sum} = 74
$$

---

### Full DP Table (Bottom → Top)

```
Row 0 (top)    : [34, 34, 56, 54, 74, 31]
Row 1          : [13, 24, 24, 54, 24, 27]
Row 2          : [ 1, 12, 24, 20, 22,  4]
Row 3 (bottom) : [ 1,  0,  2, 20,  0,  4]
```

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

