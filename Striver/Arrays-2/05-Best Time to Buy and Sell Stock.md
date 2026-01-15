
# Best Time to Buy and Sell Stock [Single Transaction](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/  )
---

## 1️⃣ Explain the Problem

### 📌 Problem Statement  
You are given an integer array `prices[]` where `prices[i]` represents the stock price on day `i`.

You are allowed to:
- **Buy once**
- **Sell once**
- **Buy must happen before sell**

Your task is to **maximize the profit**.  
If no profit is possible, return `0`.

---

### 🔗 Problem Link  
- LeetCode: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/  
- GeeksforGeeks: https://www.geeksforgeeks.org/problems/buy-and-sell-a-share-at-most-twice/1 (related)

---

### ✏️ Example

```

Input: prices = [7,1,5,3,6,4]
Output: 5

Explanation:
Buy at price = 1
Sell at price = 6
Profit = 6 - 1 = 5

```

---

## 2️⃣ Similar Problems (Same Pattern)

- Best Time to Buy and Sell Stock II (Multiple transactions)
- Best Time to Buy and Sell Stock with Cooldown
- Best Time to Buy and Sell Stock with Transaction Fee
- Maximum Subarray Sum (Kadane’s Algorithm)
- Maximum Difference Problem

All rely on **tracking optimal state while traversing once**.

---

## 3️⃣ Core Logic Behind the Problem & Main Challenge

### 🎯 Goal  

Maximize:

```

profit = sellPrice - buyPrice

```

With constraint:

```

buyDay < sellDay

````

---

### ⚠️ Main Challenges

- You **cannot** try all buy–sell pairs (too slow)
- You must:
  - Always buy before selling
  - Decide the best buy price dynamically
- Prices change every day → decision must be **online**

---

## 4️⃣ Brute Force Solution & Why It’s Not Acceptable

### 🧠 Idea  
Try all possible `(buyDay, sellDay)` pairs and calculate profit.

### 💻 Code
```java
int maxProfit(int[] prices) {
    int maxProfit = 0;
    for (int i = 0; i < prices.length; i++) {
        for (int j = i + 1; j < prices.length; j++) {
            maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
        }
    }
    return maxProfit;
}
````

---

### ❌ Problems

* Time Complexity: **O(n²)**
* Too slow for large inputs (`n` up to 10⁵)
* Fails performance constraints
* Not interview-acceptable

---

## 5️⃣ All Methods & Logic Behind Each

---

### ✅ Method 1: Greedy with Minimum Price Tracking (Optimal)

### 🔑 Key Insight

At every day:

* Assume **you sell today**
* Best profit = `todayPrice - minimumPriceSoFar`

So we must always know:

* The **lowest price before today**
* The **best profit seen so far**

---

### 🧠 Logic

1. Initialize:

   ```
   minPrice = very large
   maxProfit = 0
   ```

2. Traverse prices:

   * Update `minPrice`
   * Calculate profit if sold today
   * Update `maxProfit`

---

### 💻 Code (Optimal)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;        // best buy so far
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }
        return maxProfit;
    }
}
```

---

## The ONE rule you must remember

> **Always buy at the lowest price before today.
> Always sell today only if it increases profit.**

---

## Why this rule exists (important intuition)

* Profit depends on **difference**
* To maximize difference:

  * Minimize buy price
  * Maximize sell price (after buying)

Tracking minimum ensures:

* Buy always happens **before** sell
* No invalid transaction is considered

---

## Fixed 2-step pattern (never break this)

```java
minPrice = Math.min(minPrice, currentPrice);
maxProfit = Math.max(maxProfit, currentPrice - minPrice);
```

📌 **This order matters**

---

## The code we are visualizing

```java
int minPrice = prices[0];
int profit = 0;

for (int i = 1; i < prices.length; i++) {
    minPrice = Math.min(minPrice, prices[i]);
    profit = Math.max(profit, prices[i] - minPrice);
}
```

---

# 🔍 Visualization with BIG & CLEAR examples

---

## Example 1️⃣

### `prices = [7, 1, 5, 3, 6, 4]`

---

### STEP-BY-STEP TABLE (MOST IMPORTANT)

| Day | Price | minPrice | Profit if sold today | maxProfit |
| --- | ----- | -------- | -------------------- | --------- |
| 0   | 7     | 7        | —                    | 0         |
| 1   | 1     | 1        | —                    | 0         |
| 2   | 5     | 1        | 4                    | 4         |
| 3   | 3     | 1        | 2                    | 4         |
| 4   | 6     | 1        | 5                    | 5         |
| 5   | 4     | 1        | 3                    | 5         |

### ✅ Answer = **5**

---

## What is actually happening?

* Day 1 gives best buy price = `1`
* Every later day:

  * Try selling
  * Keep the best profit

Noise (bad prices) is ignored automatically.

---

## Example 2️⃣ (Descending prices — edge case)

### `prices = [7,6,4,3,1]`

---

| Day | Price | minPrice | Profit | maxProfit |
| --- | ----- | -------- | ------ | --------- |
| 0   | 7     | 7        | —      | 0         |
| 1   | 6     | 6        | -1     | 0         |
| 2   | 4     | 4        | -2     | 0         |
| 3   | 3     | 3        | -1     | 0         |
| 4   | 1     | 1        | -2     | 0         |

### ✅ Answer = **0**

No profit → no transaction

---

# 🔍 Alternative View (Kadane’s Algorithm Insight)

### Transform Prices to Daily Differences

```
diff[i] = prices[i] - prices[i-1]
```

Then find **maximum subarray sum**.

---

### 💻 Code

```java
class Solution {
    public int maxProfit(int[] prices) {
        int curr = 0, maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            curr = Math.max(0, curr + prices[i] - prices[i - 1]);
            maxProfit = Math.max(maxProfit, curr);
        }
        return maxProfit;
    }
}
```

---

### Why this works

* Continuous positive differences = rising stock
* Maximum subarray = best buy-sell window

---

## 6️⃣ Why the Best Method Works (Greedy Proof)

### 🔑 Invariant

At every index `i`:

* `minPrice` = minimum price from day `0` to `i`
* `maxProfit` = maximum profit using days up to `i`

So final answer is correct.

---

## 7️⃣ Comparison Summary

| Method      | Idea                        | Time  | Space | Accepted |
| ----------- | --------------------------- | ----- | ----- | -------- |
| Brute Force | Try all pairs               | O(n²) | O(1)  | ❌        |
| Greedy Min  | Track best buy              | O(n)  | O(1)  | ✅        |
| Kadane View | Max subarray on differences | O(n)  | O(1)  | ✅        |

---

## 8️⃣ Time & Space Complexity

### ⏱ Time

* Single traversal → **O(n)**

### 📦 Space

* Only variables → **O(1)**

---

## 9️⃣ Key Takeaways (Must Remember)

* Buy **before** sell
* Track **minimum price so far**
* Profit is checked **every day**
* One pass is enough
* This is **already optimal**
* Same logic extends to advanced stock problems

---

### ✅ Final One-Liner

> **We don’t search for buy & sell days — we let the best opportunity emerge while scanning once.**

