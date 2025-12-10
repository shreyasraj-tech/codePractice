
# ✅ Partition A Set Into Two Subsets With Minimum Absolute Sum Difference [(LeetCode Problem Link)](https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/)
---

### 📘 Problem Reminder

You are given an integer array `nums`.

Your task is to **partition the array into two subsets** such that the **absolute difference between the sum of the two subsets is minimized**.

Return the **minimum possible absolute difference**.

---

### 🧩 Key Points

- Each element must go into **exactly one** of the two subsets.
- The two subsets together must include **all elements**.
- The goal is to **minimize:**

```

| sum1 - sum2 |

```

- Let total sum be `TOTAL`.
- If we choose one subset with sum = `S1`, then the other subset will have:

```

S2 = TOTAL - S1
Difference = | TOTAL - 2*S1 |

```

✅ So the problem becomes:

> **Find a subset with sum `S1` such that `|TOTAL - 2*S1|` is minimized.**

This is a direct **extension of Subset Sum DP**.

---

# **0️⃣ Recursion (Brute Force)**

---

### 🎯 Intuition

At every index, we decide:

- ✅ pick → put element in subset-1
- ❌ notPick → put element in subset-2

When all elements are processed, we compute:

```

difference = |sum1 - sum2|

```

And take the minimum across all possibilities.

---

### 🧠 Recursive State

Let:

```

f(i, sum1)

```

= Minimum absolute difference we can get using first `i` elements with subset-1 having sum `sum1`.

Remaining sum automatically goes to subset-2.

---

### 🧮 Base Case

```

If i == n:
sum2 = TOTAL - sum1
return |sum1 - sum2|

```

---

### 🔁 Transition (Recurrence)

```

pick    → f(i+1, sum1 + nums[i])
notPick → f(i+1, sum1)

```

Final decision:

```

f(i, sum1) = min(pick, notPick)

````

---

### ⚠️ Problem with Pure Recursion

- Each element creates **2 branches**
- Exponential growth → **TLE**
- Not feasible for large `n`

---

### ✅ Java Code (Recursion)

```java
class Solution {
    int total;

    public int minimumDifference(int[] nums) {
        total = 0;
        for (int x : nums) total += x;
        return helper(nums, 0, 0);
    }

    private int helper(int[] nums, int i, int sum1) {
        if (i == nums.length) {
            int sum2 = total - sum1;
            return Math.abs(sum1 - sum2);
        }

        int pick = helper(nums, i + 1, sum1 + nums[i]);
        int notPick = helper(nums, i + 1, sum1);

        return Math.min(pick, notPick);
    }
}
````

✅ **Time Complexity:** `O(2^n)`
✅ **Space Complexity:** `O(n)`

---

# **1️⃣ Memoization (Top-Down DP)**

---

### 💡 Idea

Many `(i, sum1)` states repeat → cache their results.

---

### 🧮 Recursive + Memoization Formula

```
If i == n:
    return |sum1 - (TOTAL - sum1)|

If dp[i][sum1] exists:
    return dp[i][sum1]

pick    = f(i+1, sum1 + nums[i])
notPick = f(i+1, sum1)

dp[i][sum1] = min(pick, notPick)
```

---

### ✅ Java Code (Memoization)

```java
class Solution {
    int total;
    Integer[][] dp;

    public int minimumDifference(int[] nums) {
        total = 0;
        for (int x : nums) total += x;

        dp = new Integer[nums.length][total + 1];
        return helper(nums, 0, 0);
    }

    private int helper(int[] nums, int i, int sum1) {
        if (i == nums.length) {
            int sum2 = total - sum1;
            return Math.abs(sum1 - sum2);
        }

        if (dp[i][sum1] != null)
            return dp[i][sum1];

        int pick = helper(nums, i + 1, sum1 + nums[i]);
        int notPick = helper(nums, i + 1, sum1);

        return dp[i][sum1] = Math.min(pick, notPick);
    }
}
```

✅ **Time Complexity:** `O(n × TOTAL)`
✅ **Space Complexity:** `O(n × TOTAL)` + recursion stack

