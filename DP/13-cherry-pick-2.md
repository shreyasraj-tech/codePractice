# 🍒 Cherry Pickup II [(LeetCode 1463)](https://leetcode.com/problems/cherry-pickup-ii/description/)
---

### 📘 Problem Reminder

You are given a **grid** of size `m × n`, where each cell contains some **cherries** (integer values).

Two robots start at:
- Robot 1 → `(0, 0)`
- Robot 2 → `(0, n−1)`

They both move **down one row** at each step.  
From position `(r, c)`, a robot can move to:
- `(r+1, c-1)` → down-left
- `(r+1, c)` → down
- `(r+1, c+1)` → down-right

They **collect cherries** from the cells they are on.
If both robots land on the **same cell**, the cherry is counted **only once**.

Your goal → **Maximize total cherries collected**.

---

### 🧩 Key Points

- Both robots always move **down one row per turn**, but can shift horizontally.  
- They move **simultaneously** until reaching the **last row**.
- Once both reach the bottom, return the **maximum total cherries**.

---

# **0️⃣ Recursion (Brute Force)**

---

### 🎯 Intuition

Each robot moves one row down at every step.  
So for each row `r`, both have positions:
```
Robot1 at (r, c1)
Robot2 at (r, c2)
```

At each step, we choose **3 possible moves** for each robot:
- Down-left `(r+1, c-1)`
- Down `(r+1, c)`
- Down-right `(r+1, c+1)`

Thus total `3 × 3 = 9` combinations.

We explore all paths recursively to maximize collected cherries.

---

### 🧠 Recursive State

Let  
`f(r, c1, c2)` = max cherries from row `r` to bottom  
if robot1 is at `(r, c1)` and robot2 at `(r, c2)`.

---

### 🧮 Base Cases

```
If any (c1 or c2) is out of bounds → return 0
If r == m−1 (last row):
    if c1 == c2 → return grid[r][c1]
    else → return grid[r][c1] + grid[r][c2]
```

---

### 🔁 Transition (Recurrence)

For each row:
- Both robots move to the next row with one of three moves each.  
- We compute all combinations and take the **maximum**.

```
maxCherries = 0
for dc1 in [-1, 0, 1]:
    for dc2 in [-1, 0, 1]:
        maxCherries = max(maxCherries, f(r+1, c1+dc1, c2+dc2))
```

Then add cherries of current positions:

```
if (c1 == c2):
    cherries = grid[r][c1]
else:
    cherries = grid[r][c1] + grid[r][c2]
```

Final recursive formula:

```
f(r, c1, c2) = cherries + maxCherries
```

---

### ⚠️ Problem with Pure Recursion

Exponential time due to 9 branching possibilities per step →  
`O(3^(2m))`, which is infeasible for `m ≤ 70`.

---

# **1️⃣ Memoization (Top-Down DP)**

---

### 💡 Idea

Store overlapping results in `dp[r][c1][c2]`.

**State Meaning**:
> Maximum cherries from row `r` to bottom if  
> robot1 at column `c1`, robot2 at column `c2`.

---

### 🧮 Recursive + Memoization Formula

```
f(r, c1, c2):
    if out of bounds -> return 0
    if r == m-1:
        return (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2]

    if dp[r][c1][c2] != -1:
        return dp[r][c1][c2]

    maxCherries = 0
    for dc1 in [-1,0,1]:
        for dc2 in [-1,0,1]:
            maxCherries = max(maxCherries, f(r+1, c1+dc1, c2+dc2))

    cherries = (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2]
    dp[r][c1][c2] = cherries + maxCherries
    return dp[r][c1][c2]
```

---

### 🧱 Visualization Example

For example grid:

```
[
 [3, 1, 1],
 [2, 5, 1],
 [1, 5, 5],
 [2, 1, 1]
]
```

Row 0:
```
Robot1 → col 0
Robot2 → col 2
```

Row 1 possibilities:
```
Robot1: 0,1
Robot2: 1,2
```

At each row, both explore 9 possibilities.

---

### ✅ Java Code (Memoization)

```java
class Solution {
    int[][][] dp;
    int m, n;

    public int cherryPickup(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n][n];
        for (int[][] mat : dp)
            for (int[] row : mat)
                Arrays.fill(row, -1);

        return helper(0, 0, n - 1, grid);
    }

    private int helper(int r, int c1, int c2, int[][] grid) {
        if (c1 < 0 || c2 < 0 || c1 >= n || c2 >= n) return 0;

        if (r == m - 1) {
            return (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];
        }

        if (dp[r][c1][c2] != -1) return dp[r][c1][c2];

        int maxCherries = 0;
        for (int dc1 = -1; dc1 <= 1; dc1++) {
            for (int dc2 = -1; dc2 <= 1; dc2++) {
                maxCherries = Math.max(maxCherries,
                        helper(r + 1, c1 + dc1, c2 + dc2, grid));
            }
        }

        int cherries = (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];
        return dp[r][c1][c2] = cherries + maxCherries;
    }
}
```

✅ **Time Complexity:** `O(m × n² × 9)` ≈ `O(m × n²)`  
✅ **Space Complexity:** `O(m × n²)` + recursion stack

---

# **2️⃣ Tabulation (Bottom-Up DP)**

---

### 💡 Idea

We convert recursive approach into iterative bottom-up DP.

