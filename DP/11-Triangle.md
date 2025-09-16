# **📌 Triangle – Minimum Path Sum (LeetCode 120)**

### ✅ **Problem Link**: [Triangle - LeetCode](https://leetcode.com/problems/triangle/)

---

### **Problem Reminder**
- You’re given a **triangle array**.
- Start at the **top** and move to **adjacent numbers** on the row below.
- Find the **minimum path sum from top to bottom**.
-

---


### Example (from LeetCode)

```
triangle = [         triangle = [
     [2],             [2],
    [3, 4],           [3, 4],
   [6, 5, 7],         [6, 5, 7],
  [4, 1, 8, 3]        [4, 1, 8, 3],
]                     ]
```

---

### Step 1: Base Case (last row is copied into dp)

```
dp (row 3) = [4, 1, 8, 3]
```

---

### Step 2: Fill row 2 (from bottom up)

Formula:

$$
dp[i][j] = triangle[i][j] + \min(dp[i+1][j], dp[i+1][j+1])
$$

* `dp[2][0] = 6 + min(4, 1) = 6 + 1 = 7`
* `dp[2][1] = 5 + min(1, 8) = 5 + 1 = 6`
* `dp[2][2] = 7 + min(8, 3) = 7 + 3 = 10`

```
dp (row 2) = [7, 6, 10]
```

---

### Step 3: Fill row 1

* `dp[1][0] = 3 + min(7, 6) = 3 + 6 = 9`
* `dp[1][1] = 4 + min(6, 10) = 4 + 6 = 10`

```
dp (row 1) = [9, 10]
```

---

### Step 4: Fill row 0 (top)

* `dp[0][0] = 2 + min(9, 10) = 2 + 9 = 11`

```
dp (row 0) = [11]
```

---

### Final Answer

$$
\text{Minimum Path Sum} = 11
$$

---

### Visualization (Bottom → Top)

Think of DP as collapsing the triangle upward:

```
Row 3 (base)   : [ 4,   1,   8,   3 ]
Row 2          : [ 7,   6,  10 ]
Row 1          : [ 9,  10 ]
Row 0 (answer) : [ 11 ]
```

So, the values bubble up row by row, each time picking the **minimum of the two possible paths below**.

---

# **✅ 1. Memoization (Top-Down DP)**

**State:**
`dp[i][j]` = minimum path sum from `(i,j)` to bottom.

**Recurrence:**

```

dp\[i]\[j] = triangle\[i]\[j] + min(dp\[i+1]\[j], dp\[i+1]\[j+1])

````

**Base Case:**
- If at last row → `dp[i][j] = triangle[i][j]`

---

### **Java Code (Memoization)**

```java
import java.util.*;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helper(0, 0, triangle, dp);
    }

    private int helper(int i, int j, List<List<Integer>> triangle, int[][] dp) {
        int n = triangle.size();
        if (i == n-1) return triangle.get(i).get(j);
        if (dp[i][j] != -1) return dp[i][j];

        int down = helper(i+1, j, triangle, dp);
        int diagonal = helper(i+1, j+1, triangle, dp);

        return dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
    }
}
````

✅ **Time Complexity:** `O(n²)`
✅ **Space Complexity:** `O(n²)` + recursion stack

---

# **✅ 2. Tabulation (Bottom-Up DP)**

👉 Fill the DP table from the **last row up**.

### **Steps with Example**

**Base Case (last row):**

```
dp[3] = [4, 1, 8, 3]
```

**Row 2:**

* `dp[2][0] = 6 + min(4, 1) = 7`
* `dp[2][1] = 5 + min(1, 8) = 6`
* `dp[2][2] = 7 + min(8, 3) = 10`

```
dp[2] = [7, 6, 10]
```

**Row 1:**

* `dp[1][0] = 3 + min(7, 6) = 9`
* `dp[1][1] = 4 + min(6, 10) = 10`

```
dp[1] = [9, 10]
```

**Row 0:**

* `dp[0][0] = 2 + min(9, 10) = 11`

```
dp[0] = [11]
```

✅ Final Answer = **11**

---

### **Java Code (Tabulation)**

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // Base case: last row
        for (int j = 0; j < n; j++) {
            dp[n-1][j] = triangle.get(n-1).get(j);
        }

        // Bottom-up DP
        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int down = dp[i+1][j];
                int diagonal = dp[i+1][j+1];
                dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
        }
        return dp[0][0];
    }
}
```

✅ **Time Complexity:** `O(n²)`
✅ **Space Complexity:** `O(n²)`

---

# **✅ 3. Space Optimized DP**

👉 We only need the **next row** at any point.
Use **1D arrays**: `front[]` (next row) and `curr[]` (current row).

---

### **Java Code (Space Optimization)**

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] front = new int[n];

        // Initialize last row
        for (int j = 0; j < n; j++) {
            front[j] = triangle.get(n-1).get(j);
        }

        // Bottom-up calculation
        for (int i = n-2; i >= 0; i--) {
            int[] curr = new int[n];
            for (int j = 0; j <= i; j++) {
                int down = front[j];
                int diagonal = front[j+1];
                curr[j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
            front = curr; // move current row up
        }
        return front[0];
    }
}
```

✅ **Time Complexity:** `O(n²)`
✅ **Space Complexity:** `O(n)`

---

# **🚀 DP Triangle Visualization**

```
Row 3 (base)   : [ 4,   1,   8,   3 ]
Row 2          : [ 7,   6,  10 ]
Row 1          : [ 9,  10 ]
Row 0 (answer) : [ 11 ]
```

So the values bubble up row by row, each time taking the **minimum of two adjacent paths**.

---

# **📌 Key Takeaways**

* Always copy **last row** as base case.
* Transition:
  `dp[i][j] = triangle[i][j] + min(dp[i+1][j], dp[i+1][j+1])`
* **Memoization** → recursion + cache
* **Tabulation** → bottom-up
* **Space Optimization** → 1D arrays

---

```

---

Do you want me to also create a **side-by-side visualization** (triangle + dp values overlay) like:  

```

```
[2]
```

\[3, 4]
\[7, 6, 10]
\[4, 1, 8, 3]

```

👉 This makes it even clearer how DP values replace triangle values row by row. Would you like me to add that?
```
