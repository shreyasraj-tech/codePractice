
# 🍒 Cherry Pickup[-LeetCode741](https://leetcode.com/problems/cherry-pickup/description/)

### 📘 Problem

You are given an **n × n grid** where each cell has:
- `0` → empty cell  
- `1` → contains a cherry  
- `-1` → thorn (cannot be crossed)

Two people start at `(0,0)` and move **to (n−1, n−1)** by only moving **right** or **down**.  
Both pick cherries along their paths, and **a cell’s cherry can only be picked once** (if both visit the same cell, count it once).

Your task:  
Return **maximum cherries both can collect** after going from `(0,0)` → `(n−1,n−1)` and back.

---

### 🧩 Concept Simplification

Instead of thinking of *two separate trips* (forward + backward),  
we imagine **two people starting from (0,0)** moving **simultaneously** to `(n−1, n−1)`.

✅ Why?  
Because both paths have the same total number of steps (`2n - 2`).  
Each can move only **right (r)** or **down (d)** at every step.

At any moment `t`, both persons have taken `t` moves, so:
```
x1 + y1 = t
x2 + y2 = t
```
Hence we only need **3 coordinates** to represent a state: `(x1, y1, x2)`  
since `y2` can be derived as `y2 = x1 + y1 - x2`.

---

# **0️⃣ Recursion (Brute Force)**

---

### 🎯 Intuition

At each step, both players can move:
- `→` (right)
- `↓` (down)

Thus 4 combinations:
1. (right, right)
2. (right, down)
3. (down, right)
4. (down, down)

We recursively explore all, ensuring:
- We **don’t go out of bounds**
- We **avoid thorns (-1)**
- We **add cherries only once** if both are on same cell

---

### 🧠 Recursive Relation

Let `f(x1, y1, x2)` = **maximum cherries collected**  
when person A is at `(x1, y1)` and person B is at `(x2, y2 = x1 + y1 - x2)`.

Base case:
```
If any out of bounds or thorn -> return -∞
If both reach (n−1, n−1) -> return grid[n−1][n−1]
```

Recursive transitions:

```
cherries = grid[x1][y1]
if (x1 != x2) cherries += grid[x2][y2]

Next move = max(
    f(x1+1, y1, x2+1),   // both down
    f(x1, y1+1, x2),     // A right, B right
    f(x1+1, y1, x2),     // A down, B right
    f(x1, y1+1, x2+1)    // A right, B down
)

return cherries + next move
```

---

### 🧩 Visualization (Recursive Traversal)

Imagine both move step by step:

```
Start: (0,0) & (0,0)
  ↓
After 1 move: (1,0) & (0,1)
  ↓
After 2 moves: (1,1) & (1,1)
```

At each “layer” of movement (sum of coordinates = constant),  
they collect cherries and proceed forward.

---

### ⚠️ Issue

Exponential Time — because same states `(x1, y1, x2)` are recomputed multiple times.  
Hence we use **memoization** next.

---

# **1️⃣ Memoization (Top-Down DP)**

---

### 💡 State Definition

`dp[x1][y1][x2]` → max cherries when:
- Player 1 at `(x1, y1)`
- Player 2 at `(x2, y2 = x1 + y1 - x2)`

We only need **3D DP**, not 4D, because `y2` is **dependent** on others.

If we used 4D (`x1, y1, x2, y2`),  
then `y1 + x1` and `y2 + x2` would represent same step `t`,  
making 4D redundant and exponentially larger in memory.

So, **3D DP** = Optimal & sufficient.

---

### 🧱 Example Grid

```
grid =
[
 [0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]
]
```

### 🔍 Visualization of DP Layers

Each layer `t` = x1 + y1 = x2 + y2  
represents simultaneous positions of both players:

```
t = 0 : [(0,0), (0,0)]
t = 1 : [(1,0),(0,1)]
t = 2 : [(1,1),(1,1)] and others
```

At each layer, we take maximum cherries and continue expanding.

---

### ✅ Java (Memoization Code)

```java
class Solution {
    int[][][] dp;
    int n;

    public int cherryPickup(int[][] grid) {
        n = grid.length;
        dp = new int[n][n][n];
        for (int[][] mat : dp)
            for (int[] row : mat)
                Arrays.fill(row, Integer.MIN_VALUE);

        return Math.max(0, helper(0, 0, 0, grid));
    }

    private int helper(int x1, int y1, int x2, int[][] grid) {
        int y2 = x1 + y1 - x2;
        if (x1 >= n || y1 >= n || x2 >= n || y2 >= n ||
            grid[x1][y1] == -1 || grid[x2][y2] == -1)
            return Integer.MIN_VALUE;

        if (x1 == n-1 && y1 == n-1) return grid[x1][y1];
        if (dp[x1][y1][x2] != Integer.MIN_VALUE) return dp[x1][y1][x2];

        int cherries = grid[x1][y1];
        if (x1 != x2) cherries += grid[x2][y2];

        int next = Math.max(
            Math.max(helper(x1+1, y1, x2+1, grid),
                     helper(x1, y1+1, x2, grid)),
            Math.max(helper(x1+1, y1, x2, grid),
                     helper(x1, y1+1, x2+1, grid))
        );

        cherries += next;
        return dp[x1][y1][x2] = cherries;
    }
}
```

✅ **Time Complexity**: `O(n³)`  
✅ **Space Complexity**: `O(n³)`

---

# **2️⃣ Tabulation (Bottom-Up DP)**

---

### 🧩 Idea

