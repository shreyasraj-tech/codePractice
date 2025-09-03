---
# [Leetcode-746: Min Cost Climbing Stairs Leetcode](https://leetcode.com/problems/min-cost-climbing-stairs/)

---

### 📝 Problem Description

You are given an integer array `cost` where `cost[i]` is the cost of stepping on the `i-th` stair.  
Once you pay the cost, you can climb **one or two steps**.  
You can start from **step 0 or step 1**.  

Return the **minimum cost to reach the top of the floor**.

---

### Constraints
- `2 <= cost.length <= 1000`
- `0 <= cost[i] <= 999`

---

## ✅ Approach 1: Simple Recursion

We solve it like Fibonacci, but instead of counting ways, we minimize cost.

- To reach `i`, you can come from:
  - `i-1` (cost = `f(i-1) + cost[i-1]`)
  - `i-2` (cost = `f(i-2) + cost[i-2]`)
- Pick the minimum.

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        return Math.min(helper(n - 1, cost), helper(n - 2, cost));
    }

    private int helper(int i, int[] cost) {
        if (i < 0) return 0;
        if (i == 0 || i == 1) return cost[i];
        return cost[i] + Math.min(helper(i - 1, cost), helper(i - 2, cost));
    }
}
````

⏱ Time Complexity: `O(2^n)`
📦 Space Complexity: `O(n)` (recursion stack)

---

## ✅ Approach 2: Recursion + Memoization (Top-Down DP)

We cache results in a `dp` array to avoid recomputation.

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return Math.min(helper(n - 1, cost, dp), helper(n - 2, cost, dp));
    }

    private int helper(int i, int[] cost, int[] dp) {
        if (i < 0) return 0;
        if (i == 0 || i == 1) return cost[i];
        if (dp[i] != -1) return dp[i];

        dp[i] = cost[i] + Math.min(helper(i - 1, cost, dp), helper(i - 2, cost, dp));
        return dp[i];
    }
}
```

⏱ Time Complexity: `O(n)`
📦 Space Complexity: `O(n)`

---

## ✅ Approach 3: Tabulation (Bottom-Up DP)

We build `dp[]` iteratively.

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                             dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
```

---

### 📊 Example Dry Run (cost = \[10, 15, 20])

| `i` | Formula                               | `dp[i]` |
| --- | ------------------------------------- | ------- |
| 2   | min(dp\[1]+cost\[1], dp\[0]+cost\[0]) | 10      |
| 3   | min(dp\[2]+cost\[2], dp\[1]+cost\[1]) | 15      |

👉 `dp = [0,0,10,15]` → Answer = 15

---

## ✅ Approach 4: Space Optimization

We only need the last two states.

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int prev2 = 0, prev1 = 0;

        for (int i = 2; i <= n; i++) {
            int curr = Math.min(prev1 + cost[i - 1],
                                prev2 + cost[i - 2]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

⏱ Time Complexity: `O(n)`
📦 Space Complexity: `O(1)`

---

### 📌 Infographic-style Flow

```
Step i → Can come from i-1 or i-2

dp[i] = min(
    dp[i-1] + cost[i-1],   ← move 1 step
    dp[i-2] + cost[i-2]    ← move 2 steps
)
```


---

### 📌 Problem Goal:
Reach the "top floor", which is **just after the last stair** (i.e., `dp[10]`), and we can start from step 0 or step 1. So:

- `dp[0] = 0`
- `dp[1] = 0`

---

### 🧠 Initialize:
```
cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
n = cost.length = 10
dp size = n + 1 = 11 (to include the top)
```

---

### 🔄 Step-by-step table:

| `i` | `dp[i]` computation | Value | Explanation |
|----|----------------------|-------|-------------|
| 2  | min(dp[1]+cost[1], dp[0]+cost[0]) | min(0+100, 0+1) = 1 | Step 2 from step 0 is cheaper |
| 3  | min(dp[2]+cost[2], dp[1]+cost[1]) | min(1+1, 0+100) = 2 | Step 3 from step 2 is cheaper |
| 4  | min(dp[3]+cost[3], dp[2]+cost[2]) | min(2+1, 1+1) = 2 | Step 4 from step 2 is better |
| 5  | min(dp[4]+cost[4], dp[3]+cost[3]) | min(2+1, 2+1) = 3 | Both are equal, choose any |
| 6  | min(dp[5]+cost[5], dp[4]+cost[4]) | min(3+100, 2+1) = 3 | Step 6 from step 4 is better |
| 7  | min(dp[6]+cost[6], dp[5]+cost[5]) | min(3+1, 3+100) = 4 | Step 7 from step 6 |
| 8  | min(dp[7]+cost[7], dp[6]+cost[6]) | min(4+1, 3+1) = 4 | Step 8 from step 6 is cheaper |
| 9  | min(dp[8]+cost[8], dp[7]+cost[7]) | min(4+100, 4+1) = 5 | Step 9 from step 7 |
|10  | min(dp[9]+cost[9], dp[8]+cost[8]) | min(5+1, 4+100) = 6 | Step 10 from step 9 |

---

### ✅ Final Result:

`dp[10] = 6` → **Minimum cost to reach the top**

---

---

### ✅ Final Summary

| Approach                     | Time Complexity | Space Complexity |
| ---------------------------- | --------------- | ---------------- |
| Simple Recursion             | O(2^n)          | O(n)             |
| Recursion + Memoization      | O(n)            | O(n)             |
| Tabulation (Bottom-Up DP)    | O(n)            | O(n)             |
| Space Optimization (Optimal) | O(n)            | O(1)             |

👉 **Best Choice:** **Approach 4 (Space Optimization)** ✅
