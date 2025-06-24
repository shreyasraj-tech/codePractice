# LeetCode 509 - Fibonacci Number (Dynamic Programming)
(https://leetcode.com/problems/fibonacci-number/)

## Problem Statement

Given `n`, return the nth number in the Fibonacci sequence. The sequence is defined as:

```
F(0) = 0,
F(1) = 1,
F(n) = F(n-1) + F(n-2), for n > 1
```

## Errors Found in User Code

### ❌ Problem 1: Incorrect return in `fib()` method

* **Original Code:**

  ```java
  public int fib(int n) {
      int[] dp = new int[n+1];
      Arrays.fill(dp,-1);
      System.out.println(f(n,dp));
  }
  ```
* **Issue:** The method return type is `int`, but it uses `System.out.println(...)` instead of `return`.
* **Fix:** Replace `System.out.println(f(n, dp));` with `return f(n, dp);`

### ❌ Problem 2: `ArrayIndexOutOfBoundsException` when `n == 0`

* **Original Error:** Accessing `dp[1]` when `n == 0` leads to out-of-bounds.
* **Fix:** Add a condition to return `0` directly when `n == 0`:

  ```java
  if (n == 0) return 0;
  ```

---

## Final Corrected Java Code (Top-Down Memoization)

```java
import java.util.Arrays;

class Solution {
    static int f(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = f(n - 1, dp) + f(n - 2, dp);
    }

    public int fib(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return f(n, dp);
    }
}
```

---

## What is This Approach?

### ✅ **Dynamic Programming - Memoization (Top-Down)**

* **Memoization** is a technique in Dynamic Programming where we store the results of expensive function calls and reuse them when the same inputs occur again.
* This prevents redundant calculations and improves performance from exponential to linear time.

### 🔍 Steps in Memoization:

1. Define a recursive function for the problem.
2. Store already computed values in a cache (array or hashmap).
3. Before computing a new value, check the cache.
4. Use stored value if available, else compute and store it.

### 🧠 Time and Space Complexity:

* **Time Complexity:** O(n) — Each Fibonacci number is calculated once.
* **Space Complexity:** O(n) — For the `dp` array and recursion stack.