We reverse the recursion:
- Move from bottom-right `(n−1,n−1)` → `(0,0)`
- Compute layer by layer from the end to the start.

Each state `(x1, y1, x2)` depends on next moves (down/right combos).

---

### 📊 DP Definition

`dp[x1][y1][x2]` = max cherries from `(x1,y1)` and `(x2,y2)` to end.

We fill `dp` bottom-up, avoiding invalid paths.

---

### 💡 Transition

```
for x1, y1, x2 from n-1 to 0:
    y2 = x1 + y1 - x2
    if invalid/thorn -> skip

    cherries = grid[x1][y1]
    if (x1 != x2) cherries += grid[x2][y2]

    bestNext = max(
        dp[x1+1][y1][x2+1],
        dp[x1][y1+1][x2],
        dp[x1+1][y1][x2],
        dp[x1][y1+1][x2+1]
    )

    dp[x1][y1][x2] = cherries + bestNext
```

---

### 🔢 Visualization Example

For a 3×3 grid:

```
dp layers (x1, x2)

Layer t = 4 (bottom):
dp[2][2][2] = grid[2][2] = 1

Layer t = 3:
dp[2][1][2], dp[1][2][2], ...
build upward using bottom layer
```

We collapse the cube layer by layer from end → start.

---

### ✅ Tabulation Code (Java)

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n][n][n];

        for (int[][] mat : dp)
            for (int[] row : mat)
                Arrays.fill(row, Integer.MIN_VALUE);

        dp[n-1][n-1][n-1] = grid[n-1][n-1];

        for (int x1 = n-1; x1 >= 0; x1--) {
            for (int y1 = n-1; y1 >= 0; y1--) {
                for (int x2 = n-1; x2 >= 0; x2--) {
                    int y2 = x1 + y1 - x2;
                    if (y2 < 0 || y2 >= n) continue;
                    if (grid[x1][y1] == -1 || grid[x2][y2] == -1) continue;

                    int cherries = grid[x1][y1];
                    if (x1 != x2) cherries += grid[x2][y2];

                    if (x1 < n-1 && x2 < n-1)
                        cherries += Math.max(dp[x1+1][y1][x2+1], dp[x1][y1][x2]);
                    dp[x1][y1][x2] = Math.max(dp[x1][y1][x2], cherries);
                }
            }
        }
        return Math.max(0, dp[0][0][0]);
    }
}
```

✅ **Time Complexity:** `O(n³)`  
✅ **Space Complexity:** `O(n³)`

---

# **3️⃣ Space Optimization**

---

### ⚙️ Observation

At any point, we only need **next layer (t+1)** of states to compute **current layer (t)**.

Hence we can reduce 3D → 2D.

---

### 💡 Optimization Logic

Use two 2D matrices:
- `curr[n][n]`
- `next[n][n]`

At each iteration (decreasing t), update `curr` based on `next`.

```
curr[x1][x2] = max(
    next[x1+1][x2+1],
    next[x1+1][x2],
    next[x1][x2+1],
    next[x1][x2]
)
```

Add cherries accordingly and swap matrices.

---

### ✅ Optimized Java Code

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][] next = new int[n][n];

        for (int t = 2 * (n - 1); t >= 0; t--) {
            int[][] curr = new int[n][n];
            for (int[] row : curr) Arrays.fill(row, Integer.MIN_VALUE);

            for (int x1 = Math.max(0, t - (n - 1)); x1 <= Math.min(n - 1, t); x1++) {
                for (int x2 = Math.max(0, t - (n - 1)); x2 <= Math.min(n - 1, t); x2++) {
                    int y1 = t - x1, y2 = t - x2;
                    if (y1 >= n || y2 >= n || grid[x1][y1] == -1 || grid[x2][y2] == -1)
                        continue;

                    int cherries = grid[x1][y1];
                    if (x1 != x2) cherries += grid[x2][y2];

                    int bestNext = 0;
                    for (int dx1 : new int[]{0,1}) {
                        for (int dx2 : new int[]{0,1}) {
                            int val = (x1+dx1<n && x2+dx2<n && next[x1+dx1][x2+dx2]!=Integer.MIN_VALUE)
                                      ? next[x1+dx1][x2+dx2] : Integer.MIN_VALUE;
                            bestNext = Math.max(bestNext, val);
                        }
                    }
                    curr[x1][x2] = (bestNext == Integer.MIN_VALUE) ? bestNext : cherries + bestNext;
                }
            }
            next = curr;
        }
        return Math.max(0, next[0][0]);
    }
}
```

✅ **Time Complexity:** `O(n³)`  
✅ **Space Complexity:** `O(n²)`

---

# 🚀 Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|--------|
| Recursion | Try all 4 moves recursively | Exponential | O(1) |
| Memoization | Top-down + 3D DP | O(n³) | O(n³) |
| Tabulation | Bottom-up | O(n³) | O(n³) |
| Space Optimized | Layer compression | O(n³) | O(n²) |

---

# 🎯 Why 3D DP, Not 4D?

* Both players take same number of steps `t`
* Hence, `y2` can be **derived** from `(x1, y1, x2)` as `y2 = x1 + y1 - x2`
* Thus, 4D → redundant and increases complexity `O(n⁴)` unnecessarily  
✅ 3D gives full information compactly.

---

✨ **Final Answer**: This structured approach — from recursion to space-optimized DP —  
illustrates how 3D DP efficiently models simultaneous movements in grid problems like **Cherry Pickup**.

