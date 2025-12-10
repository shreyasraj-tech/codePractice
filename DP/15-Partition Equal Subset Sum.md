# ✅ Partition Equal Subset Sum [(LeetCode 416)](https://leetcode.com/problems/partition-equal-subset-sum/description/)
---

### 📘 Problem Reminder

You are given an integer array `nums`.

Your task is to determine **whether you can partition the array into two subsets such that the sum of both subsets is equal**.

---

### 🧩 Key Points

- Each number can be used **only once**.
- Order does **not** matter.
- The array must be split into **exactly two subsets**.
- If total sum is **odd**, answer is immediately **false**.
- If total sum is **even**, the problem reduces to:
  
> ✅ **Can we find a subset with sum = (totalSum / 2)?**

This becomes a **Subset Sum Equals Target** problem.

---

# **0️⃣ Recursion (Brute Force)**

---

### 🎯 Intuition

If total array sum is `SUM`, then:

- If `SUM` is **odd** → partition is **impossible**.
- If `SUM` is **even** → we only need to check:
  
```

Can we find a subset with sum = SUM / 2 ?

```

At every index, we make **two decisions**:

- ✅ pick the number
- ❌ notPick the number

---

### 🧠 Recursive State

Let:

```

f(i, sum)

```

= Can we form `sum` using first `i` elements?

---

### 🧮 Base Cases

```

If sum == 0 → return true
If i == 0 → return false

```

---

### 🔁 Transition (Recurrence)

```

If nums[i-1] > sum:
f(i, sum) = f(i-1, sum)
Else:
f(i, sum) = f(i-1, sum) OR f(i-1, sum - nums[i-1])

````

---

### ⚠️ Problem with Pure Recursion

- Each element generates **2 branches**
- Time grows exponentially
- Results in **TLE**

---

### ✅ Java Code (Recursion)

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;

        if (sum % 2 != 0) return false;

        return helper(nums, nums.length, sum / 2);
    }

    private boolean helper(int[] nums, int i, int sum) {
        if (sum == 0) return true;
        if (i == 0) return false;

        if (nums[i - 1] > sum)
            return helper(nums, i - 1, sum);

        return helper(nums, i - 1, sum) ||
               helper(nums, i - 1, sum - nums[i - 1]);
    }
}
````

✅ **Time Complexity:** `O(2^n)`
✅ **Space Complexity:** `O(n)`

---

# **1️⃣ Memoization (Top-Down DP)**

---

### 💡 Idea

Cache recursive states so that each `(i, sum)` is computed **only once**.

---

### 🧮 Recursive + Memoization Formula

```
If sum == 0 → true
If i == 0 → false
If dp[i][sum] exists → return it

If nums[i-1] > sum:
    dp[i][sum] = f(i-1, sum)
Else:
    dp[i][sum] = f(i-1, sum) OR f(i-1, sum - nums[i-1])
```

---

### ✅ Java Code (Memoization)

```java
class Solution {
    Boolean[][] dp;

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        dp = new Boolean[nums.length + 1][target + 1];

        return helper(nums, nums.length, target);
    }

    private boolean helper(int[] nums, int i, int sum) {
        if (sum == 0) return true;
        if (i == 0) return false;

        if (dp[i][sum] != null) return dp[i][sum];

        if (nums[i - 1] > sum)
            dp[i][sum] = helper(nums, i - 1, sum);
        else
            dp[i][sum] = helper(nums, i - 1, sum) ||
                         helper(nums, i - 1, sum - nums[i - 1]);

        return dp[i][sum];
    }
}
```

✅ **Time Complexity:** `O(n × target)`
✅ **Space Complexity:** `O(n × target)` + recursion stack

---

# **2️⃣ Tabulation (Bottom-Up DP)**

---

### 💡 Idea

We build a **2D DP table** where:

```
dp[i][s] = true
```

means:

> Using first `i` numbers, we can form sum `s`.

---

### 🧮 Initialization

```
dp[i][0] = true  (sum 0 always possible)
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
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        int n = nums.length;

        boolean[][] dp = new boolean[n + 1][target + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= target; s++) {
                if (nums[i - 1] > s)
                    dp[i][s] = dp[i - 1][s];
                else
                    dp[i][s] = dp[i - 1][s] ||
                               dp[i - 1][s - nums[i - 1]];
            }
        }

        return dp[n][target];
    }
}
```

✅ **Time Complexity:** `O(n × target)`
✅ **Space Complexity:** `O(n × target)`

---

# **3️⃣ Space Optimization**

---

### ⚙️ Observation

Each row depends only on the **previous row**.
So we can compress `2D DP → 1D DP`.

---

### 💡 Space Optimization Logic

```
boolean dp[target + 1]
Traverse from RIGHT to LEFT
```

---

### ✅ Java Code (Space Optimized)

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                if (dp[s - num])
                    dp[s] = true;
            }
        }

        return dp[target];
    }
}
```

✅ **Time Complexity:** `O(n × target)`
✅ **Space Complexity:** `O(target)`

---

# 🚀 Summary

| Approach        | Description     | Time            | Space           |
| --------------- | --------------- | --------------- | --------------- |
| Recursion       | pick / notPick  | `O(2^n)`        | `O(n)`          |
| Memoization     | Cache DP states | `O(n × target)` | `O(n × target)` |
| Tabulation      | Bottom-up DP    | `O(n × target)` | `O(n × target)` |
| Space Optimized | 1D DP           | `O(n × target)` | `O(target)`     |

---

# 🎯 Key Takeaways

* Partition Equal Subset Sum is just:

> ✅ **Subset Sum with target = totalSum / 2**

* If total sum is **odd**, answer is immediately **false**.
* This problem directly uses:

  * pick / notPick logic
  * Subset Sum DP
* 1D optimization must iterate **right → left** to avoid reusing elements.

---

```

---

If you want, next I can also:

✅ Visually draw the DP matrix for Partition  
✅ Show decision tree for a sample input  
✅ Explain why right-to-left traversal is mandatory  
✅ Convert this into a **handwritten-style revision sheet**

Just tell me which one you want 👍
```
