✅ **Problem Name:** Max Sum without Adjacent
✅ **Link:** [GeeksforGeeks problem link](https://www.geeksforgeeks.org/problems/max-sum-without-adjacents2430/1)

---

## 🟢 **Problem Statement (Simplified)**

Given an array of positive integers, find the maximum sum of elements such that no two chosen elements are adjacent in the array.

**Example:**

```
Input: arr = [5, 5, 10, 100, 10, 5]  
Output: 110  
Explanation: Pick indices 0, 3, and 5 → 5 + 100 + 5 = 110.
```

---

## 🟢 **Memoization Method Explanation**

This method uses **top-down dynamic programming** with recursion + memoization, avoiding recomputation of overlapping subproblems.

**Key recursive idea:**

* At every index `i`, you have two choices:

  1. **pick** the current element `arr[i]` → but then you cannot pick `i-1`, so you solve subproblem `i-2`.
  2. **not pick** the current element → you move to `i-1`.

Take the maximum of these two.

---

### 🔎 Code (Memoization)

```java
class Solution {
    static int help(int i, int arr[], int[] dp) {
        if (i == 0) return arr[i];
        if (i < 0) return 0;
        if (dp[i] != -1) return dp[i];

        int pick = arr[i] + help(i - 2, arr, dp);   // pick current, jump to i-2
        int notPick = help(i - 1, arr, dp);         // skip current, go to i-1
        
        return dp[i] = Math.max(pick, notPick);
    }

    int findMaxSum(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return help(n - 1, arr, dp);
    }
}
```

---

## 🟢 **Tabulation Method Explanation**

This is the **bottom-up** DP approach. You build the answer iteratively from smaller to larger subproblems.

**Logic:**

* `dp[i]` stores the maximum sum achievable up to index `i`.
* base cases:

  * `dp[0] = arr[0]`
  * `dp[1] = max(arr[0], arr[1])`
* for each `i >= 2`:

  * pick current → `arr[i] + dp[i-2]`
  * not pick → `dp[i-1]`
  * `dp[i] = max(pick, notPick)`

---

### 🔎 Code (Tabulation)

```java
int findMaxSum(int arr[], int n) {
    if (n == 0) return 0;
    if (n == 1) return arr[0];

    int[] dp = new int[n];
    dp[0] = arr[0];
    dp[1] = Math.max(arr[0], arr[1]);

    for (int i = 2; i < n; i++) {
        int pick = arr[i] + dp[i - 2];
        int notPick = dp[i - 1];
        dp[i] = Math.max(pick, notPick);
    }
    return dp[n - 1];
}
```

---

## 🟢 **Breakdown of Important Lines**

✅ `int pick = arr[i] + help(i - 2, arr, dp);`
👉 Means you **pick** the current value `arr[i]`, and then move to index `i-2` because adjacent cannot be picked.

✅ `int notPick = 0 + help(i - 1, arr, dp);`
👉 Means you **do not pick** the current value, and directly check the best for index `i-1`.

✅ `return dp[i] = Math.max(pick, notPick);`
👉 Store and return the best between picking and not picking the current index.

---

✅ **Let’s break down the Tabulation method properly, with a clear explanation of efficiency, example, and the role of `dp[1] = max(arr[0], arr[1])`.**

---

## 🟢 **What is Tabulation Method?**

Tabulation = **Bottom-up Dynamic Programming**
👉 Instead of solving large problems first (like memoization), we **solve all smaller subproblems** and use their results to build up to the final solution.

👉 We fill a DP table **iteratively**, no recursion needed → this avoids function call overhead and is typically more memory/cache friendly.

👉 Time: O(n)
👉 Space: O(n) (can be reduced to O(1) — I’ll mention that later)

---

## 🟢 **How it works in this problem?**

### The idea

For each index `i`, we need to know:

* What is the best sum **if we pick `arr[i]`**?
  👉 We must skip `arr[i-1]`, so: `arr[i] + dp[i-2]`

* What is the best sum **if we don't pick `arr[i]`?**
  👉 We simply take `dp[i-1]`

We choose:

```
dp[i] = max(pick, notPick)
```

---

## 🟢 **Why `dp[1] = max(arr[0], arr[1])` ?**

💡 At index `1`, you can:

* Either pick `arr[0]`
* Or pick `arr[1]`

👉 You can’t pick both `arr[0] + arr[1]` because they’re adjacent!
👉 So `dp[1]` stores the **best sum** up to index 1 — the larger of these two.

---

## 🟢 **Example with arr = \[5, 5, 10, 100, 10, 5]**

Let’s build the DP table:

```
arr = [5, 5, 10, 100, 10, 5]
```

```
dp[0] = 5   → just pick arr[0]
dp[1] = max(5, 5) = 5  → pick the better of arr[0] or arr[1]
```

Now fill rest:

```
i = 2
pick = arr[2] + dp[0] = 10 + 5 = 15
notPick = dp[1] = 5
dp[2] = max(15, 5) = 15

i = 3
pick = 100 + dp[1] = 100 + 5 = 105
notPick = dp[2] = 15
dp[3] = max(105, 15) = 105

i = 4
pick = 10 + dp[2] = 10 + 15 = 25
notPick = dp[3] = 105
dp[4] = max(25, 105) = 105

i = 5
pick = 5 + dp[3] = 5 + 105 = 110
notPick = dp[4] = 105
dp[5] = max(110, 105) = 110
```

✅ Final answer: `dp[5] = 110`

---

## 🟢 **Efficiency gained**

* **No recursion stack** → constant stack space
* **No recomputation** → each dp\[i] is computed exactly once
* **O(n) time and space**
* Works well for large arrays — no TLE

---

## 🟢 **Final code (Tabulation)**

```java
int findMaxSum(int arr[], int n) {
    if (n == 0) return 0;
    if (n == 1) return arr[0];

    int[] dp = new int[n];
    dp[0] = arr[0];
    dp[1] = Math.max(arr[0], arr[1]);

    for (int i = 2; i < n; i++) {
        int pick = arr[i] + dp[i - 2];
        int notPick = dp[i - 1];
        dp[i] = Math.max(pick, notPick);
    }
    return dp[n - 1];
}
```

---

## 🟢 **Space optimized version**

Since we only need `dp[i-1]` and `dp[i-2]` at each step:

```java
int findMaxSum(int arr[], int n) {
    if (n == 0) return 0;
    if (n == 1) return arr[0];

    int prev2 = arr[0];
    int prev1 = Math.max(arr[0], arr[1]);

    for (int i = 2; i < n; i++) {
        int pick = arr[i] + prev2;
        int notPick = prev1;
        int curr = Math.max(pick, notPick);

        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```

👉 **O(1) space, O(n) time**

---
