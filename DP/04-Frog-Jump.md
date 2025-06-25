**Frog Jump** problem using **Dynamic Programming** in both:

[Frog Jump](https://www.geeksforgeeks.org/problems/geek-jump/1)


* ✅ **Memoization (Top-down)**
* ✅ **Tabulation (Bottom-up)**


---

## 🐸 Problem Statement Recap

Given an array `height[]` where `height[i]` represents the height of the `i-th` stair, the frog starts at the **0-th stair** and can:

* Jump to the next stair `i + 1`
* Or skip one stair and jump to `i + 2`

The **cost** of each jump is the **absolute difference** between the heights of the two stairs.

👉 Goal: Reach the last stair (`n - 1`) with **minimum total cost**.

---

## ✅ Method 1: **Memoization (Top-Down DP)**

### 📌 Code:

```java
class Solution {
    public int frogJump(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1); // Initialize memo table
        return minCost(n - 1, height, dp);
    }

    private int minCost(int i, int[] height, int[] dp) {
        if (i == 0) return 0;

        if (dp[i] != -1) return dp[i];

        int oneStep = minCost(i - 1, height, dp) + Math.abs(height[i] - height[i - 1]);

        int twoStep = Integer.MAX_VALUE;
        if (i > 1)
            twoStep = minCost(i - 2, height, dp) + Math.abs(height[i] - height[i - 2]);

        return dp[i] = Math.min(oneStep, twoStep);
    }
}
```

### ✅ Explanation:

* Start from the last stair and recursively find the min cost from previous steps.
* Use a `dp[]` array to cache results (memoization).
* Each subproblem is only solved once.

---

## ✅ Method 2: **Tabulation (Bottom-Up DP)**

### 📌 Code:

```java
class Solution {
    public int frogJump(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int oneStep = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
            int twoStep = Integer.MAX_VALUE;
            if (i > 1)
                twoStep = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
            dp[i] = Math.min(oneStep, twoStep);
        }

        return dp[n - 1];
    }
}
```

### ✅ Explanation:

* Build the solution from `0` to `n-1` iteratively.
* At each step, decide the cheaper path: 1-step or 2-step.
* Use a `dp[]` array to store the minimum cost up to each stair.

---

## 📒 Comparison and Notes

| Feature             | Memoization (Top-Down)                    | Tabulation (Bottom-Up)                      |
| ------------------- | ----------------------------------------- | ------------------------------------------- |
| Approach Type       | Recursive                                 | Iterative                                   |
| Function Calls      | Many recursive calls                      | No recursion                                |
| Time Complexity     | `O(n)`                                    | `O(n)`                                      |
| Space Complexity    | `O(n)` (stack + dp\[])                    | `O(n)` (only dp\[])                         |
| Stack Overflow Risk | Yes (if `n` is large, e.g., >10⁴ or 10⁵)  | No                                          |
| Code Readability    | Closer to problem definition (intuitive)  | Cleaner and usually faster                  |
| Performance         | Slightly slower due to recursion overhead | Slightly faster due to loop-based execution |

---

## 🏆 Which One is Better?

### ✅ Tabulation is **generally preferred** because:

* No recursion → no risk of **stack overflow**
* Slightly **faster** due to lower function call overhead
* Easier to optimize further (e.g., with **space optimization**)

---

## 💡 Bonus: Space-Optimized Tabulation (Only 2 variables)

```java
class Solution {
    public int frogJump(int[] height) {
        int n = height.length;
        int prev = 0, prev2 = 0;

        for (int i = 1; i < n; i++) {
            int oneStep = prev + Math.abs(height[i] - height[i - 1]);
            int twoStep = (i > 1) ? prev2 + Math.abs(height[i] - height[i - 2]) : Integer.MAX_VALUE;
            int curr = Math.min(oneStep, twoStep);

            prev2 = prev;
            prev = curr;
        }

        return prev;
    }
}
```

* Uses `O(1)` space instead of `O(n)`!

---

## ✅ Summary Notes

* **Memoization** is good for understanding and tracing the problem recursively.
* **Tabulation** is more efficient and better for large inputs.
* Prefer **tabulation with space optimization** in real-world performance-critical scenarios.

