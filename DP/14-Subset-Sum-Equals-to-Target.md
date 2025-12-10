# ✅ Subset Sum Equals to Target [(GeeksforGeeks Problem Link)](https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1)
---

### 📘 Problem Reminder

You are given an array `arr[]` of `n` **non-negative integers** and a target integer `S`.

Your task is to determine whether there exists **any subset** of the given array whose sum is **exactly equal to `S`**.

✅ Each element can be used **at most once**  
✅ Order of elements does **not** matter  
✅ You only need to return **true/false**

---

### 🧩 Key Points

- This is a classic **0/1 Knapsack (Decision Version)** problem.
- Every element has **two choices** → take it or skip it.
- This problem is solved efficiently using **Dynamic Programming**.
- Constraints allow a **pseudo-polynomial DP solution**.



# 🎯 Key Takeaways

* Subset Sum is the **foundation of many DP problems**:

  * Partition Equal Subset Sum
  * Count of Subsets
  * Target Sum
  * 0/1 Knapsack
* Always traverse **right to left** for 1D optimization.
* Decision problems → use **boolean DP**.
* This problem transitions naturally into **Knapsack Pattern**.


---

## ✅ Understanding **pick / notPick** (Note Version)

At every element in the array, we have **only two choices**:

| Choice      | Meaning                          |
| ----------- | -------------------------------- |
| **pick**    | Take the element into the subset |
| **notPick** | Ignore the element               |

There is **no other option**.

---

## ✅ What happens to the target?

Let current number be `x` and remaining target be `T`:

| Action        | New Target |
| ------------- | ---------- |
| **pick x**    | `T - x`    |
| **notPick x** | `T`        |

So:

* **pick** → reduces the target
* **notPick** → keeps the target same

---

## ✅ Why this works

A **subset is formed only by**:

* Choosing some elements → **pick**
* Skipping some elements → **notPick**

Trying both choices for every element guarantees:
✅ All possible subsets are explored
✅ No valid answer is missed

---

From the current number:

```
notPick → f(i-1, T)
pick    → f(i-1, T - arr[i-1])
```

Final decision:

```
f(i, T) = notPick OR pick
```

---

## ✅ Mini Example (One-Line)

```
arr = [2,3,6], target = 5
pick 2 → target = 3 → pick 3 → target = 0 ✅
```

---

## ✅ One-Line Golden Rule (Best for Notes)

> “For every number, either I **pick it and reduce the target**, or I **notPick it and keep the target same**.
> If the target becomes **0**, a valid subset is found.”

# **0️⃣ Recursion (Brute Force)**

---

### 🎯 Intuition

For every element, we have **two choices**:

- **Include it** in the subset  
- **Exclude it** from the subset  

So at every index, the recursion branches into **2 paths**.

---

### 🧠 Recursive State

Let  
`f(i, sum)` = **true** if a subset from first `i` elements can form sum `sum`.

---

### 🧮 Base Cases

```

If sum == 0 → return true  (empty subset possible)
If i == 0 → return false  (no elements left but sum not formed)

```

---

### 🔁 Transition (Recurrence)

```

If arr[i-1] > sum:
f(i, sum) = f(i-1, sum)

Else:
f(i, sum) = f(i-1, sum) OR f(i-1, sum - arr[i-1])

````

---

### ⚠️ Problem with Pure Recursion

- Huge number of repeated subproblems
- Exponential time complexity
- Leads to **TLE** for large inputs

---

### ✅ Java Code (Recursion)

```java
class Solution {
    public boolean isSubsetSum(int[] arr, int n, int target) {
        return helper(arr, n, target);
    }

    private boolean helper(int[] arr, int i, int sum) {
        if (sum == 0) return true;
        if (i == 0) return false;

        if (arr[i - 1] > sum)
            return helper(arr, i - 1, sum);

        return helper(arr, i - 1, sum) ||
               helper(arr, i - 1, sum - arr[i - 1]);
    }
}
````