---

# **2️⃣ Tabulation (Bottom-Up DP)**

---

### 💡 Idea

Instead of directly minimizing difference, we first find **all possible subset sums** using **boolean Subset Sum DP**.

Then we check:

```
For every S1 from 0 → TOTAL/2:
    minimize |TOTAL - 2*S1|
```

---

### 🧩 State Definition

```
dp[i][s] = true
```

means:

> Using first `i` elements, we can form sum `s`.

---

### 🧮 Initialization

```
dp[i][0] = true
dp[0][s] = false for s > 0
```

---

### 🔁 Transition

```
If nums[i-1] > s:
    dp[i][s] = dp[i-1][s]
Else:
    dp[i][s] = dp[i-1][s] OR dp[i-1][s - nums[i-1]]
```

---

### ✅ Java Code (Tabulation)

```java
class Solution {
    public int minimumDifference(int[] nums) {
        int total = 0;
        for (int x : nums) total += x;

        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][total + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= total; s++) {
                if (nums[i - 1] > s)
                    dp[i][s] = dp[i - 1][s];
                else
                    dp[i][s] = dp[i - 1][s] ||
                               dp[i - 1][s - nums[i - 1]];
            }
        }

        int minDiff = Integer.MAX_VALUE;

        for (int s1 = 0; s1 <= total / 2; s1++) {
            if (dp[n][s1]) {
                int s2 = total - s1;
                minDiff = Math.min(minDiff, Math.abs(s1 - s2));
            }
        }

        return minDiff;
    }
}
```

✅ **Time Complexity:** `O(n × TOTAL)`
✅ **Space Complexity:** `O(n × TOTAL)`

---

# **3️⃣ Space Optimization**

---

### ⚙️ Observation

Each row depends only on the **previous row**.
So we can compress **2D DP → 1D DP**.

---

### 💡 Space Optimization Logic

```
boolean dp[TOTAL + 1]
Traverse from RIGHT → LEFT
```

---

### ✅ Java Code (Space Optimized)

```java
class Solution {
    public int minimumDifference(int[] nums) {
        int total = 0;
        for (int x : nums) total += x;

        boolean[] dp = new boolean[total + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int s = total; s >= num; s--) {
                if (dp[s - num])
                    dp[s] = true;
            }
        }

        int minDiff = Integer.MAX_VALUE;

        for (int s1 = 0; s1 <= total / 2; s1++) {
            if (dp[s1]) {
                int s2 = total - s1;
                minDiff = Math.min(minDiff, Math.abs(s1 - s2));
            }
        }

        return minDiff;
    }
}
```

✅ **Time Complexity:** `O(n × TOTAL)`
✅ **Space Complexity:** `O(TOTAL)`

---

# 🚀 Summary

| Approach        | Description                | Time           | Space          |
| --------------- | -------------------------- | -------------- | -------------- |
| Recursion       | Try all partitions         | `O(2^n)`       | `O(n)`         |
| Memoization     | Cache `(i, sum1)` states   | `O(n × TOTAL)` | `O(n × TOTAL)` |
| Tabulation      | Subset sum DP + diff check | `O(n × TOTAL)` | `O(n × TOTAL)` |
| Space Optimized | 1D DP                      | `O(n × TOTAL)` | `O(TOTAL)`     |

---

# 🎯 Key Takeaways

* This problem is a **direct extension of Subset Sum DP**.
* Instead of checking exact equality, we **minimize the difference**.
* We only need to check subset sums from `0 → TOTAL/2`.
* Final formula used:

```
min |TOTAL - 2*S1|
```

* This pattern is the base of:

  * Balanced Partition
  * Load Balancing
  * Scheduling problems
  * Minimum Knapsack Difference

---

```

---

If you want next, I can also:

✅ Visually draw the DP matrix  
✅ Show dry-run on a real example  
✅ Compare this with **Partition Equal Subset Sum**  
✅ Make a **one-page handwritten revision sheet**

Just tell me which one you want 👍
```
