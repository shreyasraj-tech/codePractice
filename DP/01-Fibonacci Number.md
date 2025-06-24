# LeetCode 509 - Fibonacci Number (Dynamic Programming)
(https://leetcode.com/problems/fibonacci-number/)

## Problem Statement

Given `n`, return the nth number in the Fibonacci sequence. The sequence is defined as:

```
F(0) = 0,
F(1) = 1,
F(n) = F(n-1) + F(n-2), for n > 1
```

## 🧠 1. Memoization (Top-Down)

### 🔷 Concept:

* Solve the problem **recursively**.
* **Store** results of subproblems in a **cache** (like an array or HashMap).
* Avoids repeated calculations by **checking the cache first**.

### ✅ Characteristics:

* **Recursive approach**
* Uses **call stack** (top-down)
* Caches results for reuse
* More intuitive if you start from recursion

### 📦 Code (Recursive with Memoization):

```java
int[] dp = new int[n + 1];
Arrays.fill(dp, -1);

int fib(int n) {
    if (n <= 1) return n;
    if (dp[n] != -1) return dp[n];
    return dp[n] = fib(n - 1) + fib(n - 2);
}
```

### 📈 Time Complexity:

* `O(n)` – each value is calculated once

### 🧮 Space Complexity:

* `O(n)` for `dp[]`
* `O(n)` call stack due to recursion

---

## 📊 2. Tabulation (Bottom-Up)

### 🔷 Concept:

* Build the solution **iteratively from the base cases**.
* Start from `0`, `1`, and build up to `n`.
* Completely **avoids recursion**.

### ✅ Characteristics:

* **Iterative approach**
* Fills up a table from **bottom to top**
* No stack overflow risk
* Often faster in practice (no recursion overhead)

### 📦 Code (Iterative with Tabulation):

```java
int fib(int n) {
    if (n == 0) return 0;
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];
}
```

### 📈 Time Complexity:

* `O(n)`

### 🧮 Space Complexity:

* `O(n)` for `dp[]` (can be optimized to `O(1)`)

---

## 🔄 Summary Comparison

| Feature              | Memoization (Top-Down)      | Tabulation (Bottom-Up)    |
| -------------------- | --------------------------- | ------------------------- |
| Approach             | Recursive                   | Iterative                 |
| Stack Usage          | Yes (can overflow)          | No                        |
| Caching              | Yes                         | Yes                       |
| Easier for Beginners | Yes (if used to recursion)  | Slightly harder initially |
| Optimization         | Difficult to optimize space | Can be optimized to O(1)  |

---

Do you want me to **add these notes to canvas** or show a **space-optimized version** of tabulation?