We’ll compute from **last row → first row**.

---

### 🧩 State Definition

`dp[r][c1][c2]` = max cherries collected from `(r,c1)` and `(r,c2)` till bottom.

---

### 🧮 Initialization

For base case (last row `r = m−1`):

```
if (c1 == c2)
    dp[m−1][c1][c2] = grid[m−1][c1]
else
    dp[m−1][c1][c2] = grid[m−1][c1] + grid[m−1][c2]
```

---

### 🔁 Transition

For each row `r` from `m−2 → 0`:

```
for c1 in [0,n)
  for c2 in [0,n)
    maxCherries = 0
    for dc1 in [-1,0,1]:
        for dc2 in [-1,0,1]:
            if c1+dc1 and c2+dc2 are in bounds:
                maxCherries = max(maxCherries, dp[r+1][c1+dc1][c2+dc2])

    cherries = (c1==c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2]
    dp[r][c1][c2] = cherries + maxCherries
```

---

### 🧱 Visualization of DP Layers

Think of the DP as a **3D matrix [row][col1][col2]**:

Each “layer” = one row of the grid.

```
Row 3 (base): direct cherries in last row
Row 2: built from Row 3
Row 1: built from Row 2
Row 0: built from Row 1
```

At the end, our answer is at `dp[0][0][n-1]`.

---

### ✅ Java Code (Tabulation)

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n][n];

        // Base case: last row
        for (int c1 = 0; c1 < n; c1++) {
            for (int c2 = 0; c2 < n; c2++) {
                dp[m-1][c1][c2] = (c1 == c2)
                        ? grid[m-1][c1]
                        : grid[m-1][c1] + grid[m-1][c2];
            }
        }

        // Fill DP bottom-up
        for (int r = m - 2; r >= 0; r--) {
            for (int c1 = 0; c1 < n; c1++) {
                for (int c2 = 0; c2 < n; c2++) {
                    int maxCherries = 0;
                    for (int dc1 = -1; dc1 <= 1; dc1++) {
                        for (int dc2 = -1; dc2 <= 1; dc2++) {
                            int nc1 = c1 + dc1, nc2 = c2 + dc2;
                            if (nc1 >= 0 && nc2 >= 0 && nc1 < n && nc2 < n) {
                                maxCherries = Math.max(maxCherries,
                                        dp[r + 1][nc1][nc2]);
                            }
                        }
                    }
                    int cherries = (c1 == c2)
                            ? grid[r][c1]
                            : grid[r][c1] + grid[r][c2];
                    dp[r][c1][c2] = cherries + maxCherries;
                }
            }
        }

        return dp[0][0][n-1];
    }
}
```

✅ **Time Complexity:** `O(m × n² × 9)` ≈ `O(m × n²)`  
✅ **Space Complexity:** `O(m × n²)`

---

# **3️⃣ Space Optimization**

---

### ⚙️ Observation

At any point, we only depend on the **next row (r+1)**.  
So we can reduce 3D DP → 2D DP for current and next row.

---

### 💡 Space Optimization Logic

Use:
```
int[][] front = new int[n][n]; // row r+1
int[][] curr  = new int[n][n]; // current row
```

We iterate bottom-up, updating `curr` from `front`.

---

### ✅ Java Code (Space Optimized)

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] front = new int[n][n];

        // Base case: last row
        for (int c1 = 0; c1 < n; c1++) {
            for (int c2 = 0; c2 < n; c2++) {
                front[c1][c2] = (c1 == c2)
                        ? grid[m-1][c1]
                        : grid[m-1][c1] + grid[m-1][c2];
            }
        }

        // Iterate upwards
        for (int r = m - 2; r >= 0; r--) {
            int[][] curr = new int[n][n];
            for (int c1 = 0; c1 < n; c1++) {
                for (int c2 = 0; c2 < n; c2++) {
                    int maxCherries = 0;
                    for (int dc1 = -1; dc1 <= 1; dc1++) {
                        for (int dc2 = -1; dc2 <= 1; dc2++) {
                            int nc1 = c1 + dc1, nc2 = c2 + dc2;
                            if (nc1 >= 0 && nc2 >= 0 && nc1 < n && nc2 < n)
                                maxCherries = Math.max(maxCherries, front[nc1][nc2]);
                        }
                    }
                    int cherries = (c1 == c2)
                            ? grid[r][c1]
                            : grid[r][c1] + grid[r][c2];
                    curr[c1][c2] = cherries + maxCherries;
                }
            }
            front = curr;
        }

        return front[0][n-1];
    }
}
```

✅ **Time Complexity:** `O(m × n² × 9)`  
✅ **Space Complexity:** `O(n²)`

---

# 🚀 Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|--------|
| Recursion | Explore all 9 possibilities | Exponential | O(1) |
| Memoization | Cache results in 3D DP | O(m × n²) | O(m × n²) |
| Tabulation | Bottom-up computation | O(m × n²) | O(m × n²) |
| Space Optimized | Store only 2 layers | O(m × n²) | O(n²) |

---

# 🎯 Key Takeaways

- Use **3D DP** because both robots share the same row index `r`.  
- Each robot’s column varies, so 2 column indices form DP dimensions.  
- Each cell in DP represents **maximum cherries** collectable from that configuration.
- **Space optimization** possible since each layer only depends on the one below.

---


