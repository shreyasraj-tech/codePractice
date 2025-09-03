**"Min Cost Climbing Stairs"** problem on LeetCode

### [Stair climbing count (like Fibonacci) - Leetcode] (https://leetcode.com/problems/climbing-stairs/description/)

You're implementing logic for **stair climbing count (like Fibonacci)**, but the original problem is:

> You are given an array `cost` where `cost[i]` is the cost of stepping on the `i-th` stair. You can either climb one or two steps. Return the minimum cost to reach the top of the floor.

So this part is incorrect:

```java
dp[1] = 1;
dp[2] = 2;
dp[i] = dp[i - 1] + dp[i - 2];
```

That logic calculates the number of ways to reach the `n-th` stair, **not the minimum cost** to do so.

---

### ✅ Correct version of the code:

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        
        dp[0] = 0;
        dp[1] = 0;  // No cost to start at step 0 or 1

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                             dp[i - 2] + cost[i - 2]);
        }

        return dp[n];
    }
}
```

### Summary:

* ❌ Your code calculates number of ways to reach the `n-th` step (like climbing stairs problem).
* ✅ The correct code computes **minimum cost** using `cost[i]` values.
* The cast `int n = cost.length;` is valid and correct.


The line:

```java
dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                 dp[i - 2] + cost[i - 2]);
```

is the **core logic** of the dynamic programming solution for the **"Min Cost Climbing Stairs"** problem.

---

### 🔍 What it means:

To reach step `i`, you have **two options**:

1. **From step `i - 1`**: You pay `cost[i - 1]` to get there, and you already paid `dp[i - 1]` to reach that step.
2. **From step `i - 2`**: You pay `cost[i - 2]` to get there, and you already paid `dp[i - 2]`.

So, you choose the **minimum cost** between those two options:

```
dp[i] = min(cost to reach i from i-1, cost to reach i from i-2)
```

---

### 🔧 Example:

If `cost = [10, 15, 20]`:

- Step 0 → cost 10  
- Step 1 → cost 15  
- Step 2 (top) → choose min of:  
  - `dp[1] + cost[1] = 0 + 15 = 15`  
  - `dp[0] + cost[0] = 0 + 10 = 10`  
- So, `dp[2] = min(15, 10) = 10`

Then you return `dp[n]`, which is the minimum cost to reach the top **after the last step**.

---

### ✅ Final Point:
This logic ensures you're always picking the **cheapest** path to reach the top.

You're absolutely right to ask for a step-by-step visual. Let's go through the `cost = [1,100,1,1,1,100,1,1,100,1]` example **in full detail**, and explain exactly how this works using:

```java
dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
```

---

### 📌 Problem Goal:
Reach the "top floor", which is **just after the last stair** (i.e., `dp[10]`), and we can start from step 0 or step 1. So:

- `dp[0] = 0`
- `dp[1] = 0`

---

### 🧠 Initialize:
```
cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
n = cost.length = 10
dp size = n + 1 = 11 (to include the top)
```

---

### 🔄 Step-by-step table:

| `i` | `dp[i]` computation | Value | Explanation |
|----|----------------------|-------|-------------|
| 2  | min(dp[1]+cost[1], dp[0]+cost[0]) | min(0+100, 0+1) = 1 | Step 2 from step 0 is cheaper |
| 3  | min(dp[2]+cost[2], dp[1]+cost[1]) | min(1+1, 0+100) = 2 | Step 3 from step 2 is cheaper |
| 4  | min(dp[3]+cost[3], dp[2]+cost[2]) | min(2+1, 1+1) = 2 | Step 4 from step 2 is better |
| 5  | min(dp[4]+cost[4], dp[3]+cost[3]) | min(2+1, 2+1) = 3 | Both are equal, choose any |
| 6  | min(dp[5]+cost[5], dp[4]+cost[4]) | min(3+100, 2+1) = 3 | Step 6 from step 4 is better |
| 7  | min(dp[6]+cost[6], dp[5]+cost[5]) | min(3+1, 3+100) = 4 | Step 7 from step 6 |
| 8  | min(dp[7]+cost[7], dp[6]+cost[6]) | min(4+1, 3+1) = 4 | Step 8 from step 6 is cheaper |
| 9  | min(dp[8]+cost[8], dp[7]+cost[7]) | min(4+100, 4+1) = 5 | Step 9 from step 7 |
|10  | min(dp[9]+cost[9], dp[8]+cost[8]) | min(5+1, 4+100) = 6 | Step 10 from step 9 |

---

### ✅ Final Result:

`dp[10] = 6` → **Minimum cost to reach the top**

---

### 🔍 What is `cost[i-1]`, `dp[i-2] + cost[i-2]`?

Let’s pick one value, say `i = 5`:
```java
dp[5] = Math.min(dp[4] + cost[4], dp[3] + cost[3])
       = Math.min(2 + 1, 2 + 1)
       = 3
```

This means:
- From step 3: pay 1 (cost[3]) + whatever it took to reach 3 (dp[3] = 2)
- From step 4: pay 1 (cost[4]) + whatever it took to reach 4 (dp[4] = 2)

Both paths cost 3, so we pick either.

---

### 🧩 Final `dp` array:
```java
dp = [0, 0, 1, 2, 2, 3, 3, 4, 4, 5, 6]
```

Return `dp[10] = 6`.
