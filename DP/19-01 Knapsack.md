# ✅ [GFG- 0/1 Knapsack Problem ](https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1)

---

## 📘 1. Problem Explanation

You are given:

* An integer `W` → **maximum capacity** of the knapsack
* `n` items
* Two arrays:

  * `wt[i]` → weight of the i-th item
  * `val[i]` → value of the i-th item

### 🎯 Goal

Find the **maximum total value** you can put into the knapsack **without exceeding capacity `W`**.

### ❗ Constraints / Rules

* Each item can be taken **at most once** (0/1 choice)
* You **cannot take fractions** of an item
* Order of items does **not** matter

---

## 🧩 2. Thought Process & Similar Patterns

This problem follows the **0/1 Knapsack Pattern**:

* Every item gives **two choices**:

  * **Take** the item
  * **Do not take** the item

This exact thought process appears in:

* Subset Sum
* Partition Equal Subset Sum
* Count Subsets with Sum K
* Target Sum

> 🔑 Golden Thought:
> “For every item, I either **take it (once)** or **skip it**.”

---

## 🧠 3. Core Logic (Pick / Not Pick)

At item index `i` and remaining capacity `W`:

| Choice       | Action        | Effect                      |
| ------------ | ------------- | --------------------------- |
| **Not Take** | Skip item `i` | Capacity stays same         |
| **Take**     | Take item `i` | Capacity reduces by `wt[i]` |

### 🔁 Transition

```
notTake = f(i-1, W)
take    = val[i] + f(i-1, W - wt[i])   (only if wt[i] <= W)

f(i, W) = max(take, notTake)
```

---

## 0️⃣ 4. Recursion (Brute Force)

---

### 🧠 Recursive State

```
f(i, W) = maximum value using items from 0..i with capacity W
```

---

### 🧮 Base Case

```
If i == 0:
    If wt[0] <= W → return val[0]
    Else → return 0
```

---

### 🔁 Recursive Relation

```
notTake = f(i-1, W)

take = -∞
If wt[i] <= W:
    take = val[i] + f(i-1, W - wt[i])

return max(take, notTake)
```

---

### ❌ Drawback

* Repeated subproblems
* Exponential time → **TLE**

---

### ✅ Java Code (Recursion)

```java
class Solution {
    public int knapsack(int i, int W, int[] wt, int[] val) {
        if (i == 0) {
            if (wt[0] <= W) return val[0];
            return 0;
        }

        int notTake = knapsack(i - 1, W, wt, val);
        int take = Integer.MIN_VALUE;

        if (wt[i] <= W)
            take = val[i] + knapsack(i - 1, W - wt[i], wt, val);

        return Math.max(take, notTake);
    }
}
```

⏱ **Time Complexity:** `O(2^n)`
🧠 **Space Complexity:** `O(n)` (recursion stack)

---

## 1️⃣ 5. Memoization (Top-Down DP)

---

### 💡 Idea

Store results of already solved subproblems to avoid recomputation.

---

### 🧠 DP State

```
dp[i][W] = maximum value using items 0..i with capacity W
```

---

### 🔁 Logic

```
If dp[i][W] != -1 → return dp[i][W]

notTake = f(i-1, W)

take = -∞
If wt[i] <= W:
    take = val[i] + f(i-1, W - wt[i])

dp[i][W] = max(take, notTake)
```

---

### ✅ Java Code (Memoization)

```java
class Solution {
    int[][] dp;

    public int knapSack(int W, int[] wt, int[] val, int n) {
        dp = new int[n][W + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return helper(n - 1, W, wt, val);
    }

    private int helper(int i, int W, int[] wt, int[] val) {
        if (i == 0) {
            if (wt[0] <= W) return val[0];
            return 0;
        }

        if (dp[i][W] != -1) return dp[i][W];

        int notTake = helper(i - 1, W, wt, val);
        int take = Integer.MIN_VALUE;

        if (wt[i] <= W)
            take = val[i] + helper(i - 1, W - wt[i], wt, val);

        return dp[i][W] = Math.max(take, notTake);
    }
}
```

⏱ **Time Complexity:** `O(n × W)`
🧠 **Space Complexity:** `O(n × W)` + recursion stack

---

## 2️⃣ 6. Tabulation (Bottom-Up DP)

---

### 💡 Idea

Build the solution **iteratively** starting from smaller subproblems.

---

### 🧠 DP Definition

```
dp[i][w] = maximum value using items 0..i with capacity w
```

---

### 🧮 Initialization

```
dp[0][w] = val[0]  if wt[0] <= w
Else 0
```

---

### 🔁 Transition

```
notTake = dp[i-1][w]

take = -∞
If wt[i] <= w:
    take = val[i] + dp[i-1][w - wt[i]]

dp[i][w] = max(take, notTake)
```

---

### ✅ Java Code (Tabulation)

```java
class Solution {
    public int knapSack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n][W + 1];

        for (int w = wt[0]; w <= W; w++)
            dp[0][w] = val[0];

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                int notTake = dp[i - 1][w];
                int take = Integer.MIN_VALUE;

                if (wt[i] <= w)
                    take = val[i] + dp[i - 1][w - wt[i]];

                dp[i][w] = Math.max(take, notTake);
            }
        }
        return dp[n - 1][W];
    }
}
```

⏱ **Time Complexity:** `O(n × W)`
🧠 **Space Complexity:** `O(n × W)`

---

## 🚀 Final Summary

| Approach    | Time Complexity | Space Complexity |
| ----------- | --------------- | ---------------- |
| Recursion   | `O(2^n)`        | `O(n)`           |
| Memoization | `O(n × W)`      | `O(n × W)`       |
| Tabulation  | `O(n × W)`      | `O(n × W)`       |

---

### 🔑 One-Line Golden Rule

> “For every item, either **take it once and reduce capacity**, or **skip it and move on** — choose the option that gives **maximum value**.”
