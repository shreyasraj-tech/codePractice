---
# [Leetcode-70: Climbing Stairs Leetcode](https://leetcode.com/problems/climbing-stairs/)

---

### Problem Description

You are climbing a staircase. It takes `n` steps to reach the top.  

Each time you can either climb **1 or 2 steps**.  
In how many distinct ways can you climb to the top?

---

### Constraints

- `1 <= n <= 45`

---

### Example 1:

- **Input**: `n = 2`  
- **Output**: `2`  
- **Explanation**: There are two ways to climb to the top:
  1. 1 step + 1 step  
  2. 2 steps  

---

### Example 2:

- **Input**: `n = 3`  
- **Output**: `3`  
- **Explanation**: There are three ways to climb to the top:
  1. 1 step + 1 step + 1 step  
  2. 1 step + 2 steps  
  3. 2 steps + 1 step  

---

## ✅ Approach 1: Simple Recursion

We try to climb from step `n` by breaking it into smaller subproblems:

- Either take `1 step` → solve `f(n-1)`  
- Or take `2 steps` → solve `f(n-2)`  

Thus:  
`f(n) = f(n-1) + f(n-2)`

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n; // Base cases
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
````

🔎 **Explanation**:

* If `n == 1`, only 1 way.
* If `n == 2`, two ways: (1+1) or (2).
* Otherwise, recursively compute by breaking into smaller steps.

⚠️ **Time Complexity**: O(2^n) (exponential)
⚠️ **Space Complexity**: O(n) (recursion stack)

---

## ✅ Approach 2: Recursion + Memoization (Top-Down DP)

We use recursion but store results of already computed subproblems in a `dp[]` array.

```java
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return helper(n, dp);
    }

    private int helper(int n, int[] dp) {
        if (n <= 2) return n;
        if (dp[n] != -1) return dp[n]; // Already computed
        dp[n] = helper(n - 1, dp) + helper(n - 2, dp);
        return dp[n];
    }
}
```

🔎 **Explanation**:

* Base cases remain the same.
* Cache intermediate results in `dp[]`.
* Prevents recalculating overlapping subproblems.

⏱ **Time Complexity**: O(n)
📦 **Space Complexity**: O(n) (recursion + dp array)

---

## ✅ Approach 3: Tabulation (Bottom-Up DP)

We iteratively build the solution from step `1` to `n`.

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;

        int[] dp = new int[n + 1];
        dp[1] = 1; // 1 way to reach step 1
        dp[2] = 2; // 2 ways to reach step 2

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
```

🔎 **Explanation**:

* Start with known base cases.
* Build answers iteratively for each step until `n`.

⏱ **Time Complexity**: O(n)
📦 **Space Complexity**: O(n)

---

## ✅ Approach 4: Tabulation + Space Optimization

We notice that only the last two values are needed, so we reduce space to **O(1)**.

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;

        int prev1 = 2; // ways to reach step 2
        int prev2 = 1; // ways to reach step 1
        int curr = 0;

        for (int i = 3; i <= n; i++) {
            curr = prev1 + prev2; // current = sum of last two
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1; // final answer
    }
}
```

🔎 **Explanation**:

* Maintain only two variables (`prev1` and `prev2`).
* Update them as we climb steps iteratively.

⏱ **Time Complexity**: O(n)
📦 **Space Complexity**: O(1)

---

## 🔑 Summary of Approaches

| Approach                         | Time Complexity | Space Complexity |
| -------------------------------- | --------------- | ---------------- |
| ✅ Approach 1: Simple Recursion   | O(2^n)          | O(n) recursion   |
| ✅ Approach 2: Recursion + Memo   | O(n)            | O(n)             |
| ✅ Approach 3: Tabulation         | O(n)            | O(n)             |
| ✅ Approach 4: Space Optimization | O(n)            | O(1)             |

---

👉 The **most optimal approach** is **Approach 4: Tabulation with Space Optimization**.

