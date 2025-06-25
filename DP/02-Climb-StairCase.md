---

## 📝 Problem Note: **Climbing Stairs**

### **Problem Statement:**

You are climbing a staircase. It takes `n` steps to reach the top. Each time you can climb either 1 or 2 steps. Find in how many distinct ways you can climb to the top.

---

### ✅ **Approach 1: Dynamic Programming (Tabulation)**

```java
class Solution {
    public int climbStairs(int n) {
        if(n <= 3) return n;
        int[] dp = new int[n + 1];
        dp[2] = 2;
        dp[3] = 3;
        for(int i = 4; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```

* **Concept**: This uses bottom-up DP with a `dp` array.
* **State Definition**: `dp[i]` = number of ways to reach step `i`.
* **Transition**: `dp[i] = dp[i-1] + dp[i-2]` (either take 1 or 2 steps)
* **Space Complexity**: `O(n)`

---

### ✅ **Approach 2: Space Optimized DP**

```java
class Solution {
    public int climbStairs(int n) {
        if(n <= 3) return n;

        int p3 = 3;
        int p2 = 2;
        int curr = 0;

        for(int i = 4; i <= n; i++){
            curr = p3 + p2;
            p2 = p3;
            p3 = curr;
        }

        return curr;
    }
}
```

* **Concept**: Optimizes space by storing only last two values instead of full array.
* **Space Complexity**: `O(1)` (constant space)
* **Time Complexity**: `O(n)`

---

### 🔁 **Summary:**

This is a classic **Dynamic Programming** problem with **Optimal Substructure** and **Overlapping Subproblems**.
Both solutions use **bottom-up tabulation**, the second one improves space efficiency.

---