✅ **Time Complexity:** `O(2^n)`
✅ **Space Complexity:** `O(n)` (recursion stack)

---

# **1️⃣ Memoization (Top-Down DP n -> 0)**

---

### 💡 Idea

Store already computed results in a DP table to avoid recomputation.

---

### 🧠 DP State

```
dp[i][sum] = true/false
```

Meaning:

> Can a subset of first `i` elements form sum `sum`?

---

### 🧮 Recursive + Memoization Formula

```
If sum == 0 → true
If i == 0 → false
If dp[i][sum] already exists → return it

If arr[i-1] > sum:
    dp[i][sum] = f(i-1, sum)
Else:
    dp[i][sum] = f(i-1, sum) OR f(i-1, sum-arr[i-1])
```

---

### ✅ Java Code (Memoization)

```java
class Solution {
    Boolean[][] dp;

    public boolean isSubsetSum(int[] arr, int n, int target) {
        dp = new Boolean[n + 1][target + 1];
        return helper(arr, n, target);
    }

    private boolean helper(int[] arr, int i, int sum) {
        if (sum == 0) return true;
        if (i == 0) return false;

        if (dp[i][sum] != null) return dp[i][sum];

        if (arr[i - 1] > sum)
            dp[i][sum] = helper(arr, i - 1, sum);
        else
            dp[i][sum] = helper(arr, i - 1, sum) ||
                         helper(arr, i - 1, sum - arr[i - 1]);

        return dp[i][sum];
    }
}
```

✅ **Time Complexity:** `O(n × S)`
✅ **Space Complexity:** `O(n × S)` + recursion stack

---

# **2️⃣ Tabulation (Bottom-Up DP)**

---

### 💡 Idea

Build the solution **iteratively** using a DP table.

---

### 🧩 State Definition

```
dp[i][s] = true if we can make sum s using first i elements
```

---

### 🧮 Initialization

```
dp[i][0] = true  (sum 0 always possible)
dp[0][s] = false for all s > 0
```

---

### 🔁 Transition

```
If arr[i-1] > s:
    dp[i][s] = dp[i-1][s]
Else:
    dp[i][s] = dp[i-1][s] OR dp[i-1][s - arr[i-1]]
```

---

### ✅ Java Code (Tabulation)

```java
class Solution {
    public boolean isSubsetSum(int[] arr, int n, int target) {
        boolean[][] dp = new boolean[n + 1][target + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= target; s++) {
                if (arr[i - 1] > s)
                    dp[i][s] = dp[i - 1][s];
                else
                    dp[i][s] = dp[i - 1][s] ||
                               dp[i - 1][s - arr[i - 1]];
            }
        }

        return dp[n][target];
    }
}
```

✅ **Time Complexity:** `O(n × S)`
✅ **Space Complexity:** `O(n × S)`

---

# **3️⃣ Space Optimization**

---

### ⚙️ Observation

Only **previous row** is required to compute the current row.

So we convert **2D DP → 1D DP**.

---

### 💡 Space Optimization Logic

```
boolean dp[sum + 1]
Update dp[] from right to left
```

---

### ✅ Java Code (Space Optimized)

```java
class Solution {
    public boolean isSubsetSum(int[] arr, int n, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int s = target; s >= arr[i]; s--) {
                if (dp[s - arr[i]])
                    dp[s] = true;
            }
        }

        return dp[target];
    }
}
```

✅ **Time Complexity:** `O(n × S)`
✅ **Space Complexity:** `O(S)`

---

# 🚀 Summary

| Approach        | Description         | Time       | Space      |
| --------------- | ------------------- | ---------- | ---------- |
| Recursion       | Try include/exclude | `O(2^n)`   | `O(n)`     |
| Memoization     | Cache DP states     | `O(n × S)` | `O(n × S)` |
| Tabulation      | Bottom-up DP        | `O(n × S)` | `O(n × S)` |
| Space Optimized | 1D DP               | `O(n × S)` | `O(S)`     |

---



So `n + 1` rows for same reason ✅

