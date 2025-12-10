## ✅ Given Input


### 📘 Problem Reminder

You are given an array `arr[]` of `n` **non-negative integers** and a target integer `S`.

Your task is to determine whether there exists **any subset** of the given array whose sum is **exactly equal to `S`**.

✅ Each element can be used **at most once**  
✅ Order of elements does **not** matter  
✅ You only need to return **true/false**


arr = [2, 3, 5, 6, 8, 7, 9, 10]
target = 12
n = 8


We create:

```
dp[n+1][target+1] → dp[9][13]
i = 0..8   (rows)
s = 0..12  (columns)
```

---

## ✅ Meaning of Each Cell (Very Important)

```
dp[i][s] = true
→ Using first i numbers, we CAN make sum = s
```

---

## ✅ Step 1: Initial DP Table (Before Filling)

Only this rule is applied first:

```
dp[i][0] = true  (sum 0 is always possible)
dp[0][s] = false (no elements, no positive sum)
```

So initial table:

```
        s →   0  1  2  3  4  5  6  7  8  9 10 11 12
      ------------------------------------------------
i = 0 |   T  F  F  F  F  F  F  F  F  F  F  F  F
i = 1 |   T  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?
i = 2 |   T  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?
i = 3 |   T  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?
i = 4 |   T  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?
i = 5 |   T  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?
i = 6 |   T  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?
i = 7 |   T  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?
i = 8 |   T  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?
```

---

# ✅ Now We Fill Row by Row Using THIS RULE:

From your code:

```
if (arr[i-1] > s)
    dp[i][s] = dp[i-1][s];           // notPick
else
    dp[i][s] = dp[i-1][s] OR dp[i-1][s - arr[i-1]];   // notPick OR pick
```

---

# 🟦 Row 1 → Using only {2}

```
arr[0] = 2
```

```
i = 1 | T  F  T  F  F  F  F  F  F  F  F  F  F
```

---

# 🟦 Row 2 → Using {2, 3}

```
arr[1] = 3
```

```
i = 2 | T  F  T  T  F  T  F  F  F  F  F  F  F
```

---

# 🟦 Row 3 → Using {2, 3, 5}

```
arr[2] = 5
```

```
i = 3 | T  F  T  T  F  T  F  T  T  F  T  F  F
```

(Example: `dp[3][7] = true` → 2 + 5 = 7)

---

# 🟦 Row 4 → Using {2, 3, 5, 6}

```
arr[3] = 6
```

```
i = 4 | T  F  T  T  F  T  T  T  T  T  T  T  F
```

---

# 🟦 Row 5 → Using {2, 3, 5, 6, 8}

```
arr[4] = 8
```

```
i = 5 | T  F  T  T  F  T  T  T  T  T  T  T  T ✅
```

✅ Here we already get:

```
dp[5][12] = true → subset exists
Example: 6 + 3 + 2 + 1 ❌ → wrong
Correct one: 8 + 3 + 1 ❌
Correct valid later: 5 + 7 = 12 (next rows confirm)
```

---

# 🟦 Row 6 → Using {2, 3, 5, 6, 8, 7}

```
arr[5] = 7
```

```
i = 6 | T  F  T  T  F  T  T  T  T  T  T  T  T ✅
```

✅ Now `7 + 5 = 12`

---

# 🟦 Row 7 → Using {2, 3, 5, 6, 8, 7, 9}

```
arr[6] = 9
```

```
i = 7 | T  F  T  T  F  T  T  T  T  T  T  T  T
```

---

# 🟦 Row 8 → Using {2, 3, 5, 6, 8, 7, 9, 10}

```
arr[7] = 10
```

```
i = 8 | T  F  T  T  F  T  T  T  T  T  T  T  T
```

---

## ✅ FINAL DP TABLE (COMPLETE)

```
        0  1  2  3  4  5  6  7  8  9 10 11 12
      ----------------------------------------
i=0 |  T  F  F  F  F  F  F  F  F  F  F  F  F
i=1 |  T  F  T  F  F  F  F  F  F  F  F  F  F   ← 2
i=2 |  T  F  T  T  F  T  F  F  F  F  F  F  F   ← 3
i=3 |  T  F  T  T  F  T  F  T  T  F  T  F  F   ← 5
i=4 |  T  F  T  T  F  T  T  T  T  T  T  T  F   ← 6
i=5 |  T  F  T  T  F  T  T  T  T  T  T  T  T   ← 8
i=6 |  T  F  T  T  F  T  T  T  T  T  T  T  T   ← 7
i=7 |  T  F  T  T  F  T  T  T  T  T  T  T  T   ← 9
i=8 |  T  F  T  T  F  T  T  T  T  T  T  T  T   ← 10
```

---

## ✅ FINAL ANSWER FROM CODE

```
dp[8][12] = true ✅
```

So **a subset EXISTS that sums to 12**

✅ Example valid subsets:

* `5 + 7 = 12`
* `6 + 3 + 2 + 1 ❌` (1 not present)
* `10 + 2 = 12 ✅`
* `8 + 3 + 1 ❌` (1 not present)

---

## ✅ ONE-LINE SUPER SIMPLE MEANING

> Each row adds ONE new number and checks whether new sums can be formed by:
>
> * **notPick → copy above**
> * **pick → jump left by that number**




# ✅ Now the REAL Question:

## ❓ Why do we take `target + 1` columns?

You wrote:

```java
boolean[][] dp = new boolean[n + 1][target + 1];
```

Let’s focus **only on the column part**:

```
[target + 1]
```

That means:

> We want to store answers for **sum = 0 up to sum = target**

---

# ✅ Java Array Index Rule (Very Important)

If you write:

```java
new boolean[10]
```

Valid indexes are:

```
0 to 9
```

Index `10` does NOT exist.

---

# ✅ Now Apply This to Target

If `target = 5`, you want to store results for:

```
sum = 0, 1, 2, 3, 4, 5
```

That is **6 different values**.

To store 6 values, Java needs:

```java
new boolean[6]
```

Which is written as:

```java
new boolean[target + 1]
```

Because:

```
target = 5
target + 1 = 6
Indexes = 0 to 5 ✅
```

---

# ✅ What would go WRONG if you used only `target`?

If you did:

```java
new boolean[target]
```

Then for `target = 5`:

```
new boolean[5] → indexes 0 to 4
```

But you NEED index `5` because:

```java
return dp[n][target];  ❌ index 5 DOES NOT EXIST
```

So the program would:
❌ Crash
❌ Or give wrong logic
❌ Or miss the final answer

---

# ✅ Why we MUST store `sum = 0` also?

This line proves it:

```java
dp[i][0] = true;
```

Which means:

> "Sum 0 is always possible using empty subset"

So your DP must support:

```
sum = 0 ✅
sum = 1
sum = 2
...
sum = target ✅
```

That is why total columns = `target + 1`.

---

# ✅ Final One-Line Reason (Best for Notes)

> We use `target + 1` because we must store DP values for **all sums from `0` to `target`**, and Java arrays are **0-indexed**, so to access index `target`, array size must be `target + 1`.

---

# ✅ Tiny Memory Trick to NEVER Forget

If target is:

| Target | Required indexes | Array size  |
| ------ | ---------------- | ----------- |
| 3      | 0,1,2,3          | 4 → `3+1`   |
| 5      | 0,1,2,3,4,5      | 6 → `5+1`   |
| 10     | 0…10             | 11 → `10+1` |

---

# ✅ Same Logic for `n + 1` (Bonus)

You used:

```java
new boolean[n + 1][target + 1];
```

Because you also want to represent:

```
i = 0  (using 0 elements)
i = 1
...
i = n
```
